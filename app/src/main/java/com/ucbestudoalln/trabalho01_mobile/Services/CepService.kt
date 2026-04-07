package com.ucbestudoalln.trabalho01_mobile.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class CepData (
    val localidade: String
)
interface CepService {
    @GET("{cep}/json/")
    suspend fun buscarCep(@Path("cep") cep: String): CepData

}

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CepService = retrofit.create(CepService::class.java)
}