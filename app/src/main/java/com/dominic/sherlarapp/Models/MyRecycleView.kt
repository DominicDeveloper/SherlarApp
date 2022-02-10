package com.dominic.sherlarapp.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dominic.sherlarapp.R
import kotlinx.android.synthetic.main.item_poem.view.*

class MyRecycleView(val list:List<Poem>,val click: Click): RecyclerView.Adapter<MyRecycleView.VH>() {

    inner class VH(itemView:View):RecyclerView.ViewHolder(itemView){
        fun Bind(poem: Poem){
            itemView.text_poemname.text = poem._poemName
            itemView.text_poeminfo.text = poem._poemInfo
            itemView.setOnClickListener {
               click.onClick(poem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_poem,parent,false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.Bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface Click{
        fun onClick(poem: Poem)
    }
}