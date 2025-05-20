package com.example.serlinkstock.network

import com.example.serlinkstock.model.BWIBBUAllResponse
import com.example.serlinkstock.model.StockDayAllResponse
import com.example.serlinkstock.model.StockDayAvgAllResponse
import retrofit2.http.GET

interface StockApiService {
    @GET("/v1/exchangeReport/STOCK_DAY_ALL")
    suspend fun getStockDayAll(): List<StockDayAllResponse>

    @GET("/v1/exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getStockDayAvgAll(): List<StockDayAvgAllResponse>

    @GET("/v1/exchangeReport/BWIBBU_ALL")
    suspend fun getBWIBBUAll(): List<BWIBBUAllResponse>
}