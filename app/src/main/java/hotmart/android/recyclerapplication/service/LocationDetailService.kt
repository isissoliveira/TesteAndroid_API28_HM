package hotmart.android.recyclerapplication.service

import com.google.gson.*
import hotmart.android.recyclerapplication.model.LocationDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Type

private const val BASE_URL_LOCATIONS = "https://hotmart-mobile-app.herokuapp.com"


// CUSTOM DESERIALIZER PARA TRATAR QUANDO O ELEMENTO "SCHEDULE" VEM COMO ARRAY NO JSON
private val gSon : Gson = GsonBuilder()
    .registerTypeAdapter( LocationDetail::class.java, LocationDetail.LocationDetailDeserializer())
    .create()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gSon))
    .baseUrl(BASE_URL_LOCATIONS)
    .build()


interface LocationDetailApiInterface {
    @GET("locations/{id}")
    fun getLocationDetail( @Path("id" ) id : String ): Call<LocationDetail>
}


/*
* objeto em Kotlin são decralações de objetos Singleton
* O padrão Singleton garante que uma instância de um objeto seja criada e tenha um ponto global de acesso a esse objeto
* */

object SingleLocationDetailApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val RETROFIT_INTERFACE : LocationDetailApiInterface by lazy {
        retrofit.create(LocationDetailApiInterface::class.java)

    }
}

