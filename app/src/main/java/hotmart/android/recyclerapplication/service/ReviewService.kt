package hotmart.android.recyclerapplication.service

import com.google.gson.*
import hotmart.android.recyclerapplication.model.LocationDetail
import hotmart.android.recyclerapplication.model.Review
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Type

private const val BASE_URL_LOCATIONS = "https://0jkw06jisy.api.quickmocker.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_LOCATIONS)
    .build()


interface ReviewApiInterface {
    @GET("locations/reviews/{location_id}")
    fun getReviews( @Path("location_id" ) location_id : String ): Call<List<Review>>
}


/*
* objeto em Kotlin são decralações de objetos Singleton
* O padrão Singleton garante que uma instância de um objeto seja criada e tenha um ponto global de acesso a esse objeto
* */

object SingleReviewApi {
    // Nosso objeto Singleton faz a inicialização do objeto Retrofit
    val RETROFIT_INTERFACE : ReviewApiInterface by lazy {
        retrofit.create(ReviewApiInterface::class.java)

    }
}

