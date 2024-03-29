package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.R
import com.example.optiplans.databinding.FragmentStreamBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.collapse
import com.example.optiplans.entities.collapseItem
import com.example.optiplans.entities.expand
import com.example.optiplans.view.rv.RVStreamProducedAdapter
import com.example.optiplans.view.rv.RVStreamUsedAdapter

class StreamFragment(val unitListener: IUnitClickListener) : Fragment() {
    var currentStream: Stream? = null
    lateinit var binding: FragmentStreamBinding
    private var showSails: Boolean = true
    private var showPurchases: Boolean = true
    private var showBalance: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStreamBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentStream = ModelExample.currentStream
        binding.tvStreamTag.text = currentStream?.tag?:""
        binding.tvStreamName.text = currentStream?.name?:""
        currentStream?.apply { binding.svStreamCaption.painting(this.color) }
        var iMax = currentStream?.prices?.size ?: 1
        iMax--
        for (i in 0..iMax) {
            val tvPeriod = TextView(context)
            tvPeriod.text = "Период " + (i+1).toString() + System.lineSeparator() + ModelExample.periods[i].toString() + " дней"
            tvPeriod.gravity = Gravity.CENTER
            tvPeriod.setTextAppearance(R.style.TableText)
            binding.trStreamSailsPeriod.addView(tvPeriod)
        }
        currentStream?.minBoundsSales?.apply { addTableItem(binding.trStreamSailsMin , this)  }
        currentStream?.maxBoundsSales?.apply { addTableItem(binding.trStreamSailsMax , this) }
        currentStream?.prices?.apply { addTableItem(binding.trStreamSailsPrice, this ) }
        currentStream?.sails?.apply { addTableItem(binding.trStreamSailsSolution, this ) }

        for (i in 0..iMax) {
            val tvPeriod = TextView(context)
            tvPeriod.text = "Период " + (i+1).toString() + System.lineSeparator() + ModelExample.periods[i].toString() + " дней"
            tvPeriod.gravity = Gravity.CENTER
            tvPeriod.setTextAppearance(R.style.TableText)
            binding.trStreamPurchasesPeriod.addView(tvPeriod)
        }
        currentStream?.minBoundsPurchases?.apply { addTableItem(binding.trStreamPurchasesMin , this)  }
        currentStream?.maxBoundsPurchases?.apply { addTableItem(binding.trStreamPurchasesMax , this) }
        currentStream?.costs?.apply { addTableItem(binding.trStreamPurchasesPrice, this ) }
        currentStream?.purchases?.apply { addTableItem(binding.trStreamPurchasesSolution, this ) }
        currentStream?.apply {
            binding.rvStreamProduced.layoutManager= LinearLayoutManager(activity)
            binding.rvStreamProduced.adapter = RVStreamProducedAdapter(this, ModelExample.currentPeriodNum, unitListener)
            binding.rvStreamUsed.layoutManager= LinearLayoutManager(activity)
            binding.rvStreamUsed.adapter = RVStreamUsedAdapter(this, ModelExample.currentPeriodNum, unitListener)
        }
        val spinnerData = Array<String>(ModelExample.periods.size+1) {""}
        var sum = 0
        for (i in ModelExample.periods.indices) {
            spinnerData[i] = (i+1).toString() + " (" + ModelExample.periods[i] + " дней)"
            sum+=ModelExample.periods[i]
        }
        spinnerData[ModelExample.periods.size] = "Всего " + "(" + sum.toString() + " дней)"
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this.requireContext(),android.R.layout.simple_spinner_item, spinnerData)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sStreamPeriod.adapter = arrayAdapter
        binding.sStreamPeriod.prompt = "Выберите период"
        binding.sStreamPeriod.setSelection(ModelExample.currentPeriodNum)
        binding.sStreamPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ModelExample.currentPeriodNum = p2
                currentStream?.apply {
                    binding.rvStreamProduced.adapter = RVStreamProducedAdapter(this, ModelExample.currentPeriodNum, unitListener)
                    binding.rvStreamUsed.adapter = RVStreamUsedAdapter(this, ModelExample.currentPeriodNum, unitListener)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        if (!showPurchases) {collapseItem(binding.ivStreamPurchasesTop, binding.hsvStreamPurchases, showPurchases)}
        if (!showSails) {collapseItem(binding.ivStreamSeilsTop, binding.hsvStreamSails, showSails)}
        if (!showBalance) {collapseItem(binding.ivStreamBalance, binding.llStreamBalance, showBalance)}
        binding.ivStreamPurchasesTop.setOnClickListener {
            collapseItem(binding.ivStreamPurchasesTop, binding.hsvStreamPurchases, !showPurchases)
            showPurchases=!showPurchases
        }
        binding.tvStreamPurchasesTop.setOnClickListener {
            collapseItem(binding.ivStreamPurchasesTop, binding.hsvStreamPurchases, !showPurchases)
            showPurchases=!showPurchases
        }
        binding.ivStreamSeilsTop.setOnClickListener {
            collapseItem(binding.ivStreamSeilsTop, binding.hsvStreamSails, !showSails)
            showSails=!showSails
        }
        binding.tvStreamSeilsTop.setOnClickListener {
            collapseItem(binding.ivStreamSeilsTop, binding.hsvStreamSails, !showSails)
            showSails=!showSails
        }
        binding.ivStreamBalance.setOnClickListener {
            collapseItem(binding.ivStreamBalance, binding.llStreamBalance, !showBalance)
            showBalance=!showBalance
        }
        binding.tvStreamBalance.setOnClickListener {
            collapseItem(binding.ivStreamBalance, binding.llStreamBalance, !showBalance)
            showBalance=!showBalance
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun addTableItem(layout: LinearLayout, values: Array<Float>) {
        values.forEach {
            val textView = TextView(context)
            textView.text = if (it>=0) it.toString() else ""
            textView.gravity = Gravity.CENTER
            textView.setTextAppearance(R.style.TableText)
            layout.addView(textView)
        }
    }
}