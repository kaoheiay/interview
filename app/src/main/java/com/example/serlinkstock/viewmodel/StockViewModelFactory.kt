package com.example.serlinkstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serlinkstock.network.StockApiService

class StockViewModelFactory(private val stockApiService: StockApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            return StockViewModel(stockApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}