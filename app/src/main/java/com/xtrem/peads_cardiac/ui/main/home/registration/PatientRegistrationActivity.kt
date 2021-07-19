package com.xtrem.peads_cardiac.ui.main.home.registration

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayoutMediator
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.MainActivity
import com.xtrem.peads_cardiac.ui.main.adapters.RegistrationPagerAdapter
import kotlinx.android.synthetic.main.activity_patient_registration.*

class PatientRegistrationActivity : AppCompatActivity() {
    private lateinit var adapter: RegistrationPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_registration)

        setSupportActionBar(toolbar as Toolbar?)

        supportActionBar?.title = "Patient Registration"

        setupViewPager()
    }

    private fun setupViewPager() {
        adapter = RegistrationPagerAdapter(fragmentManager = this)

        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabDots, viewPager) { _, _ ->

        }.attach()
    }

    fun nextFragment() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    fun secondFragment() {
        viewPager.currentItem = viewPager.currentItem + 2
    }

    fun previousFragment() {
        viewPager.currentItem = viewPager.currentItem - 1
    }

    fun previousSecondFragment() {
        viewPager.currentItem = viewPager.currentItem - 2
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
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
