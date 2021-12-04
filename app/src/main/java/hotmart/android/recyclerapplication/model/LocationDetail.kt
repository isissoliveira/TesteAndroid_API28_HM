package hotmart.android.recyclerapplication.model

data class LocationDetail (
    private val id : Int,
    private val name : String,
    private val review : Double,
    private val type : String,
    private val about : String,
    private val fone : String,
    private val address : String,
    private val schedule : List<Schedule>

        ) {
}