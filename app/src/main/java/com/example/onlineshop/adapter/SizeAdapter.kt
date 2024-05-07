package com.example.onlineshop.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderColorBinding
import com.example.onlineshop.databinding.ViewholderSizeBinding
import com.example.onlineshop.model.BrandModel
import com.example.onlineshop.model.SliderModel


class SizeAdapter (var brandItems:MutableList<String>): RecyclerView.Adapter<SizeAdapter.MyBrandViHolder>()  {

    private var selectPosition = -1
    private var lastPosition = -1
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBrandViHolder {
        context = parent.context
        val view = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return SizeAdapter.MyBrandViHolder(view)
    }

    override fun getItemCount(): Int  = brandItems.size

    override fun onBindViewHolder(holder: MyBrandViHolder, position: Int) {


        holder.binding.sizeText.text =brandItems[position]

        holder.binding.root.setOnClickListener {
            lastPosition =selectPosition
            selectPosition=position
            notifyItemChanged(lastPosition)
            notifyItemChanged(selectPosition)
        }
        if (selectPosition==position)
        {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.gray_bg_select)

            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.purple))
        }

        else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.black))

        }
    }


    class  MyBrandViHolder(val binding: ViewholderSizeBinding):RecyclerView.ViewHolder(binding.root)
}