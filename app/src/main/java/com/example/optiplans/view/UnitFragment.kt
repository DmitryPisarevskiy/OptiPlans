package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.R
import com.example.optiplans.databinding.FragmentUnitBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Unit
import com.example.optiplans.view.rv.RVUnitBalanceAdapter

class UnitFragment(val streamListener: IStreamClickListener) : Fragment() {
    private lateinit var binding: FragmentUnitBinding
    var currentUnit: Unit? = null
    private var showBalance = true
    private var showCaps = true
    private var showRegimes = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnitBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            currentUnit = ModelExample.currentUnit
            tvUnitTag.text = currentUnit?.tag?:""
            tvUnitName.text = currentUnit?.name?:""
            currentUnit?.let {
                uvUnit.setColor(it.color)
                uvUnitCaption.setColor(it.color)
                rvUnitFeeds.layoutManager = LinearLayoutManager (activity)
                rvUnitFeeds.adapter = RVUnitBalanceAdapter(it, true, ModelExample.currentPeriodNum, streamListener)
                rvUnitProducts.layoutManager = LinearLayoutManager (activity)
                rvUnitProducts.adapter = RVUnitBalanceAdapter(it, false, ModelExample.currentPeriodNum, streamListener)
            }
            val spinnerData = Array<String>(ModelExample.periods.size) {""}
            var sum = 0
            for (i in ModelExample.periods.indices) {
                spinnerData[i] = (i+1).toString() + " (" + ModelExample.periods[i] + " дней)"
                sum+=ModelExample.periods[i]
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),
                R.layout.spinner_item, spinnerData)
            arrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down)
            sUnitPeriod.adapter = arrayAdapter
            sUnitPeriod.prompt = "Выберите период"
            sUnitPeriod.setSelection(ModelExample.currentPeriodNum)
            sUnitPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    ModelExample.currentPeriodNum = p2
                    currentUnit?.apply {
                        rvUnitProducts.adapter = RVUnitBalanceAdapter(this, isFeeds = false, ModelExample.currentPeriodNum, streamListener)
                        rvUnitFeeds.adapter = RVUnitBalanceAdapter(this, isFeeds = true, ModelExample.currentPeriodNum, streamListener)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            ivUnitBalance.setOnClickListener {
                collapseItem(ivUnitBalance, llUnitBalance, !showBalance)
                showBalance=!showBalance
            }
            tvUnitBalance.setOnClickListener {
                collapseItem(ivUnitBalance, llUnitBalance, !showBalance)
                showBalance=!showBalance
            }
            currentUnit?.let {
                addTablePeriodRow(trUnitCapsPeriod)
                addCapsItems(tlUnitCaps, it)
            }
            ivUnitCaps.setOnClickListener {
                collapseItem(ivUnitCaps, hsvUnitCaps, !showCaps)
                showCaps=!showCaps
            }
            tvUnitCaps.setOnClickListener {
                collapseItem(ivUnitCaps, hsvUnitCaps, !showCaps)
                showCaps=!showCaps
            }
            currentUnit?.let {
                addTablePeriodRow(trUnitRegimesPeriod)
                addRegimesItems(tlUnitRegimes, it)
            }
            ivUnitRegimes.setOnClickListener {
                collapseItem(ivUnitRegimes, hsvUnitRegimes, !showRegimes)
                showRegimes=!showRegimes
            }
            tvUnitRegimes.setOnClickListener {
                collapseItem(ivUnitRegimes, hsvUnitRegimes, !showRegimes)
                showRegimes=!showRegimes
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun addCapsItems(table: TableLayout, unit: Unit) {
        unit.capacities.forEach {
            val trUnitCapName = TableRow(requireContext())
            val tvUnitCapName = TextView(requireContext())
            tvUnitCapName.text = it.name
            tvUnitCapName.setTextAppearance(R.style.TableCaption)
            trUnitCapName.addView(tvUnitCapName)
            table.addView(trUnitCapName)
            val trUnitCapMin = TableRow(requireContext())
            val tvUnitCapMin = TextView(requireContext())
            tvUnitCapMin.text = getString(R.string.min)
            tvUnitCapMin.setTextAppearance(R.style.TableText)
            trUnitCapMin.addView(tvUnitCapMin)
            table.addView(trUnitCapMin)
            addTableRow(trUnitCapMin, it.minBounds)
            val trUnitCapMax = TableRow(requireContext())
            val tvUnitCapMax = TextView(requireContext())
            tvUnitCapMax.text = getString(R.string.max)
            tvUnitCapMax.setTextAppearance(R.style.TableText)
            trUnitCapMax.addView(tvUnitCapMax)
            table.addView(trUnitCapMax)
            addTableRow(trUnitCapMax, it.maxBounds)
            val trUnitCapSolution = TableRow(requireContext())
            val tvUnitCapSolution = TextView(requireContext())
            tvUnitCapSolution.text = getString(R.string.solution)
            tvUnitCapSolution.setTextAppearance(R.style.TableText)
            trUnitCapSolution.addView(tvUnitCapSolution)
            trUnitCapSolution.setBackgroundColor(resources.getColor(R.color.light_gray))
            table.addView(trUnitCapSolution)
            addTableRow(trUnitCapSolution, it.activities)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun addRegimesItems(table: TableLayout, unit: Unit) {
        unit.regimes.forEach {
            val tr = TableRow(requireContext())
            val tv = TextView(requireContext())
            tv.text = it.name
            tv.setTextAppearance(R.style.TableCaption)
            tr.addView(tv)
            table.addView(tr)
            addTableRow(tr, it.activities)
        }
    }
}