package io.github.alvarosanzrodrigo.fornitestatsapp.Repositories

import android.util.Log
import io.github.alvarosanzrodrigo.fornitestatsapp.APIs.ForniteAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor() {
    companion object {
        internal const val BASE_URL = "https://api.fortnitetracker.com/v1/"

        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository().also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private val apiService = ForniteAPI.create()

    fun searchPlayer(platform: String, playerName: String) = apiService.getSearch(platform, playerName)

}
