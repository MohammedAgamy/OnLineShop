package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshop.databinding.ViewholderCarBinding
import com.example.onlineshop.databinding.ViewholderSizeBinding
import com.example.onlineshop.helper.ChangeNumberItemsListener
import com.example.onlineshop.helper.ManagmentCart
import com.example.onlineshop.model.ItemsModel

class CarAdapter(
    private val itemList: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CarAdapter.ViewHolderCar>() {

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCar {

        val binding =
            ViewholderCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCar(binding)
    }

    override fun getItemCount(): Int = itemList.size
    override fun onBindViewHolder(holder: ViewHolderCar, position: Int) {
        val item = itemList[position]
        holder.binding.title.text = item.title
        holder.binding.FeeEachItem.text = "$${item.price}"
        holder.binding.totleEachPriceitem.text = "$${Math.round(item.numberInCAr * item.price)}"
        holder.binding.numberItemText.text = item.numberInCAr.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCar)


        holder.binding.plusCarBtn.setOnClickListener {
            managmentCart.plusItem(itemList, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }


        holder.binding.minusCarBtn.setOnClickListener {
            managmentCart.minusItem(itemList, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }

    class ViewHolderCar(val binding: ViewholderCarBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}