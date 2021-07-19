package com.xtrem.peads_cardiac.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.data.records.stats.StatsData

class HomeViewModel : ViewModel() {
    fun todayPatients(token: String, key: String): LiveData<List<Record>> {
        return HomeRepository.getPatients(token, key)
    }

    fun statsSummary(token: String, key: String): LiveData<StatsData>{
        return HomeRepository.getStats(token, key)
    }
}