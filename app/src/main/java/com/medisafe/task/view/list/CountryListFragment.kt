package com.medisafe.task.view.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.medisafe.task.R
import com.medisafe.task.api.model.CountryDetails
import com.medisafe.task.databinding.FragmentCountryListBinding
import com.medisafe.task.view.list.adapter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryListFragment : Fragment() {

    private lateinit var listener: OnSelectCountryListener
    private val model: CountryListViewModel by viewModel()
    lateinit var binding: FragmentCountryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.fetchCountries()

        model.result.observe(this, Observer {
            when (it) {
                is CountryList -> binding.countryRecyclerView.adapter?.notifyDataSetChanged()
                is CountryInfo -> listener.onCountryDetailsFetched(it.details)
                is Error       -> listener.onError(it.throwable)
            }
        })

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSelectCountryListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnSelectCountryListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_list, container, false)
        activity!!.title = getString(R.string.fragment_country_list_title)
        binding.viewModel = model

        val gridManager = GridLayoutManager(context, 2)
        gridManager.spanSizeLookup = SpanGroupSize(model.graph)

        binding.countryRecyclerView.layoutManager = gridManager
        binding.countryRecyclerView.adapter = RecyclerViewAdapter(model)

        return binding.root
    }

    interface OnSelectCountryListener {
        fun onCountryDetailsFetched(details: CountryDetails)
        fun onError(error: Throwable)
    }

    companion object {
        fun newInstance() = CountryListFragment()
    }


}