package com.medisafe.task.view.list.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medisafe.task.R
import com.medisafe.task.api.model.Country
import com.medisafe.task.databinding.*
import com.medisafe.task.view.list.CountryListViewModel
import java.lang.IllegalArgumentException


class RecyclerViewAdapter(private val model: CountryListViewModel) : RecyclerView.Adapter<RecyclerViewAdapter.CountryHolder>() {


    override fun getItemCount() = model.countryList.size

    override fun getItemViewType(position: Int) = model.graph.getTypeForPosition(position)!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return when (viewType) {
            CARD            -> CardHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_country_item, parent, false))
            CARD_HALF_ROW   -> CardHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_country_item, parent, false))
            LIST_RIGHT_TEXT -> ListRightTextHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_country_right_text_item, parent, false))
            else            -> throw  IllegalArgumentException(" Not supported holder type: $viewType ")
        }
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) = holder.bind(model, model.countryList[position])

    class CardHolder(val binding: CardCountryItemBinding) : CountryHolder(binding.root) {
        override fun bind(model: CountryListViewModel, country: Country) {
            binding.viewModel = model
            binding.country = country
            binding.executePendingBindings()
        }
    }

    class ListRightTextHolder(val binding: ListCountryRightTextItemBinding) : CountryHolder(binding.root) {
        override fun bind(model: CountryListViewModel, country: Country) {
            binding.viewModel = model
            binding.country = country
            binding.executePendingBindings()
        }
    }

    abstract class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(model: CountryListViewModel, country: Country)
    }

    companion object {
        const val CARD = 1
        const val LIST_RIGHT_TEXT = 2
        const val CARD_HALF_ROW = 3
    }

}