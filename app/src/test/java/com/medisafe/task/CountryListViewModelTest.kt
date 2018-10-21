package com.medisafe.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.medisafe.task.api.Api
import com.medisafe.task.api.model.Country
import com.medisafe.task.api.model.CountryDetails
import com.medisafe.task.api.model.Currency
import com.medisafe.task.view.list.CountryInfo
import com.medisafe.task.view.list.CountryList
import com.medisafe.task.view.list.Error
import com.medisafe.task.view.list.CountryListViewModel
import com.medisafe.task.view.list.Result
import org.junit.Test
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule


class CountryListViewModelTest {
    @get:Rule
    val archInstantRule: TestRule = InstantTaskExecutorRule()

    private val countryDetails: MutableList<CountryDetails> = mutableListOf(CountryDetails("CountryName", "NativeName",
            "Flag", "Population", "Catital", "Region", listOf(Currency("code", "name", "symbol"))))
    private val country = Country("CountryName", "NativeName", "Flag", "Population")
    private val countryList: MutableList<Country> = mutableListOf(country)


    @Test
    fun `model should have properly initialized properties`() {
        val api: Api = mock()
        val model = CountryListViewModel(api)
        assertNotNull(model.isLoading)
        assertNotNull(model.countryList)
        assertNotNull(model.result)

        assertTrue(model.countryList.isEmpty())
    }


    @Test
    fun `model should send api request for fetching country list if country list is empty`() {
        val api: Api = mock {
            on { fetchAllCountries(any()) } doReturn Single.just<List<Country>>(countryList)
        }
        val loadingObserver: Observable.OnPropertyChangedCallback = mock()
        val resultObserver: Observer<Result?> = mock()

        val model = CountryListViewModel(api)

        model.result.observeForever(resultObserver)
        model.isLoading.addOnPropertyChangedCallback(loadingObserver)

        model.fetchCountries()


        inOrder(loadingObserver, resultObserver) {
            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())

            verify(resultObserver).onChanged(CountryList(countryList))

            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())
            assertFalse(model.isLoading.get())
        }

    }

    @Test
    fun `model should not send api request for fetching country list if country list is already filled`() {
        val api: Api = mock()
        val loadingObserver: Observable.OnPropertyChangedCallback = mock()
        val resultObserver: Observer<Result?> = mock()

        val model = CountryListViewModel(api)
        model.countryList = countryList

        model.result.observeForever(resultObserver)
        model.isLoading.addOnPropertyChangedCallback(loadingObserver)

        model.fetchCountries()


        inOrder(loadingObserver, resultObserver) {
            verify(loadingObserver, never()).onPropertyChanged(eq(model.isLoading), any())

            verify(resultObserver, never()).onChanged(CountryList(countryList))
        }

    }

    @Test
    fun `model should send api request for fetching country detail info`() {
        val api: Api = mock {
            on { fetchCountryDetails(any(), any()) } doReturn Single.just<List<CountryDetails>>(countryDetails)
        }
        val loadingObserver: Observable.OnPropertyChangedCallback = mock()
        val resultObserver: Observer<Result?> = mock()

        val model = CountryListViewModel(api)

        model.result.observeForever(resultObserver)
        model.isLoading.addOnPropertyChangedCallback(loadingObserver)

        model.selectCountry(country)


        inOrder(loadingObserver, resultObserver) {
            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())

            verify(resultObserver).onChanged(CountryInfo(countryDetails.first()))

            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())
            assertFalse(model.isLoading.get())
        }

    }

    @Test
    fun `model should send notify observers on error`() {

        val exception = Exception("404")

        val api: Api = mock {
            on { fetchCountryDetails(any(), any()) } doReturn  Single.error<List<CountryDetails>>(exception)
        }
        val loadingObserver: Observable.OnPropertyChangedCallback = mock()
        val resultObserver: Observer<Result?> = mock()

        val model = CountryListViewModel(api)

        model.result.observeForever(resultObserver)
        model.isLoading.addOnPropertyChangedCallback(loadingObserver)

        model.selectCountry(country)


        inOrder(loadingObserver, resultObserver) {
            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())

            verify(resultObserver).onChanged(Error(exception))

            verify(loadingObserver).onPropertyChanged(eq(model.isLoading), any())
            assertFalse(model.isLoading.get())
        }

    }


}
