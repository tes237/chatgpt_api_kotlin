package com.ai.kottest001.di

import android.content.Context
import com.ai.kottest001.helper.SharedPreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesHelperModule {

    @Singleton
    @Provides
    fun provideSharedPreferenceHelper(@ApplicationContext context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(context)
    }
}