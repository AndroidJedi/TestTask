package com.medisafe.task.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.medisafe.task.R
import com.medisafe.task.api.model.CountryDetails
import com.medisafe.task.databinding.FragmentCountryDetailBinding

class CountryDetailFragment : Fragment() {

    private lateinit var countryDetails: CountryDetails
    private lateinit var binding: FragmentCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryDetails = arguments!!.getSerializable(COUNTRY_DETAILS) as CountryDetails
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_detail, container, false)
        activity!!.title = countryDetails.englishName
        binding.countryDetails = countryDetails
        return binding.root
    }


    companion object {
        private const val COUNTRY_DETAILS = "country_details_key"
        @JvmStatic
        fun newInstance(countryDetails: CountryDetails) = CountryDetailFragment().apply {
            arguments = bundleOf(COUNTRY_DETAILS to countryDetails)
        }
    }

}