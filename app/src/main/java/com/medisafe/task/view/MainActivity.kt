package com.medisafe.task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import com.medisafe.task.R
import com.medisafe.task.api.model.CountryDetails
import com.medisafe.task.databinding.ActivityMainBinding
import com.medisafe.task.view.common.FragmentNavigator
import com.medisafe.task.view.common.toast
import com.medisafe.task.view.list.*
import com.medisafe.task.view.detail.CountryDetailFragment


class MainActivity : AppCompatActivity(), CountryListFragment.OnSelectCountryListener {

    private lateinit var binding: ActivityMainBinding

    private val navigator = FragmentNavigator(R.id.fragment_container, supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        savedInstanceState ?: navigator.showOnly<CountryListFragment>()
    }

    override fun onCountryDetailsFetched(details: CountryDetails) {
        navigator.show { CountryDetailFragment.newInstance(details) }
    }

    override fun onError(error: Throwable) {
        toast(error.message ?: error.localizedMessage)
    }

}