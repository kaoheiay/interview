package com.example.serlinkstock.model

data class StockDayAllResponse(
    val Code: String, // 證券代號
    val Name: String, // 證券名稱
    val TradeVolume: String?, // 成交股數
    val TradeValue: String?,  // 成交金額
    val OpeningPrice: String?, // 開盤價
    val HighestPrice: String?, // 最高價
    val LowestPrice: String?, // 最低價
    val ClosingPrice: String?, // 收盤
    val Change: String?,    // 漲跌價差
    val Transaction: String? // 成交筆數
)