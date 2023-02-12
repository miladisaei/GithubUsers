package com.miladisaei.githubusers.di

import com.jesus.soldiership.datastore.SettingsDataStore
import com.miladisaei.githubusers.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataStoreModule {

    @Provides
    @ViewModelScoped
    fun providesSettingsDataStore(app: BaseApplication): SettingsDataStore {
        return SettingsDataStore(app)
    }

}