package com.monstarlab.core.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.monstarlab.core.BuildConfig
import com.monstarlab.core.config.BuildConfiguration
import com.monstarlab.core.network.errorhandling.ApiErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
class OkHttpModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }
        clientBuilder.addInterceptor(ChuckerInterceptor.Builder(context).build())
        clientBuilder.addInterceptor(ApiErrorInterceptor(json))
        return clientBuilder.build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, buildConfiguration: BuildConfiguration): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(buildConfiguration.networkConfig.baseUrl)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }
}
