package com.makhalibagas.newsq.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.makhalibagas.newsq.R
import com.makhalibagas.newsq.databinding.ActivityMainBinding
import com.makhalibagas.newsq.presentation.detail.DetailActivity
import com.makhalibagas.newsq.state.UiStateWrapper
import com.makhalibagas.newsq.utils.collectLifecycleFlow
import com.makhalibagas.newsq.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        adapter = MainAdapter()
        binding.rvNews.adapter = adapter
    }

    private fun initListener() {
        adapter.onClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(getString(R.string.app_name), it)
            startActivity(intent)
        }
    }

    private fun initObserver() {
        viewModel.getNews()
        collectLifecycleFlow(viewModel.news) { state ->
            when (state) {
                is UiStateWrapper.Loading -> binding.pb.visibility = View.VISIBLE
                is UiStateWrapper.Success -> {
                    binding.pb.visibility = View.GONE
                    adapter.setData(state.data)
                }
                is UiStateWrapper.Error -> binding.pb.visibility = View.GONE
            }
        }
    }

}