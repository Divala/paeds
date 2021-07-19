package com.xtrem.peads_cardiac.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import kotlinx.android.synthetic.main.cardiac_eco_item.view.*

class CardiacEcoAdapter(private val data: List<String>) :
    RecyclerView.Adapter<CardiacEcoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardiac_eco_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = data[position]

        holder.name?.text = name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.tvName

    }
}