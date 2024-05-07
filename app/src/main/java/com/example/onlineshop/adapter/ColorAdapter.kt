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
import com.example.onlineshop.model.BrandModel
import com.example.onlineshop.model.SliderModel


class ColorAdapter (var brandItems:MutableList<String>): RecyclerView.Adapter<ColorAdapter.MyBrandViHolder>()  {

    private var selectPosition = -1
    private var lastPosition = -1
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBrandViHolder {
        context = parent.context
        val view = ViewholderColorBinding.inflate(LayoutInflater.from(context),parent,false)
        return ColorAdapter.MyBrandViHolder(view)
    }

    override fun getItemCount(): Int  = brandItems.size

    override fun onBindViewHolder(holder: MyBrandViHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(brandItems[position])
            .into(holder.binding.picColor)

        holder.binding.root.setOnClickListener {
            lastPosition =selectPosition
            selectPosition=position
            notifyItemChanged(lastPosition)
            notifyItemChanged(selectPosition)
        }
        if (selectPosition==position)
        {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.gray_bg_select)
        }

        else{

            holder.binding.colorLayout.setBackgroundResource(R.drawable.gray_bg)

        }
    }


    class  MyBrandViHolder(val binding: ViewholderColorBinding):RecyclerView.ViewHolder(binding.root)
}