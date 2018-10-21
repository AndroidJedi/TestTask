package com.medisafe.task.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(@SerializedName("name")       val englishName: String,
                   @SerializedName("nativeName") val nativeName: String,
                   @SerializedName("flag")       val flag: String,
                   @SerializedName("population") val population: String ): Serializable