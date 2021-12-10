package hotmart.android.recyclerapplication.model


// Classe de dados que vai armazenar os dados de cada Location retornada pela API

data class Location (
    val id      : Int,
    val name    : String,
    val review  : Float,
    val type    : String,
        )

