package com.medisafe.task.di


import com.medisafe.task.view.list.CountryListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {

    viewModel { CountryListViewModel(get()) }

}

