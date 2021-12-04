package hotmart.android.recyclerapplication.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hotmart.android.recyclerapplication.model.Location
import hotmart.android.recyclerapplication.model.ListLocations
import hotmart.android.recyclerapplication.model.Propriedade
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL_LOCATIONS =  "https://hotmart-mobile-app.herokuapp.com"
//private const val BASE_URL_LOCATIONS =  "http://simple-node-app-nkd.herokuapp.com"

/*  Moshi é uma biblioteca de terceiros que utilizo para converter strings JSON em objetos Kotlin
    Abaixo estamos criando um objeto desta biblioteca utilizando o Moshi Builder
* */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/*  Retrofit é uma biblioteca de terceiros que utilizo para estabelecer comunicação com o serviço REST
    encontrado na URL "https://hotmart-mobile-app.herokuapp.com/"  e receber uma resposta
* */
private val retrofit = Retrofit.Builder()
    //.addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_LOCATIONS)
    .build()


interface PropriedadeApiService {
    @GET(".")
    fun getPropriedades(): Call<List<Propriedade>>  //TODO -- ESTUDAR "Call"
}

interface LocationApiInterface {
    @GET("locations")
    fun getLocations(): Call<List<Location>>
}

interface ListLocationsApiInterface {
    @GET("locations")
    fun getListLocations(): Call<ListLocations>
}


/*
* objeto em Kotlin são decralações de objetos Singleton
* O padrão Singleton garante que uma instância de um objeto seja criada e tenha um ponto global de acesso a esse objeto
* */
object PropriedadeApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val retrofitService : PropriedadeApiService by lazy {
        retrofit.create(PropriedadeApiService::class.java)
    }
}

object SingleLocationApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val RETROFIT_INTERFACE : LocationApiInterface by lazy {
        retrofit.create(LocationApiInterface::class.java)
    }
}

object SingleListLocationsApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val RETROFIT_INTERFACE : ListLocationsApiInterface by lazy {
        retrofit.create(ListLocationsApiInterface::class.java)
    }
}