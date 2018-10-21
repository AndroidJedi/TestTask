package com.medisafe.task.view.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.medisafe.task.api.Api
import com.medisafe.task.api.model.Country
import com.medisafe.task.api.model.CountryDetails
import com.medisafe.task.api.model.CountryRequest
import com.medisafe.task.view.common.AbstractViewModel
import com.medisafe.task.view.list.adapter.CountryHolderTypeGraph
import com.medisafe.task.view.list.adapter.cardHalfRowSection
import com.medisafe.task.view.list.adapter.cardSection
import com.medisafe.task.view.list.adapter.listRightTextSection


class CountryListViewModel(private val api: Api) : AbstractViewModel() {


    val isLoading = ObservableBoolean()

    var countryList = mutableListOf<Country>()

    val result = MutableLiveData<Result?>()

    val graph = CountryHolderTypeGraph(cardSection, cardHalfRowSection, listRightTextSection, cardSection, cardHalfRowSection)

    fun fetchCountries() {
        if (!countryList.isEmpty()) {
            return
        }
        isLoading.set(true)
        launch {
            api.fetchAllCountries(CountryRequest.ALL)
                    .doAfterTerminate { isLoading.set(false) }
                    .subscribe(
                            {
                                graph.build(it.size)
                                countryList.addAll(it)
                                result.value = CountryList(it)
                            },
                            {
                                result.value = Error(it)
                            })
        }
    }

    fun selectCountry(country: Country) {
        isLoading.set(true)
        launch {
            api.fetchCountryDetails(country.englishName, CountryRequest.DETAILS)
                    .doAfterTerminate { isLoading.set(false) }
                    .subscribe(
                            {
                                result.value = CountryInfo(it.first())
                                result.value = null
                            },
                            {
                                result.postValue(Error(it))
                            })
        }
    }

}

sealed class Result
data class Error(val throwable: Throwable) : Result()
data class CountryList(val list: List<Country>) : Result()
data class CountryInfo(val details: CountryDetails) : Result()