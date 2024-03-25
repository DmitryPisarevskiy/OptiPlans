package com.example.optiplans.view

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.optiplans.R
import com.example.optiplans.databinding.FragmentStreamBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream

class StreamFragment : Fragment() {
    var currentStream: Stream? = null
    lateinit var binding: FragmentStreamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStreamBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentStream = ModelExample.currentStream
        binding.tvStreamTag.text = currentStream?.tag?:""
        binding.tvStreamName.text = currentStream?.name?:""
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
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun addTableItem(layout: LinearLayout, values: Array<Float>) {
        values.forEach {
            val textView = TextView(context)
            textView.text = it.toString()
            textView.gravity = Gravity.CENTER
            textView.setTextAppearance(R.style.TableText)
            layout.addView(textView)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StreamFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}