package com.example.optiplans.view.rw.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvHomeStreamsItemBinding
import com.example.optiplans.entities.Stream

class RVHomeStreamsAdapter(private val streams: List<Stream>): RecyclerView.Adapter<RVHomeStreamsAdapter.StreamViewHolder>() {
    class StreamViewHolder (val binding: RvHomeStreamsItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvHomeStreamsItemBinding.inflate(inflater,parent, false)
        return StreamViewHolder(binding)
    }

    override fun getItemCount(): Int = streams.size

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int) {
        val stream = streams[position]
        val context = holder.itemView.context
        holder.binding.tvHomeStreamsItem.text = stream.name
        holder.binding.svHomeStreamsStream.painting(stream.color)
    }

}