package com.makhalibagas.newsq.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.makhalibagas.newsq.databinding.ItemNewsBinding
import com.makhalibagas.newsq.domain.model.ArticlesItemModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val listData = ArrayList<ArticlesItemModel>()

    var onClick: ((ArticlesItemModel) -> Unit)? = null

    fun setData(newListData: List<ArticlesItemModel>) {
        val previousContentSize = listData.size
        listData.clear()
        listData.addAll(newListData)
        notifyItemRangeRemoved(0, previousContentSize)
        notifyItemRangeInserted(0, newListData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArticlesItemModel) {
            initView(data)
            initListener(data)
        }

        private fun initView(data: ArticlesItemModel) {
            binding.apply {
                tvNews.text = data.title
                tvNewsDesc.text = data.author
                imgNews.load(data.urlToImage)
            }
        }

        private fun initListener(data: ArticlesItemModel) {
            binding.root.setOnClickListener {
                onClick?.invoke(data)
            }
        }
    }
}