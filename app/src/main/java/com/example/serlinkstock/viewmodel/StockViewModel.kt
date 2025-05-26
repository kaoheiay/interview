package com.example.serlinkstock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serlinkstock.model.BWIBBUAllResponse
import com.example.serlinkstock.model.CombinedItem
import com.example.serlinkstock.model.StockDayAllResponse
import com.example.serlinkstock.model.StockDayAvgAllResponse
import com.example.serlinkstock.network.ApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class StockViewModel(private val apiService: ApiService) : ViewModel() {

    private val _combinedItemList = MutableLiveData<List<CombinedItem>>()
    val combinedItemList: LiveData<List<CombinedItem>> = _combinedItemList

    private val _sortAscending = MutableLiveData<Boolean>(false)
    val sortAscending: LiveData<Boolean> = _sortAscending

    fun setSortAscending(ascending: Boolean) {
        _sortAscending.value = ascending
    }

    init {
        fetchAndCombineStockData()
    }

    private fun fetchAndCombineStockData() {
        viewModelScope.launch {
            val stockDayAllDeferred = async { apiService.getStockDayAll() }
            val stockDayAvgAllDeferred = async { apiService.getStockDayAvgAll() }
            val bwibbuAllDeferred = async { apiService.getBWIBBUAll() }

            val results = awaitAll(stockDayAllDeferred, stockDayAvgAllDeferred, bwibbuAllDeferred)

            @Suppress("UNCHECKED_CAST")
            val stockDayAllList = results[0] as? List<StockDayAllResponse> ?: emptyList()
            @Suppress("UNCHECKED_CAST")
            val stockDayAvgAllList = results[1] as? List<StockDayAvgAllResponse> ?: emptyList()
            @Suppress("UNCHECKED_CAST")
            val bwibbuAllList = results[2] as? List<BWIBBUAllResponse> ?: emptyList()

            val combinedMap = mutableMapOf<String, CombinedItem>()

            // 處理 StockDayAll API 的資料
            stockDayAllList.forEach { response ->
                val code = response.Code
                combinedMap[code] = combinedMap.getOrDefault(code, CombinedItem(stockCode = code, stockName = response.Name)).apply {
                    stockName = response.Name
                    tradeVolume = response.TradeVolume
                    tradeValue = response.TradeValue
                    openingPrice = response.OpeningPrice
                    highestPrice = response.HighestPrice
                    lowestPrice = response.LowestPrice
                    closingPrice = response.ClosingPrice
                    change = response.Change
                    transaction = response.Transaction
                }
            }

            // 處理 StockDayAvgAll API 的資料
            stockDayAvgAllList.forEach { response ->
                val code = response.Code
                combinedMap[code] = combinedMap.getOrDefault(code, CombinedItem(stockCode = code, stockName = response.Name)).apply {
                    stockName = response.Name
                    closingPrice = response.ClosingPrice
                    monthlyAveragePrice = response.MonthlyAveragePrice
                }
            }

            // 處理 BWIBBUAll API 的資料
            bwibbuAllList.forEach { response ->
                val code = response.Code
                combinedMap[code] = combinedMap.getOrDefault(code, CombinedItem(stockCode = code, stockName = response.Name)).apply {
                    stockName = response.Name
                    peRatio = response.PEratio
                    dividendYield = response.DividendYield
                    pbRatio = response.PBratio
                }
            }

            _combinedItemList.value = combinedMap.values.toList().sortedByDescending { it.stockCode }
        }
    }
}