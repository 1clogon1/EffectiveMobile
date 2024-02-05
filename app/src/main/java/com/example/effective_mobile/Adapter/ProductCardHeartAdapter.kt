package com.example.effective_mobile.Adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.effective_mobile.Fragment.Main.ProductCard.ProductCardFragment
import com.example.effective_mobile.Model.FilterModel
import com.example.effective_mobile.Model.Product.ItemsProductModel
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.CardFilterBinding
import com.example.effective_mobile.databinding.CardProductBinding


class ProductCardHeartAdapter(val listener: Listener, val listener2: Listener2) :
    ListAdapter<ItemsProductModel, ProductCardHeartAdapter.Holder>(Comparator()) {



    class Holder(view: View, val listener: Listener, val listener2: Listener2) :
        RecyclerView.ViewHolder(view) {

        val binding = CardProductBinding.bind(view)

        var itemTemp: ItemsProductModel? = null


        init {
            binding.btnHeart.setOnClickListener {
                itemTemp?.let { itHeart -> listener.onClickHeart(itHeart) }

            }
            binding.btnCardProduct.setOnClickListener {
                itemTemp?.let { itCard -> listener2.onClickCardProduct(itCard) }
            }

        }

        @SuppressLint("SuspiciousIndentation")
        fun bind(item: ItemsProductModel) = with(binding) {
            itemTemp = item
            val imageList = ArrayList<SlideModel>()
                //itemTemp = item
                    binding.btnHeart.setImageResource(R.drawable.heart21)



                if(item.id == "cbf0c984-7c6c-4ada-82da-e29dc698bb50"){
                    imageList.add(SlideModel(R.drawable.product1))
                    imageList.add(SlideModel(R.drawable.product1))
                }
                else if(item.id == "54a876a5-2205-48ba-9498-cfecff4baa6e"){
                    imageList.add(SlideModel(R.drawable.product2))
                    imageList.add(SlideModel(R.drawable.product2))
                }
                else if(item.id == "75c84407-52e1-4cce-a73a-ff2d3ac031b3"){
                    imageList.add(SlideModel(R.drawable.product3))
                    imageList.add(SlideModel(R.drawable.product3))
                }
                else if(item.id == "16f88865-ae74-4b7c-9d85-b68334bb97db"){
                    imageList.add(SlideModel(R.drawable.product4))
                    imageList.add(SlideModel(R.drawable.product4))
                }
                else if(item.id == "26f88856-ae74-4b7c-9d85-b68334bb97db"){
                    imageList.add(SlideModel(R.drawable.product5))
                    imageList.add(SlideModel(R.drawable.product5))
                }
                else if(item.id == "15f88865-ae74-4b7c-9d81-b78334bb97db"){
                    imageList.add(SlideModel(R.drawable.product6))
                    imageList.add(SlideModel(R.drawable.product6))
                }
                else if(item.id == "88f88865-ae74-4b7c-9d81-b78334bb97db"){
                    imageList.add(SlideModel(R.drawable.product7))
                    imageList.add(SlideModel(R.drawable.product7))
                }
                else if(item.id == "55f58865-ae74-4b7c-9d81-b78334bb97db"){
                    imageList.add(SlideModel(R.drawable.product8))
                    imageList.add(SlideModel(R.drawable.product8))
                }
                binding.price.paintFlags = binding.price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.price.text = item.price.price+" "+item.price.unit
                binding.discount.text = "-"+item.price.discount.toString()+"%"
                binding.priceWithDiscount.text = item.price.priceWithDiscount+" "+item.price.unit
                binding.title.text = item.title
                binding.subtitle.text = item.subtitle
                binding.rating.text = item.feedback.rating.toString()
                binding.count.text = "("+item.feedback.count.toString()+")"

                binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
            }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_product, parent, false)
        return Holder(view, listener,listener2)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val view = holder.bind(getItem(position))
    }


    class Comparator : DiffUtil.ItemCallback<ItemsProductModel>() {
        override fun areItemsTheSame(
            oldItem: ItemsProductModel,
            newItem: ItemsProductModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemsProductModel,
            newItem: ItemsProductModel
        ): Boolean {
            return oldItem == newItem
        }
    }


    interface Listener {
        fun onClickHeart(item: ItemsProductModel)
    }

    interface Listener2 {
        fun onClickCardProduct(item: ItemsProductModel)
    }




}




