package com.example.journeyordestination.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var toggleDest = true
    private var toggleHelp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // find toolbar and set its background color
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.background = getDrawable(R.color.blue_grey_med)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        // add toolbar in place of generic actionbar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // open and close header if car icon is selected
        val rvHeaderCL: ConstraintLayout = findViewById(R.id.rv_header_constraint_layout)
        if (toggleDest && item.itemId == R.id.menu_add_contact) {
            rvHeaderCL.visibility = View.VISIBLE
        } else if(item.itemId == R.id.menu_add_contact) rvHeaderCL.visibility = View.GONE

        toggleDest = !toggleDest

        //show and hide help directions if help icon is selected
        val tv: TextView = findViewById(R.id.how_to_use_app_text_view)
        if(toggleHelp && item.itemId == R.id.menu_help_item){
            tv.visibility = View.VISIBLE
        } else if(item.itemId == R.id.menu_help_item) tv.visibility = View.GONE
        toggleHelp = !toggleHelp

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}





