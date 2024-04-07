package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class StreamFragment(val unitListener: IUnitClickListener, val commerceListener: ICommerceSwitchListener) : Fragment(), IPeriodListener {
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
                "Период ${(i + 1)}${System.lineSeparator()}${ModelExample.periods[i]} дней".also { tvPeriod.text = it }
                tvPeriod.gravity = Gravity.CENTER
                tvPeriod.setTextAppearance(R.style.TableText)
                trStreamSailsPeriod.addView(tvPeriod)
            }
            currentStream?.minBoundsSales?.apply { addTableRow(trStreamSailsMin, this, ModelExample) }
            currentStream?.maxBoundsSales?.apply { addTableRow(trStreamSailsMax, this, ModelExample) }
            currentStream?.prices?.apply { addTableRow(trStreamSailsPrice, this, ModelExample, true) }
            currentStream?.sails?.apply { addTableRow(trStreamSailsSolution, this, ModelExample) }
            for (i in 0..iMax) {
                val tvPeriod = TextView(context)
                "Период ${(i + 1)}${System.lineSeparator()}${ModelExample.periods[i]} дней".also { tvPeriod.text = it }
                tvPeriod.gravity = Gravity.CENTER
                tvPeriod.setTextAppearance(R.style.TableText)
                trStreamPurchasesPeriod.addView(tvPeriod)
            }
            currentStream?.minBoundsPurchases?.apply {
                addTableRow(trStreamPurchasesMin,this, ModelExample)
            }
            currentStream?.maxBoundsPurchases?.apply {
                addTableRow(trStreamPurchasesMax,this, ModelExample)
            }
            currentStream?.costs?.apply { addTableRow(trStreamPurchasesPrice, this, ModelExample, true) }
            currentStream?.purchases?.apply {
                addTableRow(trStreamPurchasesSolution,this, ModelExample)
            }
            currentStream?.apply {
                rvStreamProduced.layoutManager = LinearLayoutManager(activity)
                rvStreamProduced.adapter = RVStreamProducedAdapter(
                    this,
                    ModelExample.currentPeriodNum,
                    ModelExample.measure,
                    unitListener,
                    commerceListener
                )
                rvStreamUsed.layoutManager = LinearLayoutManager(activity)
                rvStreamUsed.adapter = RVStreamUsedAdapter(this, ModelExample.currentPeriodNum, unitListener,commerceListener)
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

    override fun onPeriodSpinnerChange(period: Int) {
        currentStream?.apply {
            binding.rvStreamProduced.adapter = RVStreamProducedAdapter(
                this,
                period,
                ModelExample.measure,
                unitListener,
                commerceListener,
            )
            binding.rvStreamUsed.adapter = RVStreamUsedAdapter(
                this,
                period,
                unitListener,
                commerceListener
            )
        }
    }
}