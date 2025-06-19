package com.example.ltmobile_coffeeshop.Adapter

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ltmobile_coffeeshop.databinding.ViewholderOrderBinding
import com.example.ltmobile_coffeeshop.domain.ItemsModel

class MyOrderAdapter(
    private val orderList: ArrayList<ItemsModel>,
    private val context: Context
): RecyclerView.Adapter<MyOrderAdapter.Viewholder>()
{
    class Viewholder(val binding: ViewholderOrderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderAdapter.Viewholder {
        val binding = ViewholderOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: MyOrderAdapter.Viewholder, position: Int) {
        val item = orderList[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.priceItem.text = "${item.price}vnđ"
        holder.binding.totalEachItem.text = "${Math.round(item.numberInCart * item.price)}vnđ"
        holder.binding.numberItemTxt.text = "x${item.numberInCart}"

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picOrder)

    }

    override fun getItemCount(): Int = orderList.size

    fun updateOrderItems(newItems: ArrayList<ItemsModel>) {
        orderList.clear()
        orderList.addAll(newItems)
        notifyDataSetChanged()
    }
}

