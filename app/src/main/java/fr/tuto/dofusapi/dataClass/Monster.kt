package fr.tuto.dofusapi.dataClass

import com.google.gson.annotations.SerializedName

data class Monster (
    @SerializedName("type")var type: String,
    @SerializedName("name") var name:String,
    @SerializedName("imgUrl")var imgUrl : String,
    @SerializedName("statistics") var stat: List<Statistic>,
    var pvMin : Double,
    var pvMax: Double,
    var paMin : Double,
    var paMax: Double,
    var pmMin : Double,
    var pmMax: Double

)
//
//data class Statistic (
//    @SerializedName("PV")var pv : List<PV>
//)
//
//data class PV (
//    @SerializedName("min")var min: Int,
//    @SerializedName("max")var max: Int
//)
//

data class DropPercent (
    val min: Double = 0.0,
    val max: Double = 0.0
)

data class Statistic (
    @SerializedName("PV")
    val pv: DropPercent? = null,

    @SerializedName("PA")
    val pa: DropPercent? = null,

    @SerializedName("PM")
    val pm: DropPercent? = null
)
