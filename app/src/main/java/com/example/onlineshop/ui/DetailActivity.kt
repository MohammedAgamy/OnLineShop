package com.example.onlineshop.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.adapter.ColorAdapter
import com.example.onlineshop.adapter.SizeAdapter
import com.example.onlineshop.adapter.SliderAdapter
import com.example.onlineshop.databinding.ActivityDetailBinding
import com.example.onlineshop.helper.ManagmentCart
import com.example.onlineshop.model.ItemsModel
import com.example.onlineshop.model.SliderModel

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemsModel: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        managmentCart = ManagmentCart(this)

        getBundle()
        banner()
        iniList()

    }

    private fun iniList() {
        val list = ArrayList<String>()
        for (size in itemsModel.size) {
            list.add(size.toString())
        }

        binding.recSize.adapter = SizeAdapter(list)
        binding.recSize.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val colorList = ArrayList<String>()
        for (picUrl in itemsModel.picUrl) {
            colorList.add(picUrl)
        }

        binding.recColor.adapter = ColorAdapter(colorList)
        binding.recColor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    private fun banner() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in itemsModel.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.slideImageDil.adapter = SliderAdapter(sliderItems, binding.slideImageDil)
        binding.slideImageDil.clipToPadding = false
        binding.slideImageDil.clipChildren = false
        binding.slideImageDil.offscreenPageLimit = 3
        binding.slideImageDil.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        if (sliderItems.size > 1) {
            binding.detIndicator.visibility = View.VISIBLE
            binding.detIndicator.attachTo(binding.slideImageDil)
        }


    }

    private fun getBundle() {
        itemsModel = intent.getParcelableExtra("object")!!
        binding.titelDit.text = itemsModel.title
        binding.des.text = itemsModel.description
        binding.priceDil.text = "$" + itemsModel.price
        binding.rating.text = "${itemsModel.rating} Rating"

        binding.addToCar.setOnClickListener {
            itemsModel.numberInCAr = numberOrder
            managmentCart.insertFood(itemsModel)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.carBtn.setOnClickListener {

        }
    }
}