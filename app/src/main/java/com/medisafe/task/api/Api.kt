package com.medisafe.task.api

import com.medisafe.task.api.model.Country
import com.medisafe.task.api.model.CountryDetails
import io.reactivex.Single
import retrofit2.http.*


interface Api {

    @GET("rest/v2/all")
    fun fetchAllCountries(@Query("fields", encoded = true) responseFields: String): Single<List<Country>>

    @GET("rest/v2/name/{country}")
    fun fetchCountryDetails(@Path("country", encoded = true) englishName: String, @Query("fields", encoded = true) responseFields: String): Single<List<CountryDetails>>

}

