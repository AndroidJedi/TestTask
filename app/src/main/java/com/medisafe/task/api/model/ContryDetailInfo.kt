package com.medisafe.task.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryDetails(@SerializedName("name") val englishName: String,
                          @SerializedName("nativeName") val nativeName: String,
                          @SerializedName("flag") val flag: String,
                          @SerializedName("population") val population: String,
                          @SerializedName("capital") val capital: String,
                          @SerializedName("region") val region: String,
                          @SerializedName("currencies") val currencies: List<Currency>

) : Serializable

data class Currency(@SerializedName("code") val code: String,
                    @SerializedName("name") val name: String,
                    @SerializedName("symbol") val symbol: String

) : Serializable