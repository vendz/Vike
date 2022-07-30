package com.vandit.vike.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vandit.vike.CheckoutActivity
import com.vandit.vike.databinding.ItemViewBinding
import com.vandit.vike.models.BikeData

class ExpandableAdapter : ListAdapter<BikeData, ExpandableAdapter.ItemViewHolder>(DiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<BikeData>(){
        override fun areItemsTheSame(oldItem: BikeData, newItem: BikeData): Boolean {
            return oldItem.phoneNo == newItem.phoneNo
        }

        override fun areContentsTheSame(oldItem: BikeData, newItem: BikeData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: BikeData){
            binding.apply {
                listItem = item
                expandableLayout.visibility = if(item.isExpandable) View.VISIBLE else View.GONE
                root.setOnClickListener {
                    item.isExpandable = !item.isExpandable
                    binding.expandableLayout.visibility = if(item.isExpandable) View.VISIBLE else View.GONE
                    notifyItemChanged(adapterPosition)
                }
                submitBTN.setOnClickListener {
                    val mcontext = expandableLayout.context
                    val intent = Intent(mcontext, CheckoutActivity::class.java)
                    intent.putExtra("bikeName", bikeName.text)
                    intent.putExtra("bikePrice", bikeAmount.text)
                    mcontext.startActivity(intent)
                }
            }
        }
    }
}

@BindingAdapter("loadImage")
fun loadImage(item_imageView: ImageView, url: String){
    Glide.with(item_imageView)
        .load(url)
        .into(item_imageView)
}