package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optiplans.databinding.FragmentUnitBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.entities.Unit

class UnitFragment : Fragment() {
    private lateinit var binding: FragmentUnitBinding
    var currentUnit: Unit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUnit = ModelExample.currentUnit
        binding.tvUnitTag.text = currentUnit?.tag?:""
        binding.tvUnitName.text = currentUnit?.name?:""
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UnitFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}