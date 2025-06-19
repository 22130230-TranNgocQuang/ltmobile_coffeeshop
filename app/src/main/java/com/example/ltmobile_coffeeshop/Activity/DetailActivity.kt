package com.example.ltmobile_coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ltmobile_coffeeshop.R
import com.example.ltmobile_coffeeshop.databinding.ActivityDetailBinding
import com.example.ltmobile_coffeeshop.domain.ItemsModel
import com.example.project1762.Helper.ManagmentCart
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart:ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description

            val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
            priceTxt.text = formatter.format(item.price)

            ratingTxt.text = item.rating.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberItemTxt.text.toString()
                )
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener{
                finish()
            }

            plusCart.setOnClickListener {
//                numberItemTxt.text = (item.numberInCart + 1).toString()
//                item.numberInCart++

                item.numberInCart++
                numberItemTxt.text = item.numberInCart.toString()
            }

            minusCart.setOnClickListener {
                if (item.numberInCart > 1) {
//                    numberItemTxt.text = (item.numberInCart - 1).toString()
//                    item.numberInCart--
                    item.numberInCart--
                    numberItemTxt.text = item.numberInCart.toString()
                }
            }
        }
    }
}