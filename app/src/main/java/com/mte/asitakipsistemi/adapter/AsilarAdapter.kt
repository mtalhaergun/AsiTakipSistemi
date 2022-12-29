package com.mte.asitakipsistemi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mte.asitakipsistemi.R
import com.mte.asitakipsistemi.api.MyDataItem
import com.mte.asitakipsistemi.databinding.AsilarRecyclerViewBinding

class AsilarAdapter (private val context : Context, val postList:List<MyDataItem>): RecyclerView.Adapter<AsilarAdapter.PostHolder>() {

    class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding= AsilarRecyclerViewBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):PostHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.asilar_recycler_view,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    override fun onBindViewHolder(holder:PostHolder,position:Int){
        holder.binding.asiIsim.text=postList[position].isim
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
        Glide.with(context)
            .load(postList[position].gorsel)
            .apply(requestOptions)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(holder.binding.cardImage)

        holder.itemView.setOnClickListener {

        }
    }
}