package com.example.ltmobile_coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.media.Rating
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ltmobile_coffeeshop.Activity.DetailActivity
import com.example.ltmobile_coffeeshop.databinding.ViewholderItemPicLeftBinding
import com.example.ltmobile_coffeeshop.databinding.ViewholderItemPicRightBinding
import com.example.ltmobile_coffeeshop.domain.ItemsModel
import java.text.NumberFormat
import java.util.Locale

class ItemsListCategoryAdapter(val items:MutableList<ItemsModel>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            const val TYPE_ITEM1 = 0
            const val TYPE_ITEM2 = 1
        }

    lateinit var context: Context
    private val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))

    override fun getItemViewType(position: Int): Int {
        return if (position%2==0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewholderItem1(val binding: ViewholderItemPicRightBinding):
    RecyclerView.ViewHolder(binding.root)

    class ViewholderItem2(val binding: ViewholderItemPicLeftBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            TYPE_ITEM1 -> {
                val binding=ViewholderItemPicRightBinding.inflate(
                    LayoutInflater.from(context),
                    parent, false
                )
                ViewholderItem1(binding)
            }
            TYPE_ITEM2 -> {
                val binding=ViewholderItemPicLeftBinding.inflate(
                    LayoutInflater.from(context),
                    parent, false
                )
                ViewholderItem2(binding)
            }

            else -> throw IllegalArgumentException("Invalidview type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        fun bindCommonData(
            titleTxt:String,
            priceTxt:String,
            rating:Float,
            picUrl:String
        ){
            when(holder){
                is ViewholderItem1 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", item)
                        context.startActivity(intent)
                    }
                }

                is ViewholderItem2 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", item)
                        context.startActivity(intent)
                    }
                }
            }
        }

        val formattedPrice = formatter.format(item.price)

        bindCommonData(
            item.title,
            formattedPrice,
            item.rating.toFloat(),
            item.picUrl[0]
        )
    }
}