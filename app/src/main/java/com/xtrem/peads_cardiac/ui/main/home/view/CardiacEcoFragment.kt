package com.xtrem.peads_cardiac.ui.main.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.ui.main.adapters.CardiacEcoAdapter
import kotlinx.android.synthetic.main.fragment_cardiac_eco.*

/**
 * A simple [Fragment] subclass.
 */
class CardiacEcoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardiac_eco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            (activity as ViewDetailsActivity?)?.nextFragment()
        }

        btnBack.setOnClickListener {
            (activity as ViewDetailsActivity?)?.previousFragment()

        }
        showData(ViewDetailsActivity.record)
    }

    private fun showData(record: Record?) {
        tvSitus.text = record?.cardiacEco?.get(0)?.situs
        tvVenousReturn.text = record?.cardiacEco?.get(0)?.venousReturn
        tvGreatVesselRelationsState.text = record?.cardiacEco?.get(0)?.greatVesselRelationsState
        tvGreatVesselRelationsAortic.text = record?.cardiacEco?.get(0)?.greatVesselRelationsAortic
        tvGreatVesselRelationsCoronaries.text =
            record?.cardiacEco?.get(0)?.greatVesselRelationsCoronaries
        tvPericardialEffusion.text = record?.cardiacEco?.get(0)?.pericardialEffusion
        tvOther.text = record?.cardiacEco?.get(0)?.cardiacOther

        val cardiacMeasurements: List<String>? =
            record?.cardiacEco?.get(0)?.cardiacMeasurements?.split(",")?.map { it.trim() }

        rvCardiacMeasurements.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvCardiacMeasurements.adapter = cardiacMeasurements?.let { CardiacEcoAdapter(it) }

        val valvesMorphology: List<String>? =
            record?.cardiacEco?.get(0)?.valvesMorphology?.split(",")?.map { it.trim() }

        rvValvesMorphology.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvValvesMorphology.adapter = valvesMorphology?.let { CardiacEcoAdapter(it) }

        val greatVesselRelationsMeasurements: List<String>? =
            record?.cardiacEco?.get(0)?.greatVesselRelationsMeasurements?.split(",")
                ?.map { it.trim() }

        rvGreatVesselRelationsMeasurements.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvGreatVesselRelationsMeasurements.adapter =
            greatVesselRelationsMeasurements?.let { CardiacEcoAdapter(it) }


    }

}
