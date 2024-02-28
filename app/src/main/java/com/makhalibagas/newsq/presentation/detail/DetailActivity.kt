package com.makhalibagas.newsq.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.makhalibagas.newsq.R
import com.makhalibagas.newsq.databinding.ActivityDetailBinding
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import com.makhalibagas.newsq.utils.viewBinding

class DetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<ArticlesItemModel>(getString(R.string.app_name))

        binding.apply {
            tvNews.text = data?.title
            tvNewsDesc.text = data?.author
            imgNews.load(data?.urlToImage)
        }

    }
}