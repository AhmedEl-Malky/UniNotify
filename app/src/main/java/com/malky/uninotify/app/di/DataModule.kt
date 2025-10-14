package com.malky.uninotify.app.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.malky.uninotify.data.remote.AuthenticationServiceImpl
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideAuthenticationService(auth: FirebaseAuth,@ApplicationContext context: Context): AuthenticationService = AuthenticationServiceImpl(auth = auth,context = context)

    @Provides
    @Singleton
    fun provideThirdPartyAuthentication(auth: FirebaseAuth,@ApplicationContext context: Context): ThirdPartyAuthentication = AuthenticationServiceImpl(auth = auth,context = context)

}