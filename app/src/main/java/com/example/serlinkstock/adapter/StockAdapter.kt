package com.example.serlinkstock.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.serlinkstock.R
import com.example.serlinkstock.databinding.ItemStockBinding
import com.example.serlinkstock.model.CombinedItem
import com.example.serlinkstock.ui.OtherInfoDialog

class StockAdapter(private val itemList: List<CombinedItem>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

        var onItemClicked : ((item : CombinedItem) -> Unit)? = null
    fun setOnItemClickedListener(onItemClicked: (item : CombinedItem) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StockViewHolder(
        ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

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

            val mClosingPrice = getTurnedPrice(item.closingPrice)
            val mMonthlyAveragePrice = getTurnedPrice(item.monthlyAveragePrice)
            val mChange = getTurnedPrice(item.change)

            when {
                mClosingPrice > mMonthlyAveragePrice -> {
                    binding.tvClosingPrice.setTextColor(binding.root.context.getColor(android.R.color.holo_red_light))
                }

                mClosingPrice < mMonthlyAveragePrice -> {
                    binding.tvClosingPrice.setTextColor(binding.root.context.getColor(android.R.color.holo_green_light))
                }

                else -> {
                    binding.tvClosingPrice.setTextColor(binding.root.context.getColor(R.color.gray_90))
                }
            }

            when {
                mChange > 0 -> {
                    binding.tvChange.setTextColor(binding.root.context.getColor(android.R.color.holo_red_light))
                }

                mChange < 0 -> {
                    binding.tvChange.setTextColor(binding.root.context.getColor(android.R.color.holo_green_light))
                }

                else -> {
                    binding.tvChange.setTextColor(binding.root.context.getColor(R.color.gray_90))
                }
            }

            binding.root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }
}

private fun getTurnedPrice(price: String?): Double =
    ((if (price.isNullOrEmpty()) "0" else price).toDouble())