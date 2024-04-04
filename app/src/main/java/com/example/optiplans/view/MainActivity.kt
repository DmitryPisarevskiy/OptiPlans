package com.example.optiplans.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.optiplans.R
import com.example.optiplans.databinding.ActivityMainBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.QuantityMeasure
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.Unit

class MainActivity : AppCompatActivity(), IStreamClickListener, IUnitClickListener, ICommerceSwitchListener {
    private lateinit var binding: ActivityMainBinding
    private var periodListener: IPeriodListener? = null
    private var measureListener: IMeasureListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with (binding) {
            setContentView(root)
            setSupportActionBar(toolbar)
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> replaceFragment(HomeFragment(this@MainActivity,this@MainActivity))
//                R.id.schema -> replaceFragment(SchemaFragment())
                    R.id.commerce -> replaceFragment(CommerceFragment(ModelExample, true,this@MainActivity, this@MainActivity))
                    R.id.table -> replaceFragment(TableFragment(this@MainActivity))
                    R.id.unit -> replaceFragment(UnitFragment(this@MainActivity))
                    R.id.stream -> replaceFragment(StreamFragment(this@MainActivity,this@MainActivity))
                    else -> {}
                }
                true
            }
            val periodSpinnerData = Array<String>(ModelExample.periods.size) {""}
            var sum = 0
            for (i in ModelExample.periods.indices) {
                periodSpinnerData[i] = "Период " + (i+1).toString() + " (" + ModelExample.periods[i] + " дней)"
                sum+=ModelExample.periods[i]
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MainActivity,
                R.layout.spinner_item, periodSpinnerData)
            arrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down)
            sPeriod.adapter = arrayAdapter
            sPeriod.setSelection(ModelExample.currentPeriodNum)
            sPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    ModelExample.currentPeriodNum = p2
                    if (periodListener!=null) {
                        periodListener!!.onPeriodSpinnerChange(p2)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            val measureSpinnerData = arrayOf(ModelExample.quantityUnit + "/" + ModelExample.timeUnit, ModelExample.quantityUnit)
            val measureArrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MainActivity,
                R.layout.spinner_item, measureSpinnerData)
            measureArrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down)
            sMeasure.adapter = measureArrayAdapter
            if (ModelExample.measure==QuantityMeasure.PER_TIME_UNIT) {
                sMeasure.setSelection(0)
            } else {
                sMeasure.setSelection(1)
            }

            sMeasure.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    ModelExample.measure = if (p2==0) {QuantityMeasure.PER_TIME_UNIT} else {QuantityMeasure.PER_PERIOD}
                    if (measureListener!=null) {
                        measureListener!!.onMeasureSpinnerChange(ModelExample.measure)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            replaceFragment(HomeFragment(this@MainActivity,this@MainActivity))
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
        if (fragment is IPeriodListener) {
            periodListener = fragment
        }
        if (fragment is IMeasureListener) {
            measureListener = fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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

    override fun onCommerceSwitchClick(purchasesIsChecked: Boolean) {
        replaceFragment(CommerceFragment(ModelExample, purchasesIsChecked, this,this))
    }
}