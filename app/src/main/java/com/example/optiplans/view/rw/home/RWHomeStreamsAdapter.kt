package com.example.optiplans.view.rw.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RwHomeStreamsItemBinding
import com.example.optiplans.entities.Stream

class RWHomeStreamsAdapter(private val streams: List<Stream>): RecyclerView.Adapter<RWHomeStreamsAdapter.StreamViewHolder>() {
    class StreamViewHolder (val binding: RwHomeStreamsItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RwHomeStreamsItemBinding.inflate(inflater,parent, false)
        return StreamViewHolder(binding)
    }

    override fun getItemCount(): Int = streams.size

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int) {
        val stream = streams[position]
        val context = holder.itemView.context
        holder.binding.tvHomeStreamsItem.text = stream.name
    }
}