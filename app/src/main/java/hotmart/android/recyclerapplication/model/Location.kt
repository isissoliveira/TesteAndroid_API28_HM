package hotmart.android.recyclerapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.squareup.moshi.Json

// Classe de dados que vai armazenar os dados de cada Location retornada pela API

data class Location (
    @Json(name = "id")   val id : Int,
    @Json(name = "name")   val name : String,
    @Json(name = "review")  val review : Float,
    @Json(name = "type")  val type : String,
        )

