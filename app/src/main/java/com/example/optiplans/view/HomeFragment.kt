package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.optiplans.databinding.FragmentHomeBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.view.rw.home.RVHomeStreamsAdapter
import com.example.optiplans.view.rw.home.RVHomeUnitsAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            tvHomeModelName.text = ModelExample.name
            tvHomeNumOfPeriods.text = ModelExample.numOfPeriods.toString()
            rvHomeUnits.layoutManager = GridLayoutManager(activity,2)
            rvHomeUnits.adapter = RVHomeUnitsAdapter(ModelExample.units)
            rvHomeStreams.layoutManager= GridLayoutManager(activity,2)
            rvHomeStreams.adapter = RVHomeStreamsAdapter(ModelExample.streams)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}