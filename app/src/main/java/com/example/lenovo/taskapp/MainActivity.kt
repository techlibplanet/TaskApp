package com.example.lenovo.taskapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewParent
import com.example.lenovo.taskapp.adapter.CustomerDetailAdapter
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.newsale.NewSaleActivity
import com.example.lenovo.taskapp.paid.PaidFragment
import com.example.lenovo.taskapp.partially.PartiallyFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PartiallyFragment.OnFragmentInteractionListener, PaidFragment.OnFragmentInteractionListener {


    @Inject
    lateinit var database: TaskDatabase
    private lateinit var toolbar : Toolbar
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager
    private lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = find(R.id.viewpager)
        setupViewPager(viewPager)
        tabLayout = find(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        fab = find(R.id.fab)
        fab.setOnClickListener {
            startActivity<NewSaleActivity>()
        }


    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PartiallyFragment(), "Partial")
        adapter.addFragment(PaidFragment(), "Paid")
        viewPager.adapter = adapter
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
