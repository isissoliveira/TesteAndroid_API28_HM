package hotmart.android.recyclerapplication.model

import com.google.gson.annotations.SerializedName

data class ListLocations (
    @SerializedName( "listLocations") val listLocations : List<Location>
)