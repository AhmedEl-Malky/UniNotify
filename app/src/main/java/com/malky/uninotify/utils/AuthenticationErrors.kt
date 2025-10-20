package com.malky.uninotify.utils

import com.malky.uninotify.R

enum class AuthenticationErrors : ErrorType {
    Something_Went_Wrong,
    Invalid_Credentials,
    User_Not_Found,
    No_Account,
    Email_Already_Exists;

    override fun toUiText(): UiText {
        val stringRes = when (this) {
            Something_Went_Wrong -> R.string.SomethingWentWrong
            Invalid_Credentials -> R.string.InvalidCredentials
            User_Not_Found -> R.string.UserNotFound
            Email_Already_Exists -> R.string.EmailAlreadyExist
            No_Account -> R.string.NoAccount
        }
        return UiText.StringResourceId(stringRes)
    }
}