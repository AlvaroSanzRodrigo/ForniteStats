package io.github.alvarosanzrodrigo.fornitestatsapp.APIs

import com.google.gson.GsonBuilder
import io.github.alvarosanzrodrigo.fornitestatsapp.Models.Player
import io.github.alvarosanzrodrigo.fornitestatsapp.Repositories.Repository
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody
import retrofit2.http.*


interface ForniteAPI {

    @Headers("TRN-Api-Key: 35c45770-d114-4fac-96d2-73c82610836d")
    @GET("profile/{platform}/{epic-nickname}")
    fun getSearch(@Path("platform") platform: String, @Path("epic-nickname") playerName: String): Flowable<Player>

    companion object Factory{
        fun create(): ForniteAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(Repository.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ForniteAPI::class.java)
        }
    }
}