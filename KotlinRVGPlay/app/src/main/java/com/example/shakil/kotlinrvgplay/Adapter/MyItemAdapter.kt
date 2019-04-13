package com.example.shakil.kotlinrvgplay.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.shakil.kotlinrvgplay.Model.ItemData
import android.widget.TextView
import com.example.shakil.kotlinrvgplay.Interface.IItemClickListener
import com.example.shakil.kotlinrvgplay.R
import android.widget.Toast
import com.squareup.picasso.Picasso

class MyItemAdapter(private val context: Context, private val itemList:List<ItemData>?):RecyclerView.Adapter<MyItemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.txt_title.text = itemList!![p1].name
        Picasso.get().load(itemList[p1].image).into(p0.img_item)

        p0.setiItemClickListener(object : IItemClickListener{
            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context, "" + itemList[p1].name, Toast.LENGTH_SHORT).show();
            }

        })
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_title: TextView
        var img_item: ImageView

        lateinit var iItemClickListener: IItemClickListener

        fun setiItemClickListener(iItemClickListener: IItemClickListener) {
            this.iItemClickListener = iItemClickListener
        }

        init {
            txt_title = view.findViewById(R.id.tvTitle) as TextView
            img_item = view.findViewById(R.id.itemImage) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iItemClickListener.onItemClickListener(v!!, adapterPosition)
        }

    }
}