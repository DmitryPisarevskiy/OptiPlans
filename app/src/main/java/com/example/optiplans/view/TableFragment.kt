package com.example.optiplans.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optiplans.databinding.FragmentTableBinding
import com.example.optiplans.entities.ModelExample
import com.example.optiplans.view.rw.table.RVTableAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            rvTable.layoutManager = GridLayoutManager(activity, 2)
            rvTable.adapter = RVTableAdapter(ModelExample)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}