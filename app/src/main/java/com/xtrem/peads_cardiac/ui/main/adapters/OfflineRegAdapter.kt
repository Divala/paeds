package com.xtrem.peads_cardiac.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.PatientRecord
import kotlinx.android.synthetic.main.patient_item.view.*

class OfflineRegAdapter(private var patientRecord: List<PatientRecord>) :
    RecyclerView.Adapter<OfflineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineViewHolder {
        return OfflineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.patient_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return patientRecord.size
    }

    override fun onBindViewHolder(holder: OfflineViewHolder, position: Int) {
        val patientRecord = patientRecord[position]

        holder.name?.text = patientRecord.name
        holder.phone?.text = patientRecord.phone
    }
}

class OfflineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView? = itemView.tvName
    val phone: TextView? = itemView.tvPhone
}
