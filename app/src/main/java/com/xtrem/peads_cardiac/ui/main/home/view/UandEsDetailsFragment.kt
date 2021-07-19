package com.xtrem.peads_cardiac.ui.main.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import kotlinx.android.synthetic.main.fragment_uand_es_details.*

/**
 * A simple [Fragment] subclass.
 */
class UandEsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uand_es_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener {
            (activity as ViewDetailsActivity?)?.previousFragment()

        }
        showData(ViewDetailsActivity.record)
    }

    private fun showData(record: Record?) {
        tvK.text = record?.uandes?.get(0)?.k
        tvCa.text = record?.uandes?.get(0)?.ca
        tvNa.text = record?.uandes?.get(0)?.na
        tvCl.text = record?.uandes?.get(0)?.cl
        tvCreatinine.text = record?.uandes?.get(0)?.creatinine
        tvMg.text = record?.uandes?.get(0)?.mg
        tvUrea.text = record?.uandes?.get(0)?.urea
    }

}
