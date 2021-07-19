package com.xtrem.peads_cardiac.ui.main.home.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.ui.main.adapters.ViewDetailsPagerAdapter
import kotlinx.android.synthetic.main.activity_view_details.*

class ViewDetailsActivity : AppCompatActivity() {
    private lateinit var adapter: ViewDetailsPagerAdapter

    companion object {
        var record: Record? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details)

        setSupportActionBar(toolbar as Toolbar?)

        supportActionBar?.title = "Patient Details"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewPager()
    }

    private fun setupViewPager() {
        adapter = ViewDetailsPagerAdapter(fragmentManager = this)

        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
    }

    fun nextFragment() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    fun previousFragment() {
        viewPager.currentItem = viewPager.currentItem - 1
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem != 0) {
            viewPager.setCurrentItem(viewPager.currentItem - 1, false)
        } else {
            finish()
        }

    }

}
