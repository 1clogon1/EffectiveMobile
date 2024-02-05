package com.example.effective_mobile.Adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effective_mobile.Model.FilterModel
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.CardFilterBinding


class FilterAdapter(val listener: Listener,val listener2: Listener2) :
    ListAdapter<FilterModel, FilterAdapter.Holder>(Comparator()) {



    class Holder(view: View, val listener: Listener, val listener2: Listener2) :
        RecyclerView.ViewHolder(view) {

        val binding = CardFilterBinding.bind(view)

        var itemTemp: FilterModel? = null


        init {
            binding.btnFilter.setOnClickListener {
                itemTemp?.let { it1 -> listener.onClickFilter(it1) }

            }
            binding.btnClearFilter.setOnClickListener {
                itemTemp?.let { it2 -> listener2.onClickClearFilter(it2) }
            }

        }

        @SuppressLint("SuspiciousIndentation")
        fun bind(item: FilterModel) = with(binding) {
            itemTemp = item
            txtFilter.text = item.name

            if(item.status == true){
                btnClearFilter.visibility = View.VISIBLE
                btnFilter.setCardBackgroundColor(Color.parseColor("#3E3E3E"))
                item.status = false
            }
            else{
                btnClearFilter.visibility = View.GONE
                btnFilter.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
            }

//            btnFilter.setOnClickListener {
//                if (item.status == false) {
//                    btnClearFilter.visibility = View.VISIBLE
//                    btnFilter.setCardBackgroundColor(Color.parseColor("#3E3E3E"))
//                    item.status = true
//                }
//            }
//            btnClearFilter.setOnClickListener {
//                if (item.status == true) {
//                btnClearFilter.visibility = View.GONE
//                btnFilter.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
//                item.status = false
//            }
         // }

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_filter, parent, false)
        return Holder(view, listener,listener2)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val view = holder.bind(getItem(position))
    }


    class Comparator : DiffUtil.ItemCallback<FilterModel>() {
        override fun areItemsTheSame(
            oldItem: FilterModel,
            newItem: FilterModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FilterModel,
            newItem: FilterModel
        ): Boolean {
            return oldItem == newItem
        }
    }


    interface Listener {
        fun onClickFilter(item: FilterModel)
    }

    interface Listener2 {
        fun onClickClearFilter(item: FilterModel)
    }




}




