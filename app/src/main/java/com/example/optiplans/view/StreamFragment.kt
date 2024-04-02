package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.R
import com.example.optiplans.databinding.FragmentStreamBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.rv.RVStreamProducedAdapter
import com.example.optiplans.view.rv.RVStreamUsedAdapter

class StreamFragment(val unitListener: IUnitClickListener, val commerceListener: ICommerceSwitchListener) : Fragment() {
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
        with (binding) {
            currentStream = ModelExample.currentStream
            tvStreamTag.text = currentStream?.tag ?: ""
            tvStreamName.text = currentStream?.name ?: ""
            currentStream?.apply { svStreamCaption.painting(this.color) }
            var iMax = currentStream?.prices?.size ?: 1
            iMax--
            for (i in 0..iMax) {
                val tvPeriod = TextView(context)
                tvPeriod.text =
                    "Период " + (i + 1).toString() + System.lineSeparator() + ModelExample.periods[i].toString() + " дней"
                tvPeriod.gravity = Gravity.CENTER
                tvPeriod.setTextAppearance(R.style.TableText)
                trStreamSailsPeriod.addView(tvPeriod)
            }
            currentStream?.minBoundsSales?.apply { addTableRow(trStreamSailsMin, this) }
            currentStream?.maxBoundsSales?.apply { addTableRow(trStreamSailsMax, this) }
            currentStream?.prices?.apply { addTableRow(trStreamSailsPrice, this) }
            currentStream?.sails?.apply { addTableRow(trStreamSailsSolution, this) }
            for (i in 0..iMax) {
                val tvPeriod = TextView(context)
                tvPeriod.text =
                    "Период " + (i + 1).toString() + System.lineSeparator() + ModelExample.periods[i].toString() + " дней"
                tvPeriod.gravity = Gravity.CENTER
                tvPeriod.setTextAppearance(R.style.TableText)
                trStreamPurchasesPeriod.addView(tvPeriod)
            }
            currentStream?.minBoundsPurchases?.apply {
                addTableRow(trStreamPurchasesMin,this)
            }
            currentStream?.maxBoundsPurchases?.apply {
                addTableRow(trStreamPurchasesMax,this)
            }
            currentStream?.costs?.apply { addTableRow(trStreamPurchasesPrice, this) }
            currentStream?.purchases?.apply {
                addTableRow(trStreamPurchasesSolution,this)
            }
            currentStream?.apply {
                rvStreamProduced.layoutManager = LinearLayoutManager(activity)
                rvStreamProduced.adapter =
                    RVStreamProducedAdapter(this, ModelExample.currentPeriodNum, unitListener,commerceListener)
                rvStreamUsed.layoutManager = LinearLayoutManager(activity)
                rvStreamUsed.adapter =
                    RVStreamUsedAdapter(this, ModelExample.currentPeriodNum, unitListener,commerceListener)
            }
            val spinnerData = Array<String>(ModelExample.periods.size) { "" }
            var sum = 0
            for (i in ModelExample.periods.indices) {
                spinnerData[i] = (i + 1).toString() + " (" + ModelExample.periods[i] + " дней)"
                sum += ModelExample.periods[i]
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
                R.layout.spinner_item,
                spinnerData
            )
            arrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down)
            sStreamPeriod.adapter = arrayAdapter
            sStreamPeriod.prompt = "Выберите период"
            sStreamPeriod.setSelection(ModelExample.currentPeriodNum)
            sStreamPeriod.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        ModelExample.currentPeriodNum = p2
                        currentStream?.apply {
                            rvStreamProduced.adapter = RVStreamProducedAdapter(
                                this,
                                ModelExample.currentPeriodNum,
                                unitListener,
                                commerceListener
                            )
                            rvStreamUsed.adapter = RVStreamUsedAdapter(
                                this,
                                ModelExample.currentPeriodNum,
                                unitListener,
                                commerceListener
                            )
                        }
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
            if (!showPurchases) {
                collapseItem(ivStreamPurchasesTop,hsvStreamPurchases,showPurchases)
            }
            if (!showSails) {
                collapseItem(ivStreamSeilsTop, hsvStreamSails, showSails)
            }
            if (!showBalance) {
                collapseItem(ivStreamBalance, llStreamBalance, showBalance)
            }
            ivStreamPurchasesTop.setOnClickListener {
                collapseItem(ivStreamPurchasesTop, hsvStreamPurchases,!showPurchases)
                showPurchases = !showPurchases
            }
            tvStreamPurchasesTop.setOnClickListener {
                collapseItem(ivStreamPurchasesTop,hsvStreamPurchases,!showPurchases)
                showPurchases = !showPurchases
            }
            ivStreamSeilsTop.setOnClickListener {
                collapseItem(ivStreamSeilsTop, hsvStreamSails, !showSails)
                showSails = !showSails
            }
            tvStreamSeilsTop.setOnClickListener {
                collapseItem(ivStreamSeilsTop, hsvStreamSails, !showSails)
                showSails = !showSails
            }
            ivStreamBalance.setOnClickListener {
                collapseItem(ivStreamBalance, llStreamBalance, !showBalance)
                showBalance = !showBalance
            }
            tvStreamBalance.setOnClickListener {
                collapseItem(ivStreamBalance, llStreamBalance, !showBalance)
                showBalance = !showBalance
            }
        }
    }

}