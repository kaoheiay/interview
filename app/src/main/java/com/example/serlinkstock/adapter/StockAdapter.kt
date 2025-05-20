package com.example.serlinkstock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.serlinkstock.databinding.ItemStockBinding
import com.example.serlinkstock.model.CombinedItem

class StockAdapter(private val itemList: List<CombinedItem>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(val binding: ItemStockBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CombinedItem) {
            binding.tvStockCode.text = item.stockCode
            binding.tvStockName.text = item.stockName
            binding.tvOpeningPrice.text = item.openingPrice
            binding.tvHighestPrice.text = item.highestPrice
            binding.tvChange.text = item.change
            binding.tvClosingPrice.text = item.closingPrice
            binding.tvLowestPrice.text = item.lowestPrice
            binding.tvMonthlyAveragePrice.text = item.monthlyAveragePrice
            binding.tvTransaction.text = item.transaction
            binding.tvTradeVolume.text = item.tradeVolume
            binding.tvTradeValue.text = item.tradeValue
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StockViewHolder(
        ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}