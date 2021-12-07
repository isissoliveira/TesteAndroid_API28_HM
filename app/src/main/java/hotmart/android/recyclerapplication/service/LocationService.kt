package hotmart.android.recyclerapplication.service

import hotmart.android.recyclerapplication.model.ListLocations
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL_LOCATIONS =  "https://hotmart-mobile-app.herokuapp.com"

/*  Retrofit é uma biblioteca de terceiros que utilizo para estabelecer comunicação com o serviço REST
    encontrado na URL "https://hotmart-mobile-app.herokuapp.com/"  e receber uma resposta
    Utilizo o Converrter GSON para converter o objeto JSON em objeto Kotlin
* */
private val retrofit = Retrofit.Builder()
    //.addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_LOCATIONS)
    .build()

interface ListLocationsApiInterface {
    @GET("locations")
    fun getListLocations(): Call<ListLocations>
}


/*
* objeto em Kotlin são decralações de objetos Singleton
* O padrão Singleton garante que uma instância de um objeto seja criada e tenha um ponto global de acesso a esse objeto
* */

object SingleListLocationsApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val RETROFIT_INTERFACE : ListLocationsApiInterface by lazy {
        retrofit.create(ListLocationsApiInterface::class.java)
    }
}