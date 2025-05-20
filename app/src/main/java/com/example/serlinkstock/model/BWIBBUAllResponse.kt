package com.example.serlinkstock.model

data class BWIBBUAllResponse(
    val Code: String, // 股票代號
    val Name: String?, // 股票名稱
    val PEratio: String?, // 本益比
    val DividendYield: String?, // 殖利率(%)
    val PBratio: String? // 股價淨值比
)