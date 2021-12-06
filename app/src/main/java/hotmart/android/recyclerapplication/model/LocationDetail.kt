package hotmart.android.recyclerapplication.model

import android.content.ContentValues
import android.util.Log
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import hotmart.android.recyclerapplication.model.schedule.DaySchedule
import java.lang.reflect.Type

data class LocationDetail (
    @SerializedName("id")       val id : Int,
    @SerializedName("name")     val name : String,
    @SerializedName("review")   val review : Float,
    @SerializedName("type")     val type : String,
    @SerializedName("about")    val about : String,
    @SerializedName("phone")    val phone : String,
    @SerializedName("adress")   val adress : String,
    var meuSchedule : Schedule?
    )
{

    data class Schedule (
        val sunday      : DaySchedule?,
        val monday      : DaySchedule?,
        val tuesday     : DaySchedule?,
        val wednesday   : DaySchedule?,
        val thursday    : DaySchedule?,
        val friday      : DaySchedule?,
        val saturday    : DaySchedule?
    )


    // CUSTOM DESERIALIZER PARA TRATAR QUANDO O ELEMENTO "SCHEDULE" VEM COMO ARRAY NO JSON
    class LocationDetailDeserializer : JsonDeserializer<LocationDetail> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocationDetail {

            var novoLocationDetail : LocationDetail = Gson().fromJson(json, LocationDetail::class.java)

            var jsonObject : JsonObject = json?.asJsonObject ?: throw Exception("Item não encontrado")

                if( jsonObject.has("schedule")){

                    var jsonElementoSchedule    : JsonElement = jsonObject.get("schedule")
                    val novoObjetoSchedule      : LocationDetail.Schedule

                    jsonElementoSchedule?.let {
                        if( !it.isJsonNull && it.isJsonArray ){ // SE O ELEMENTO "schedule" É UM ARRAY JSON

                            // O ELEMENTO [0] DO ARRAY "elementoSchedule" SERÁ O "novoObjetoSchedule"
                            novoObjetoSchedule = Gson().fromJson( it.asJsonArray[0] , LocationDetail.Schedule::class.java )
                            novoLocationDetail.meuSchedule = novoObjetoSchedule

                        }else{
                            // SE O ELEMENTO "schedule" É UM OBJETO JSON ELE SERÁ O "novoObjetoSchedule"
                            novoObjetoSchedule = Gson().fromJson( it.asJsonObject , LocationDetail.Schedule::class.java )
                            novoLocationDetail.meuSchedule = novoObjetoSchedule

                        }
                    }

                }

            return novoLocationDetail
        }

    } // FIM CLASS DESERIALIZER


}

