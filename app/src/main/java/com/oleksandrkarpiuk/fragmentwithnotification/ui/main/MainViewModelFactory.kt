package com.oleksandrkarpiuk.fragmentwithnotification.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments.FragmentsRepository

class MainViewModelFactory(private val fragmentsRepository: FragmentsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(fragmentsRepository) as T
    }

}