package com.malky.uninotify.app.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.malky.uninotify.data.local.database.EventsDatabase
import com.malky.uninotify.data.local.database.dao.EventsDao
import com.malky.uninotify.data.local.preferences.MetaDataPreferences
import com.malky.uninotify.data.remote.AuthenticationServiceImpl
import com.malky.uninotify.data.remote.EventsServiceImpl
import com.malky.uninotify.data.remote.MetaDataServiceImpl
import com.malky.uninotify.data.repositories.EventsRepositoryImpl
import com.malky.uninotify.data.repositories.MetaDataRepositoryImpl
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.domain.data.MetaDataRepository
import com.malky.uninotify.domain.data.MetaDataService
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
    fun provideAuthenticationService(
        auth: FirebaseAuth,
        @ApplicationContext context: Context
    ): AuthenticationService = AuthenticationServiceImpl(auth = auth, context = context)

    @Provides
    @Singleton
    fun provideThirdPartyAuthentication(
        auth: FirebaseAuth,
        @ApplicationContext context: Context
    ): ThirdPartyAuthentication = AuthenticationServiceImpl(auth = auth, context = context)


    @Provides
    @Singleton
    fun providesEventsService(dp: FirebaseFirestore): EventsService = EventsServiceImpl(dp = dp)

    @Provides
    @Singleton
    fun providesEventsRepository(service: EventsService, dao: EventsDao): EventsRepository =
        EventsRepositoryImpl(
            service = service,
            dao = dao
        )

    @Provides
    @Singleton
    fun provideEventsDatabase(
        @ApplicationContext context: Context
    ): EventsDatabase =
        Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "Events_Database"
        ).build()

    @Provides
    @Singleton
    fun provideEventsDao(db: EventsDatabase): EventsDao = db.eventsDao

    @Provides
    @Singleton
    fun providesMetaDataDataStore(@ApplicationContext context: Context): MetaDataPreferences =
        MetaDataPreferences(context = context)

    @Provides
    @Singleton
    fun provideMetaDataService(dp: FirebaseFirestore): MetaDataService =
        MetaDataServiceImpl(dp = dp)

    @Provides
    @Singleton
    fun provideMetaDataRepository(
        metaData: MetaDataPreferences,
        metaDataService: MetaDataService
    ): MetaDataRepository =
        MetaDataRepositoryImpl(metaData = metaData, metaDataService = metaDataService)
}