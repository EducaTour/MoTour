package com.dicoding.motour.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.databinding.ItemLandmarkBinding

class HomeAdapter :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var landmarkList = ArrayList<Landmark>()
    private lateinit var onItemClickListener: OnItemClickListener

    class MyViewHolder(val binding: ItemLandmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Landmark) {
            with(binding) {
                tvLandmarkName.text = entry.name
                Glide.with(binding.root)
                    .load(entry.photo)
                    .into(ivItemLandmark)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLandmarkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = landmarkList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(landmarkList[position])
        holder.binding.card.setOnClickListener {
            onItemClickListener.onItemClick(landmarkList[position])
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun submitList(list: List<Landmark?>?) {
        this.landmarkList.clear()
        if (!list.isNullOrEmpty()) {
            this.landmarkList.addAll(list.filterNotNull())
        }
    }

    interface OnItemClickListener {
        fun onItemClick(landmark: Landmark)
    }
}