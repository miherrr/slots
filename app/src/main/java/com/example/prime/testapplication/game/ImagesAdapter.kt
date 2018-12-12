package com.example.prime.testapplication.game


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.prime.testapplication.R
import org.jetbrains.anko.find

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    private var imageList: List<SlotModel> = listOf()

    fun setList(list: List<SlotModel>) {
        imageList = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var slotImage: ImageView = view.find(R.id.slotImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.slot_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imgItem = imageList[position]

        Glide.with(holder.itemView.context).load(imgItem.image).into(holder.slotImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
