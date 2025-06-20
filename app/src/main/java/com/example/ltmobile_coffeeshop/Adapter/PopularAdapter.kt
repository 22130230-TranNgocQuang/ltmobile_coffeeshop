package com.example.ltmobile_coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ltmobile_coffeeshop.Activity.DetailActivity
import com.example.ltmobile_coffeeshop.databinding.ViewholderPopularBinding
import com.example.ltmobile_coffeeshop.domain.ItemsModel
import java.text.NumberFormat
import java.util.Locale

class PopularAdapter(val items:MutableList<ItemsModel>):
    RecyclerView.Adapter<PopularAdapter.Viewholder>( ){
    lateinit var context: Context
    class Viewholder (val binding:ViewholderPopularBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderPopularBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title

        //format giá theo VN
        val formatVND = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        holder.binding.priceTxt.text = formatVND.format(item.price)

//        holder.binding.titleTxt.text=items[position].title
//        holder.binding.priceTxt.text=items[position].price.toString()+"vnđ"

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

}