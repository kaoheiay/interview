package com.example.serlinkstock.model

import java.io.Serializable

data class CombinedItem(
    var stockCode: String,
    var stockName: String? = null,
    var tradeVolume: String? = null,
    var tradeValue: String? = null,
    var openingPrice: String? = null,
    var highestPrice: String? = null,
    var lowestPrice: String? = null,
    var closingPrice: String? = null,
    var change: String? = null,
    var transaction: String? = null,
    var monthlyAveragePrice: String? = null,
    var peRatio: String? = null,
    var dividendYield: String? = null,
    var pbRatio: String? = null
) : Serializable {
    companion object {
        const val NAME = "CombinedItem"
    }
}