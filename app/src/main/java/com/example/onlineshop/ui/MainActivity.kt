package com.example.onlineshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshop.R
import com.example.onlineshop.adapter.SliderAdapter
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.model.MainViewModel
import com.example.onlineshop.model.SliderModel

class MainActivity : AppCompatActivity() {
    val mainViewModel =MainViewModel()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniBanner()



    }
    private fun iniBanner() {
        binding.prograsBarSlider.visibility=View.VISIBLE
        mainViewModel.banners.observe(this , Observer { items ->
            banner(items)
            binding.prograsBarSlider.visibility =View.VISIBLE
        })

        mainViewModel.loadBanner()
    }

    private fun banner(image:List<SliderModel>){

            binding.viewPAgerSlider.adapter =SliderAdapter(image,binding.viewPAgerSlider)
            binding.viewPAgerSlider.clipToPadding =false
            binding.viewPAgerSlider.clipChildren =false
            binding.viewPAgerSlider.offscreenPageLimit =3
            binding.viewPAgerSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransform =CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            }

            binding.viewPAgerSlider.setPageTransformer(compositePageTransform)
            if (image.size>1)
            {
                binding.dotsIndicator.visibility =View.VISIBLE
                binding.dotsIndicator.attachTo(binding.viewPAgerSlider)
            }


    }

}