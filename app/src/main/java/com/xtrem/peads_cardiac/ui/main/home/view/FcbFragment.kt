package com.xtrem.peads_cardiac.ui.main.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import kotlinx.android.synthetic.main.fragment_fcb.*

/**
 * A simple [Fragment] subclass.
 */
class FcbFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fcb, container, false)
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
        tvWbc.text = record?.fbc?.get(0)?.wbc.toString()
        tvHb.text = record?.fbc?.get(0)?.hb
        tvMVC.text = record?.fbc?.get(0)?.mvc
        tvPlt.text = record?.fbc?.get(0)?.plt
    }

}
