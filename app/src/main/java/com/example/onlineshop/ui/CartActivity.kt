package com.example.onlineshop.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.R
import com.example.onlineshop.adapter.CarAdapter
import com.example.onlineshop.databinding.ActivityCartBinding
import com.example.onlineshop.helper.ChangeNumberItemsListener
import com.example.onlineshop.helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var bindCartBinding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    var tax:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindCartBinding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_cart)

        managmentCart= ManagmentCart(this)
        setBack()
        iniCarList()
        calculatorCar()

    }

    private fun iniCarList() {
        bindCartBinding.viewCar.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bindCartBinding.viewCar.adapter=CarAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
               calculatorCar()
            }

        })

        with(bindCartBinding){
            emptyText.visibility=if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scroll2.visibility=if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }


    private fun calculatorCar()
    {
        val percentTax =0.14
        val delivery = 10.0
        tax=Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100

        with(bindCartBinding){
            supToatalPraice.text ="$itemTotal"
            toatalTaxPrice.text ="$tax"
            freeDeliveryPrice.text ="$delivery"
            price.text="$total"
        }
    }

    private fun setBack() {
        bindCartBinding.backBrnCar.setOnClickListener { finish() }
    }
}