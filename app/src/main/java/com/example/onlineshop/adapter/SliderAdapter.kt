package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshop.R
import com.example.onlineshop.model.SliderModel
import com.google.firebase.database.core.Context

class SliderAdapter(private var sliderItems:List<SliderModel>,private val viewPAger2:ViewPager2):RecyclerView.Adapter<SliderAdapter.MyViewHolderSlider>() {

    private lateinit var context: android.content.Context
    private val runnable = Runnable {
        sliderItems =sliderItems
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.MyViewHolderSlider {
       context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_continer,parent,false)
        return MyViewHolderSlider(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.MyViewHolderSlider, position: Int) {
        holder.setImage(sliderItems[position],context)
        if (position ==sliderItems.lastIndex-1)
        {
            viewPAger2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }


    class MyViewHolderSlider(itemView:View):RecyclerView.ViewHolder(itemView) {
        val imageSlider=itemView.findViewById<ImageView>(R.id.imageSlider)

        fun setImage(sliderModel: SliderModel ,context: android.content.Context)
        {
            val requestOption = RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(sliderModel.url)
                .apply(requestOption)
                .into(imageSlider)
        }
    }
}