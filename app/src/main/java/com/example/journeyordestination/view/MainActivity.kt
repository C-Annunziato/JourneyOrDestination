package com.example.journeyordestination.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    var toggle = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()


        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

        val rvHeaderCL: ConstraintLayout = findViewById(R.id.rv_header_constraint_layout)
        val firstEditText: TextInputLayout = findViewById(R.id.first_text_field_layout)
        val secondEditText: TextInputLayout = findViewById(R.id.second_text_field_layout)

        if (toggle) {
            rvHeaderCL.visibility = View.GONE
            firstEditText.visibility = View.GONE
            secondEditText.visibility = View.GONE
            toggle = false
            return true
        } else rvHeaderCL.visibility = View.VISIBLE
        firstEditText.visibility = View.VISIBLE
        secondEditText.visibility = View.VISIBLE
        toggle = true
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}





