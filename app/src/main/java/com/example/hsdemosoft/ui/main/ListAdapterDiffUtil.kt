package com.example.hsdemosoft.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.hsdemosoft.models.Country
import com.example.hsdemosoft.models.CountryModel

class ListAdapterDiffUtil(
    private val oldList: ArrayList<Country>,
    private val newList: List<Country>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].capital ==
                newList[newItemPosition].capital
    }
}