package com.xtrem.peads_cardiac.ui.main.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.ui.main.home.visits.PatientVisitsActivity
import kotlinx.android.synthetic.main.patient_item.view.*

class PatientDetailsAdapter(private val patient: List<Record>) :
    RecyclerView.Adapter<PatientDetailsAdapter.PatientVH>() {


    class PatientVH(itemView: View, private val patient: List<Record>) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.tvName
        val phone: TextView? = itemView.tvPhone

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PatientVisitsActivity::class.java)

                intent.putExtra("PATIENT_ID", patient[adapterPosition].id)
                intent.putExtra("PATIENT_NAME", patient[adapterPosition].name)

                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientVH {
        return PatientVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.patient_item,
                parent,
                false
            ), patient
        )
    }

    override fun getItemCount(): Int {
        return patient.size
    }

    override fun onBindViewHolder(holder: PatientVH, position: Int) {
        val patientRecord = patient[position]

        holder.name?.text = patientRecord.name
        holder.phone?.text = patientRecord.phone
    }
}