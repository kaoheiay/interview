package com.example.serlinkstock.model

data class StockDayAvgAllResponse(
    val Code: String,  // 股票代號
    val Name: String, // 股票名稱
    val ClosingPrice: String?, // 收盤價
    val MonthlyAveragePrice: String? // 月平均價
)