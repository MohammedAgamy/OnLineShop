package com.example.onlineshop.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel:ViewModel() {

    private val firebaseDatabase=FirebaseDatabase.getInstance()
    private val _banner =MutableLiveData<List<SliderModel>>()
    val banners:LiveData<List<SliderModel>> = _banner

    private val _brand =MutableLiveData<MutableList<BrandModel>>()
    val brandLiveData:LiveData<MutableList<BrandModel>> = _brand

    private val _popuplar =MutableLiveData<MutableList<ItemsModel>>()
    val popuplarLiveData:LiveData<MutableList<ItemsModel>> = _popuplar

    fun loadBanner()
    {
        val ref=firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children)
                {
                    val list =childSnapshot.getValue(SliderModel::class.java)
                    if (list != null)
                    {
                        lists.add(list)
                    }
                    _banner.value =lists
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAGError",error.toString())
            }

        })
    }

    fun loadBrand()
    {
        val ref=firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children)
                {
                    val list =childSnapshot.getValue(BrandModel::class.java)
                    if (list != null)
                    {
                        lists.add(list)
                    }
                    _brand.value =lists
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAGError",error.toString())
            }

        })

    }

    fun loadPopular()
    {
        val ref=firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children)
                {
                    val list =childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null)
                    {
                        lists.add(list)
                    }
                    _popuplar.value =lists
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAGError",error.toString())
            }

        })

    }
}