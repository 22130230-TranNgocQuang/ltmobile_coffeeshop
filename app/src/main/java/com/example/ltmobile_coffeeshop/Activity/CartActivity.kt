package com.example.ltmobile_coffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ltmobile_coffeeshop.Adapter.CartAdapter
import com.example.ltmobile_coffeeshop.R
import com.example.ltmobile_coffeeshop.databinding.ActivityCartBinding
import com.example.ltmobile_coffeeshop.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ManagmentCart
import com.uilover.project195.Helper.ChangeNumberItemsListener

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        binding.apply {
            listView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object :ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }

                }
            )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{ finish() }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100
        binding.apply {
            totalFeeTxt.text = "${itemTotal}vnđ"
            taxTxt.text = "${tax}vnđ"
            deliveryTxt.text = "${delivery}vnđ"
            totalTxt.text = "${total}vnđ"
        }
    }
}