package com.daniel.raduca.deckofcards.di

import android.content.Context
import androidx.room.Room
import com.daniel.raduca.deckofcards.data.DeckOfCardsRepository
import com.daniel.raduca.deckofcards.data.local.DeckOfCardsDatabase
import com.daniel.raduca.deckofcards.data.local.LocalDataSource
import com.daniel.raduca.deckofcards.data.remote.RemoteDataSource
import com.daniel.raduca.deckofcards.network.DeckOfCardsApiService
import com.daniel.raduca.deckofcards.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): DeckOfCardsRepository {
        return DeckOfCardsRepository(remoteDataSource, localDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: DeckOfCardsApiService
    ): RemoteDataSource = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: DeckOfCardsDatabase
    ): LocalDataSource {
        return LocalDataSource(database)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DeckOfCardsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DeckOfCardsDatabase::class.java,
            "DeckOfCards.db"
        ).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): DeckOfCardsApiService {
        val retrofitService: DeckOfCardsApiService by lazy { retrofit.create(DeckOfCardsApiService::class.java) }
        return retrofitService
    }
}