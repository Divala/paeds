package com.xtrem.peads_cardiac.ui.main.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.ui.main.home.view.ViewDetailsActivity
import kotlinx.android.synthetic.main.patient_visit_item.view.*

class PatientVisitsAdapter(private val patient: List<Record>) :
    RecyclerView.Adapter<PatientVisitsAdapter.PatientVisitsVH>() {


    class PatientVisitsVH(itemView: View, private val patient: List<Record>) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.tvName
        val phone: TextView? = itemView.tvPhone
        val visitNumber: TextView? = itemView.tvVisitNumber

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ViewDetailsActivity::class.java)

                ViewDetailsActivity.record = patient[adapterPosition]
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientVisitsVH {
        return PatientVisitsVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.patient_visit_item,
                parent,
                false
            ), patient
        )
    }

    override fun getItemCount(): Int {
        return patient.size
    }

    override fun onBindViewHolder(holder: PatientVisitsVH, position: Int) {
        val patientRecord = patient[position]

        holder.name?.text = patientRecord.name
        holder.phone?.text = patientRecord.diagnosisDetails
        holder.visitNumber?.text = patientRecord.visitNumber.toString()

    }
}