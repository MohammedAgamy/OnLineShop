package com.example.onlineshop.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshop.databinding.ViewholderRecommendedBinding
import com.example.onlineshop.model.ItemsModel
import com.example.onlineshop.ui.DetailActivity

class PopularAdapter(var brandItems: MutableList<ItemsModel>) :
    RecyclerView.Adapter<PopularAdapter.MyPopularViHolder>() {
    class MyPopularViHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)


    var context: android.content.Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPopularViHolder {
        context = parent.context
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyPopularViHolder(binding)
    }

    override fun getItemCount(): Int = brandItems.size
    override fun onBindViewHolder(holder: MyPopularViHolder, position: Int) {
        holder.binding.titlText.text = brandItems[position].title
        holder.binding.priceText.text = "$"+brandItems[position].price.toString()
        holder.binding.textReting.text = brandItems[position].rating.toString()
        holder.binding.textReting.text = brandItems[position].rating.toString()

        val requestOption = RequestOptions().transform(CenterInside())
        Glide.with(context!!)
            .load(brandItems[position].picUrl[0])
            .apply(requestOption)
            .into(holder.binding.picRecommend)

        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object", brandItems[position])
            holder.itemView.context.startActivity(intent)
        }



    }


}