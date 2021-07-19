package com.xtrem.peads_cardiac.ui.main.search


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.ui.main.adapters.SearchAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(),
    ServiceListener {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch.setOnClickListener {
            searchViewModel.search(
                "Bearer " + getToken(),
                getKey(),
                getQuery()
            )

            alertDialog = SpotsDialog.Builder().setContext(context)
                .setMessage("Searching...").build().apply { show() }

            searchViewModel.post.observe(viewLifecycleOwner, Observer { data ->
                alertDialog.dismiss()

                showPatients(data)

            })

            searchViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
                alertDialog.dismiss()

                if (message.isNotEmpty()) {
                    context?.let { it1 ->
                        androidx.appcompat.app.AlertDialog.Builder(it1)
                            .setMessage(message)
                            .setTitle("Error")
                            .setNegativeButton("Ok") { dialogInterface, _ ->
                                searchViewModel.errorMessage.postValue("")
                                dialogInterface.cancel()
                            }
                            .show()
                    }
                }
            })
        }


    }

    private fun getQuery(): String {
        return etSearch.text.toString()
    }

    private fun showPatients(records: List<Record>?) {
        alertDialog.dismiss()

        if (records.isNullOrEmpty()) {
            activity?.let {
                androidx.appcompat.app.AlertDialog.Builder(it)
                    .setMessage("No patient found matching your search")
                    .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.dismiss() }
                    .show()
            }
            return
        }
        patientsRv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        patientsRv.adapter = SearchAdapter(records)
    }

    private fun getKey(): String {
        return AppConfig.APP_SECRET
    }

    private fun getToken(): String {
        return activity?.let {
            SharedPreferenceManager(it).retrievePreference(
                AppPrefKeys.ACCESS_TOKEN,
                ""
            )
        }
            .toString()
    }

    override fun onStarted() {
        alertDialog.show()
    }

    override fun onFailure(message: String) {
        alertDialog.dismiss()

        activity?.let {
            androidx.appcompat.app.AlertDialog.Builder(it).setMessage(message).setTitle("Error")
                .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }.show()
        }
    }
}
