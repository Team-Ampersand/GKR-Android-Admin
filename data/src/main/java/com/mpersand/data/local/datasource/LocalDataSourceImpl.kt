package com.mpersand.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalDataSource {
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val ACCESS_TOKEN_EXP = stringPreferencesKey("access_token_exp")
        private val REFRESH_TOKEN_EXP = stringPreferencesKey("refresh_token_exp")
        private val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[ACCESS_TOKEN_EXP] = accessTokenExp
            it[REFRESH_TOKEN_EXP] = refreshTokenExp
            it[IS_LOGIN] = true
        }
    }

    override suspend fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    override suspend fun getRefreshToken(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN] ?: "" }

    override suspend fun getAccessTokenExp(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN_EXP] ?: "" }

    override suspend fun getRefreshTokenExp(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN_EXP] ?: "" }

    override suspend fun isLogin(): Flow<Boolean> = context.dataStore.data.map { it[IS_LOGIN] ?: false }

    override suspend fun logout() {
        context.dataStore.edit {
            it[IS_LOGIN] = false
        }
    }

    override suspend fun removeLocalData() {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = ""
            it[REFRESH_TOKEN] = ""
            it[ACCESS_TOKEN_EXP] = ""
            it[REFRESH_TOKEN_EXP] = ""
            it[IS_LOGIN] = false
        }
    }
}
