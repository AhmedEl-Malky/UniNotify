package com.malky.uninotify.app.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.malky.uninotify.data.remote.AuthenticationServiceImpl
import com.malky.uninotify.data.remote.EventsServiceImpl
import com.malky.uninotify.data.repositories.EventsRepositoryImpl
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import com.malky.uninotify.domain.data.EventsRepository
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
    fun providesFirestoreDP() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideAuthenticationService(auth: FirebaseAuth,@ApplicationContext context: Context): AuthenticationService = AuthenticationServiceImpl(auth = auth,context = context)

    @Provides
    @Singleton
    fun provideThirdPartyAuthentication(auth: FirebaseAuth,@ApplicationContext context: Context): ThirdPartyAuthentication = AuthenticationServiceImpl(auth = auth,context = context)


    @Provides
    @Singleton
    fun providesEventsService(dp: FirebaseFirestore): EventsService = EventsServiceImpl(dp = dp)

    @Provides
    @Singleton
    fun providesEventsRepository(service: EventsService): EventsRepository = EventsRepositoryImpl(service = service)

}