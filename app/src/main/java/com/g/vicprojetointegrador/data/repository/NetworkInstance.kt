package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.utils.TMDBConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInstance {

    fun getService(): MovieApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor{ chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()
                val tmdbUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", TMDBConstants.API_KEY)
                    .build()

                chain.proceed(original.newBuilder().url(tmdbUrl).build())
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build()

        return retrofit.create(MovieApiService::class.java)
    }
}