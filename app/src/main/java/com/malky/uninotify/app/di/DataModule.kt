package com.malky.uninotify.app.di

import com.google.firebase.auth.FirebaseAuth
import com.malky.uninotify.data.remote.AuthenticationServiceImpl
import com.malky.uninotify.domain.authentication.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideAuthenticationService(auth: FirebaseAuth): AuthenticationService = AuthenticationServiceImpl(auth = auth)

}