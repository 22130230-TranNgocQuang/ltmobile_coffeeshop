package com.example.ltmobile_coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ltmobile_coffeeshop.Adapter.MyOrderAdapter
import com.example.ltmobile_coffeeshop.databinding.ActivityOrderBinding
import com.example.project1762.Helper.ManagmentCart

class MyOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var managerCart: ManagmentCart
    private lateinit var adapterOrder: MyOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerCart = ManagmentCart(this)
        setupUI()
        receiveOrderData()
        setupOrderList()
    }

    private fun setupUI() {
        binding.backBtnOrder.setOnClickListener {
            finish()
        }
    }

    private fun receiveOrderData() {
        val orderTotal = intent.getStringExtra("order_total") ?: "0"
        val orderStatus = intent.getStringExtra("order_status") ?: "Đang xử lý"
        val itemsCount = intent.getIntExtra("cart_items_count", 0)

        binding.totalOrderTxt.text = "$orderTotal VNĐ"

        binding.statusOrderTxt.text = orderStatus
        when (orderStatus) {
            "Đang xử lý" -> binding.statusOrderTxt.setTextColor(getColor(android.R.color.holo_orange_dark))
            "Đã xác nhận" -> binding.statusOrderTxt.setTextColor(getColor(android.R.color.holo_blue_dark))
            "Đã giao hàng" -> binding.statusOrderTxt.setTextColor(getColor(android.R.color.holo_green_dark))
            "Đã hủy" -> binding.statusOrderTxt.setTextColor(getColor(android.R.color.holo_red_dark))
            else -> binding.statusOrderTxt.setTextColor(getColor(android.R.color.darker_gray))
        }

        binding.orderInfoTxt.text = if (itemsCount > 0) {
            "Có $itemsCount sản phẩm trong đơn hàng"
        } else {
            "Không có sản phẩm nào trong đơn hàng"
        }
    }

    private fun setupOrderList() {
        binding.orderListView.layoutManager = LinearLayoutManager(this)
        binding.orderListView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        val cartItems = managerCart.getListCart()
        if (cartItems.isNotEmpty()) {
            adapterOrder = MyOrderAdapter(cartItems, this)
            binding.orderListView.adapter = adapterOrder
            binding.orderListView.visibility = View.VISIBLE
            binding.orderInfoTxt.text = "Có ${cartItems.size} sản phẩm trong đơn hàng"

            if (intent.getStringExtra("order_total") == null) {
                updateTotalAmount(cartItems)
            }


        } else {
            binding.orderListView.visibility = View.GONE
            binding.orderInfoTxt.text = "Không có sản phẩm nào trong đơn hàng"

        }
    }
    private fun updateTotalAmount(items: ArrayList<com.example.ltmobile_coffeeshop.domain.ItemsModel>) {
        var total = 0.0
        for (item in items) {
            total += item.price * item.numberInCart
        }
        binding.totalOrderTxt.text = "${Math.round(total)} VNĐ"
    }

}