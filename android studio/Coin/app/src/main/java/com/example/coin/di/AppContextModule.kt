package com.example.coin.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppContextModule {
    @Singleton
    @Provides
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }
}