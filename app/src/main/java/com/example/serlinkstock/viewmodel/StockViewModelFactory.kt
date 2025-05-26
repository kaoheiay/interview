package com.example.serlinkstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serlinkstock.network.ApiService

class StockViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(StockViewModel::class.java) -> {
                return StockViewModel(apiService) as T
            }
            /*modelClass.isAssignableFrom(AnotherViewModel::class.java) -> {
                AnotherViewModel(someOtherDependency) as T
            }*/
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}