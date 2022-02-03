package com.example.hsdemosoft.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hsdemosoft.R
import com.example.hsdemosoft.databinding.RclCountyDataItemBinding
import com.example.hsdemosoft.models.Country
import com.example.hsdemosoft.models.CountryModel
import com.example.hsdemosoft.utils.loadImg

class ListFragmentAdapter(private val listener: (Country) -> Unit) : RecyclerView.Adapter<ListFragmentAdapter.ListFragmentViewHolder>()   {
    private val countryList: ArrayList<Country> = arrayListOf()

    fun emitData(list: List<Country>?){
        val du = ListAdapterDiffUtil(countryList, list!!)
        val cDu = DiffUtil.calculateDiff(du)

        countryList.clear()
        countryList.addAll(list)

        cDu.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RclCountyDataItemBinding>(
            layoutInflater,
            R.layout.rcl_county_data_item,
            parent,
            false
        )

        return ListFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class ListFragmentViewHolder(private val binding: RclCountyDataItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Country){
            binding.countryImage.loadImg(data.emoji)
            binding.countryName.text = data.name

            binding.root.setOnClickListener {
                listener.invoke(data)
            }
        }
    }
}