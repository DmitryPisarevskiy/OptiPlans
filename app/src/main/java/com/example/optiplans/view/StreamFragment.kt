package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.optiplans.databinding.FragmentStreamBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Stream
import org.w3c.dom.Text

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentStream = ModelExample.currentStream
        binding.tvStreamTag.text = currentStream?.tag?:""
        binding.tvStreamName.text = currentStream?.name?:""
        var iMax = currentStream?.prices?.size ?: 1
        iMax--
        for (i in 0..iMax) {
            val tvPeriod = TextView(context)
            tvPeriod.text = "Период " + i.toString() + "(" + ModelExample.periods[i].toString() + " дней)"
            binding.trStreamPeriod.addView(tvPeriod)
        }
        currentStream?.minBoundsSales?.forEach {
            val tvMIN = TextView(context)
            tvMIN.text = it.toString()
            binding.trStreamMin.addView(tvMIN)
        }
        currentStream?.maxBoundsSales?.forEach {
            val tvMAX = TextView(context)
            tvMAX.text = it.toString()
            binding.trStreamMax.addView(tvMAX)
        }
        currentStream?.prices?.forEach {
            val tvPrice = TextView(context)
            tvPrice.text = it.toString()
            binding.trStreamPrice.addView(tvPrice)
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