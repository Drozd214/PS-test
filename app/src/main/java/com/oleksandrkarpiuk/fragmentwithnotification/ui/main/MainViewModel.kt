package com.oleksandrkarpiuk.fragmentwithnotification.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments.FragmentsRepository
import com.oleksandrkarpiuk.fragmentwithnotification.ui.main.MainActivity.Companion.DEFAULT_FRAGMENT_NUMBER
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val fragmentsRepository: FragmentsRepository
) : ViewModel() {

    private var _fragments: MutableLiveData<MutableList<FragmentModel>> = MutableLiveData(mutableListOf())
    val fragments: LiveData<MutableList<FragmentModel>> = _fragments

    var currentIndex: Int = 0

    fun init(currentFragmentNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val fragments = fragmentsRepository.getFragments()
            if(fragments.isEmpty()) {
                val firstFragment = FragmentModel(1)
                fragments.add(firstFragment)
                fragmentsRepository.saveFragment(firstFragment)
            }
            val index = if(currentFragmentNumber == DEFAULT_FRAGMENT_NUMBER) {
                DEFAULT_INT
            } else {
                fragments.indexOf(FragmentModel(currentFragmentNumber))
            }
            withContext(Dispatchers.Main) {
                currentIndex = index
                _fragments.value = fragments
            }
        }
    }

    fun plusClicked() {
        _fragments.value?.last()?.let {
            val newList = _fragments.value
            val fragment = FragmentModel(it.number + 1)
            viewModelScope.launch(Dispatchers.IO)  { fragmentsRepository.saveFragment(fragment) }
            newList?.add(fragment)
            _fragments.postValue(newList)
            currentIndex = fragments.value?.lastIndex ?: DEFAULT_INT
        }
    }

    fun minusClicked() {
        _fragments.value?.let {
            if(it.size > 1) {
                val newList = _fragments.value
                val fragment = newList?.removeAt(currentIndex)
                if(fragment != null) {
                    viewModelScope.launch(Dispatchers.IO)  { fragmentsRepository.deleteFragment(fragment) }
                }
                _fragments.postValue(newList)
                if(currentIndex != 0) currentIndex--
            }
        }
    }

    fun getCurrentNumber(): Int {
        return _fragments.value?.let {
            if(it.size > 0) {
                it.elementAt(currentIndex).number
            } else DEFAULT_INT
        } ?: DEFAULT_INT
    }

    companion object {
        const val DEFAULT_INT = 0
    }

}