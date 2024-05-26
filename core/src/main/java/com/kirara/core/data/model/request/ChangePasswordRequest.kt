package com.kirara.core.data.model.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("current_password")
    val currentPassword: String? = null,
    @SerializedName("new_password")
    val newPassword: String? = null,
    @SerializedName("new_password_confirmation")
    val newPasswordConfirmation: String? = null
)
