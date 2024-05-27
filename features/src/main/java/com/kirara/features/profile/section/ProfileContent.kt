package com.kirara.features.profile.section

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.R
import com.kirara.core.util.Dimens
import com.kirara.core.util.UrlHelper
import com.kirara.features.components.ConfirmationDialog
import com.kirara.features.components.ProfileItem
import com.kirara.features.profile.ProfileViewModel

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    navigateToEditProfile: () -> Unit,
    navigateToChangePassword: () -> Unit,
    viewModel: ProfileViewModel,
){
    val name: String? by viewModel.name.collectAsState()
    val email: String? by viewModel.email.collectAsState()
    val profileUrl: String? by viewModel.profileUrl.collectAsState()

    val showDialog = remember { mutableStateOf(false) }

    Log.d("",UrlHelper.formatProfileUrl(profileUrl ?: ""))

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = Dimens.dp24, horizontal = Dimens.dp20),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.size(58.dp),
                    shape = CircleShape,
                ) {
                    SubcomposeAsyncImage(
                        loading = {
                            CircularProgressIndicator(
                                color = Color.LightGray,
                                modifier = Modifier.padding(48.dp)
                            )
                        },
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(UrlHelper.formatProfileUrl(profileUrl ?: "") ?: "https://ui-avatars.com/api/?name=$name")
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(80.dp, 80.dp)
                    )
                }
                Spacer(modifier = Modifier.width(Dimens.dp20))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        name ?: "Guest",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                    Text(
                        email ?: "guest@mail.com",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimens.dp48))
            ProfileItem(
                icon = painterResource(id = R.drawable.edit_user),
                title = "Edit Profile",
                onClickAction = { navigateToEditProfile() }
            )
            ProfileItem(
                icon = Icons.Rounded.Lock,
                title = "Change Password",
                onClickAction = { navigateToChangePassword() }
            )
            ProfileItem(
                icon = Icons.Rounded.Logout,
                title = "Logout",
                onClickAction = {
                    showDialog.value = true
                }
            )
        }
        if (showDialog.value) {
            ConfirmationDialog(
                showDialog = showDialog.value,
                onDismissRequest = { showDialog.value = false },
                onConfirmAction = { viewModel.logoutApiCall() },
                title = "Logout",
                message = "Apakah Anda yakin ingin logout?",
                confirmButtonText = "Ya",
                dismissButtonText = "Tidak"
            )
        }
    }
}

