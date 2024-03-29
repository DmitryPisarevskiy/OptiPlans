package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.databinding.FragmentUnitBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Unit
import com.example.optiplans.entities.collapseItem
import com.example.optiplans.view.rv.RVUnitBalanceAdapter

class UnitFragment(val streamListener: IStreamClickListener) : Fragment() {
    private lateinit var binding: FragmentUnitBinding
    var currentUnit: Unit? = null
    private var showBalance = true

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUnit = ModelExample.currentUnit
        binding.tvUnitTag.text = currentUnit?.tag?:""
        binding.tvUnitName.text = currentUnit?.name?:""
        currentUnit?.let {
            binding.uvUnit.setColor(it.color)
            binding.rvUnitFeeds.layoutManager = LinearLayoutManager (activity)
            binding.rvUnitFeeds.adapter = RVUnitBalanceAdapter(it, isFeeds = true, ModelExample.currentPeriodNum, streamListener)
            binding.rvUnitProducts.layoutManager = LinearLayoutManager (activity)
            binding.rvUnitProducts.adapter = RVUnitBalanceAdapter(it, isFeeds = false, ModelExample.currentPeriodNum, streamListener)
        }
        val spinnerData = Array<String>(ModelExample.periods.size+1) {""}
        var sum = 0
        for (i in ModelExample.periods.indices) {
            spinnerData[i] = (i+1).toString() + " (" + ModelExample.periods[i] + " дней)"
            sum+=ModelExample.periods[i]
        }
        spinnerData[ModelExample.periods.size] = "Всего " + "(" + sum.toString() + " дней)"
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this.requireContext(),
            android.R.layout.simple_spinner_item, spinnerData)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sUnitPeriod.adapter = arrayAdapter
        binding.sUnitPeriod.prompt = "Выберите период"
        binding.sUnitPeriod.setSelection(ModelExample.currentPeriodNum)
        binding.sUnitPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ModelExample.currentPeriodNum = p2
                currentUnit?.apply {
                    binding.rvUnitProducts.adapter = RVUnitBalanceAdapter(this, isFeeds = false, ModelExample.currentPeriodNum, streamListener)
                    binding.rvUnitFeeds.adapter = RVUnitBalanceAdapter(this, isFeeds = true, ModelExample.currentPeriodNum, streamListener)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.ivUnitBalance.setOnClickListener {
            collapseItem(binding.ivUnitBalance, binding.llUnitBalance, !showBalance)
            showBalance=!showBalance
        }
    }
}