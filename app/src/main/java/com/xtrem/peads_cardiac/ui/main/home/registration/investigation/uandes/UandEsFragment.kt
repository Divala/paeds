package com.xtrem.peads_cardiac.ui.main.home.registration.investigation.uandes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_uand_es.*

/**
 * A simple [Fragment] subclass.
 */
class UandEsFragment : Fragment() {
    private lateinit var uandEsViewModel: UandEsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uand_es, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            val na = etNa.text.toString()
            val k = etK.text.toString()
            val cl = etCl.text.toString()
            val mg = etMg.text.toString()
            val ca = etCa.text.toString()
            val urea = etUrea.text.toString()
            val creatine = etCreatinine.text.toString()
            val pvc = etPVC.text.toString()

         /*   if (na.isEmpty()) {
                itNa.error = "Please enter Na"
                return@setOnClickListener
            }
            if (k.isEmpty()) {
                itK.error = "Please enter K"
                return@setOnClickListener
            }
            if (cl.isEmpty()) {
                itCl.error = "Please enter Cl"
                return@setOnClickListener
            }
            if (mg.isEmpty()) {
                itMg.error = "Please enter Mg"
                return@setOnClickListener
            }
            if (ca.isEmpty()) {
                itCa.error = "Pleas enter Ca"
                return@setOnClickListener
            }
            if (urea.isEmpty()) {
                itUrea.error = "Please enter Urea"
                return@setOnClickListener
            }
            if (creatine.isEmpty()) {
                itCreatinine.error = "Please enter Creatine"
                return@setOnClickListener
            }
            if (pvc.isEmpty()) {
                itPVC.error = "Please enter PVC"
                return@setOnClickListener
            }*/

            uandEsViewModel = ViewModelProvider(this).get(UandEsViewModel::class.java)
            uandEsViewModel.saveData(na, k, cl, mg, ca, urea, creatine, pvc)

            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }
}
