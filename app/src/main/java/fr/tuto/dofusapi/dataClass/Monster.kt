package fr.tuto.dofusapi.dataClass

import com.google.gson.annotations.SerializedName

data class Monster (
    @SerializedName("type")var type: String,
    @SerializedName("name") var name:String,
    @SerializedName("imgUrl")var imgUrl : String
)