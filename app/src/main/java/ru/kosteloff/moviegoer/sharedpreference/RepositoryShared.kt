package ru.kosteloff.moviegoer.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE

class RepositoryShared {

    private var valueFavorite: Boolean? = null

    fun saveValue(context: Context, value: Boolean, keyId: String, nameTitle: String) {
        valueFavorite = value

        val shared = context.getSharedPreferences(nameTitle, MODE_PRIVATE)
        shared.edit().putBoolean(keyId, value).apply()
    }

    fun getValue(context: Context, keyId: String, nameTitle: String): Boolean {
        val getShared = context.getSharedPreferences(nameTitle, MODE_PRIVATE)
        return getShared.getBoolean(keyId, false)
    }
}