package com.jp.gojekassignment.data.source.remote

import com.jp.gojekassignment.BuildConfig
import com.jp.gojekassignment.data.ServerInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient (private val serverInfo: ServerInfo) {
        private var retrofit:Retrofit? = null
        fun getClient() : Retrofit {
            if (retrofit == null) {
                retrofit = initClient()
            }
            return retrofit!!
        }

        private fun initClient() = Retrofit.Builder()
            .baseUrl(serverInfo.baseUrl)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private fun getHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpLoggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
            return okHttpClient.build()
        }
}