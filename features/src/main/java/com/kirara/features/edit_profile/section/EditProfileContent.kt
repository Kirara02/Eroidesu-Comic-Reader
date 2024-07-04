package com.kirara.features.edit_profile.section

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.R
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.ui.widget.RegularField
import com.kirara.core.util.Dimens
import com.kirara.core.util.FileUtil
import com.kirara.core.util.UrlHelper
import com.kirara.features.edit_profile.EditProfileViewModel
import id.zelory.compressor.Compressor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileContent(
    viewModel: EditProfileViewModel,
    navigateBack: () -> Unit,
) {
    MainTemplate(
        topBar = {
            TopAppBar(
                title = {
                    Text("Edit Profile")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) {
        val _name: String? by viewModel.name.collectAsState()
        val _email: String? by viewModel.email.collectAsState()
        val _profileUrl: String? by viewModel.profileUrl.collectAsState()

        val context = LocalContext.current

        val updateRequest = remember { mutableStateOf(UpdateUserRequest()) }

        val name = remember { mutableStateOf(_name ?: "") }
        val email = remember { mutableStateOf(_email ?: "") }
        val focusManager = LocalFocusManager.current

        val fileResult = remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
//            fileResult.value = it
            it?.let {uri ->
                CoroutineScope(Dispatchers.IO).launch{
                    val file = FileUtil.getFileFromUri(context, uri)
                    val compressedImageFile = Compressor.compress(context, file)
                    val compressedImageUri = Uri.fromFile(compressedImageFile)
                    fileResult.value = compressedImageUri
                }
            }
        }

        val profilePicturePath = fileResult.value?.let { uri ->
            FileUtil.getRealPathFromURI(context, uri)
        }

        updateRequest.value = UpdateUserRequest(
            email = email.value,
            name = name.value,
            profilePicturePath = profilePicturePath
        )

        val imageUrl = remember(_profileUrl, fileResult.value) {
            when {
                fileResult.value != null -> fileResult.value
                _profileUrl != null -> UrlHelper.formatProfileUrl(_profileUrl ?: "")
                else -> "https://ui-avatars.com/api/?name=$_name"
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        loading = {
                            CircularProgressIndicator(
                                color = Color.LightGray,
                                modifier = Modifier.padding(48.dp)
                            )
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp, 80.dp)
                            .clickable {
                                launcher.launch(
                                    PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.name_form),
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                RegularField(
                    value = name.value,
                    hint = stringResource(id = R.string.name_hint),
                    onTextChange = { name.value = it }
                )
                Spacer(modifier = Modifier.height(Dimens.dp16))
                Text(
                    text = stringResource(id = R.string.email_form),
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                RegularField(
                    value = email.value,
                    hint = stringResource(id = R.string.email_hint),
                    onTextChange = { email.value = it },
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
            Spacer(modifier = Modifier.height(Dimens.dp24))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusManager.clearFocus()
                    viewModel.updateUserApiCall(updateRequest.value)
                },
            ) {
                Text(stringResource(id = R.string.update))
            }
        }
    }
}