package com.example.optiplans.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.optiplans.databinding.RvHomeStreamsItemBinding
import com.example.optiplans.entities.Stream
import com.example.optiplans.view.IStreamClickListener

class RVHomeStreamsAdapter(private val streams: List<Stream>, val streamListener: IStreamClickListener): RecyclerView.Adapter<RVHomeStreamsAdapter.StreamViewHolder>() {
    class StreamViewHolder (val binding: RvHomeStreamsItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvHomeStreamsItemBinding.inflate(inflater,parent, false)
        return StreamViewHolder(binding)
    }

    override fun getItemCount(): Int = streams.size

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int) {
        with (holder.binding) {
            val stream = streams[position]
            tvHomeStreamsItem.text = stream.name
            svHomeStreamsStream.painting(stream.color)
            root.setOnClickListener {
                streamListener.onStreamClick(stream)
            }
        }
    }
}