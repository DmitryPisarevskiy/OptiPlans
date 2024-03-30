package com.example.optiplans.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.optiplans.R
import com.example.optiplans.databinding.ActivityMainBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.Unit

class MainActivity : AppCompatActivity(), IStreamClickListener, IUnitClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment(this,this))
                R.id.schema -> replaceFragment(SchemaFragment())
                R.id.table -> replaceFragment(TableFragment(this))
                R.id.unit -> replaceFragment(UnitFragment(this))
                R.id.stream -> replaceFragment(StreamFragment(this))
                else -> {}
            }
            true
        }
        replaceFragment(HomeFragment(this,this))
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStreamClick(stream: Stream) {
        ModelExample.currentStream = stream
        binding.bottomNav.selectedItemId = R.id.stream
    }

    override fun onUnitClick(unit: Unit) {
        ModelExample.currentUnit = unit
        binding.bottomNav.selectedItemId = R.id.unit
    }
}