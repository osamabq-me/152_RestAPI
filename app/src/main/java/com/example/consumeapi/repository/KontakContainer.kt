package com.example.consumeapi.repository

import com.example.consumeapi.service_api.KontakService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val kontakRepository:KontakRepository
}

class KontakContainer: AppContainer{

    //IF  emeliotor use 10.0.2.2
    //IF phone check phones IP ipconfig
    //// CHECK IP Always
    private val baseUrl = "http://10.69.8.30:8080/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val kontakService: KontakService by lazy {
        retrofit.create(KontakService::class.java)
    }
    override val kontakRepository: KontakRepository by lazy {
        NetwworkKontakReposiotory(kontakService)
    }
}
