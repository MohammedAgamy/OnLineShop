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
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.model.BrandModel
import com.example.onlineshop.model.SliderModel

class BrandAdapter (var brandItems:List<BrandModel>): RecyclerView.Adapter<BrandAdapter.MyBrandViHolder>()  {

    private var selectPosition = -1
    private var lastPosition = -1
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBrandViHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_brand,parent,false)
        return BrandAdapter.MyBrandViHolder(view)
    }

    override fun getItemCount(): Int  = brandItems.size

    override fun onBindViewHolder(holder: MyBrandViHolder, position: Int) {
        val  item = brandItems[position]
        holder.titel.text =item.title

        Glide.with(context)
            .load(item.picUrl)
            .into(holder.imageBrand)

        holder.itemView.setOnClickListener {
            lastPosition =selectPosition
            selectPosition=position
            notifyItemChanged(lastPosition)
            notifyItemChanged(selectPosition)
        }

        holder.titel.setTextColor(context.resources.getColor(R.color.white))
        if (selectPosition==position)
        {
            holder.imageBrand.setBackgroundColor(0)
            holder.linearBrand.setBackgroundResource(R.color.purple)
            ImageViewCompat.setImageTintList(holder.imageBrand, ColorStateList.valueOf(context.getColor(R.color.white)))
            holder.titel.visibility =View.VISIBLE
        }

        else{

            holder.imageBrand.setBackgroundColor(R.drawable.gray_bg)
            holder.linearBrand.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.imageBrand, ColorStateList.valueOf(context.getColor(R.color.black)))
            holder.titel.visibility =View.GONE
        }
    }


    class  MyBrandViHolder(val itemView: View):RecyclerView.ViewHolder(itemView){
        val titel =itemView.findViewById<TextView>(R.id.titelBrand)
        val imageBrand =itemView.findViewById<ImageView>(R.id.imageBrand)
        val linearBrand =itemView.findViewById<LinearLayout>(R.id.linearBrand)
    }
}