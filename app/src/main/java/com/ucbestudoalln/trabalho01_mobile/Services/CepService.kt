package com.ucbestudoalln.trabalho01_mobile.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepService {

}

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CepService = retrofit.create(CepService::class.java)
}