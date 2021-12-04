package hotmart.android.recyclerapplication.model

import com.squareup.moshi.Json

data class ListLocations (
    @Json(name = "listLocations") val listLocations : List<Location>
)