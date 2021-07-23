package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.TMDBConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInstance {
    lateinit var movieListingRepository: MovieListingRepository

    fun getService(): MovieApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val tmdbUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", TMDBConstants.BASE_URL)
                .build()

            chain.proceed(original.newBuilder().url(tmdbUrl).build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

        val movieApiService = retrofit.create(MovieApiService::class.java)

        movieListingRepository = MovieListingRepository()

        return movieApiService
    }
}