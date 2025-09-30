package com.malky.uninotify.utils

import com.malky.uninotify.R

object UserDataValidator {

    fun validateName(name: String): Response<Unit,UserDataError>{
        if (name.trim().isBlank()){
            return Response.Error(UserDataError.Name_is_empty)
        }
        if(name.length < 3){
            return Response.Error(UserDataError.Invalid_Name)
        }
        return Response.Success(Unit)
    }

    fun validateEmail(email: String): Response<Unit,UserDataError>{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
        if (email.trim().isBlank()){
            return Response.Error(UserDataError.Email_is_empty)
        }
        if (!email.trim().matches(emailRegex)){
            return Response.Error(UserDataError.Invalid_Email)
        }
        return Response.Success(Unit)
    }

    fun validatePassword(password: String): Response<Unit,UserDataError>{
        if (password.trim().isBlank()){
            return Response.Error(UserDataError.Password_is_empty)
        }
        if (password.length < 8){
            return Response.Error(UserDataError.Short_Password)
        }
        return Response.Success(Unit)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Response<Unit,UserDataError>{
        if (password.trim() != confirmPassword.trim()){
            return Response.Error(UserDataError.Passwords_Dont_Match)
        }
        return Response.Success(Unit)

    }

    enum class UserDataError : ErrorType {
        Invalid_Name,
        Name_is_empty,
        Invalid_Email,
        Email_is_empty,
        Password_is_empty,
        Short_Password,
        Passwords_Dont_Match;

        override fun toUiText(): UiText {
            val stringRes = when (this) {
                Invalid_Name -> R.string.InvalidName
                Name_is_empty -> R.string.EmptyName
                Invalid_Email -> R.string.InvalidEmail
                Email_is_empty -> R.string.EmptyEmail
                Password_is_empty -> R.string.EmptyPassword
                Short_Password -> R.string.ShortPassword
                Passwords_Dont_Match -> R.string.PasswordDoesntMatch
            }
            return UiText.StringResourceId(stringRes)
        }
    }

}