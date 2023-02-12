package com.jesus.soldiership.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.miladisaei.githubusers.presentation.BaseApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore
@Inject
constructor(private val app: BaseApplication) {

    private val scope = CoroutineScope(Dispatchers.Main)

    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme_key")
    }

    init {
        observeDataStore()
    }

    val isDark = mutableStateOf(false)

    private fun observeDataStore() {
        app.datastore.data.onEach { preferences ->
            preferences[DARK_THEME_KEY]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
        }.launchIn(scope)
    }

    fun toggleTheme() {
        scope.launch {
            app.datastore.edit { preferences ->
                val current = preferences[DARK_THEME_KEY] ?: false
                preferences[DARK_THEME_KEY] = !current
            }
        }
    }

}