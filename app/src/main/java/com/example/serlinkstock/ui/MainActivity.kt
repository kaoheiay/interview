package com.example.serlinkstock.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.serlinkstock.R
import com.example.serlinkstock.adapter.StockAdapter
import com.example.serlinkstock.databinding.ActivityMainBinding
import com.example.serlinkstock.network.RetrofitClient
import com.example.serlinkstock.viewmodel.StockViewModel
import com.example.serlinkstock.viewmodel.StockViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: StockViewModel by viewModels {
        StockViewModelFactory(RetrofitClient.stockApiService)
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加載佈局並獲取綁定物件
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 讓內容延伸到狀態列後面
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            // 將系統列 (包括狀態列和導覽列) 的空間應用為根 View 的 Padding
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom)

            // 返回未被消費的 Insets，以便子 View 也能處理它們
            WindowInsetsCompat.CONSUMED // 或者直接返回 windowInsets，如果子 View 也需要處理
        }

        recyclerView = binding.recyclerView

        viewModel.combinedItemList.observe(this, Observer { itemList ->
            adapter = StockAdapter(itemList)
            recyclerView.adapter = adapter
        })
    }
}