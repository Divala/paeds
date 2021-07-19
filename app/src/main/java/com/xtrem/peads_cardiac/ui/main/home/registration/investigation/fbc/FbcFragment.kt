package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.fbc


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_fbc.*

/**
 * A simple [Fragment] subclass.
 */
class FbcFragment : Fragment() {
    private lateinit var fbcViewModel: FbcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fbc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            val wbc = etWbc.text.toString()
            val hb = etHb.text.toString()
            val plt = etPlt.text.toString()
            val mcv = etMCV.text.toString()
            val chestXray = etXray.text.toString()

            /*if (wbc.isEmpty()) {
                itWbc.error = "Please enter WBC"
                return@setOnClickListener
            }
            if (hb.isEmpty()) {
                itHb.error = "Please enter HB"
                return@setOnClickListener
            }
            if (plt.isEmpty()) {
                itPlt.error = "Please enter Plt"
                return@setOnClickListener
            }
            if (mcv.isEmpty()) {
                itMCV.error = "Please enter MCV"
                return@setOnClickListener
            }
            if (chestXray.isEmpty()) {
                itXray.error = "Please enter Chest Xray"
                return@setOnClickListener
            }*/

            fbcViewModel = ViewModelProvider(this).get(FbcViewModel::class.java)
            fbcViewModel.saveData(wbc, hb, plt, mcv, chestXray)

            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }
}
