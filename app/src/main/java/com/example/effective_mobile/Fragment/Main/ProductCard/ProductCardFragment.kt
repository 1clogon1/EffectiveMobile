package com.example.effective_mobile.Fragment.Main.ProductCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.effective_mobile.Fragment.Main.CategoryFragment
import com.example.effective_mobile.Model.Product.ItemsProductModel
import com.example.effective_mobile.Model.ViewModel
import com.example.effective_mobile.R
import com.example.effective_mobile.Room.ProductDB
import com.example.effective_mobile.databinding.FragmentProductCardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductCardFragment : Fragment() {
    private lateinit var binding:FragmentProductCardBinding
    private val viewModel: ViewModel by activityViewModels()

    var descriptionTF = true
    var specificationsTF = false
    var statusTF = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCardBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMainFragment, CategoryFragment.newInstance())
                ?.commit()
        }
        viewModel.productCard.observe(viewLifecycleOwner){
            AddView(it)
        }


        ButtonDescription()

        ButtonSpecifications()



    }

    fun deleteHeart(productid:String){
        val db = ProductDB.getProduct(requireActivity())
        CoroutineScope(Dispatchers.IO).launch {

            db.getDao().deleteProductHeart(productid)

        }

    }


    private fun AddView(item:ItemsProductModel)= with(binding) {
        title.text= item.title
        subtitle.text= item.subtitle
        price.text= item.price.price+" "+item.price.unit
        price2.text= item.price.price+item.price.unit
        discount.text= "-"+item.price.discount+"%"
        priceWithDiscount.text= item.price.priceWithDiscount+" "+item.price.unit
        priceWithDiscount2.text= item.price.priceWithDiscount+item.price.unit
        count.text= item.feedback.count.toString()+" отзыва"
        rating.text= item.feedback.rating.toString()
        available.text= "Доступно для заказа "+item.available.toString()+" штук"
        description.text= item.description
        if(item.info.size==3){
            title1.text= item.info[0].title
            title22.text= item.info[1].title
            title3.text= item.info[2].title

            value1.text= item.info[0].value
            value2.text= item.info[1].value
            value3.text= item.info[2].value
        }
        else{
            LL3.visibility = View.GONE
            CV3.visibility = View.GONE
            title1.text= item.info[0].title
            title22.text= item.info[1].title

            value1.text= item.info[0].value
            value2.text= item.info[1].value
        }
        ingredients.text = item.ingredients


        val imageList = ArrayList<SlideModel>()
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

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)


        if(item.status == true){
            binding.btnHeart.setImageResource(R.drawable.heart21)
        }
        else{
            binding.btnHeart.setImageResource(R.drawable.heart20)
        }
        statusTF = item.status
        binding.btnHeart.setOnClickListener{
            deleteHeart(item.id)

            if(statusTF == true){
                statusTF = false
                binding.btnHeart.setImageResource(R.drawable.heart20)
            }
            else{
                statusTF = true
                binding.btnHeart.setImageResource(R.drawable.heart21)
            }
        }
    }

    private fun ButtonDescription()=with(binding) {
        btnDescription.setOnClickListener{
            if(descriptionTF==true){
                descriptionTF=false
                btnBrend.visibility = View.GONE
                description.visibility = View.GONE
                btnDescription.text = "Подробнее"
            }
            else{
                descriptionTF=true
                btnBrend.visibility = View.VISIBLE
                description.visibility = View.VISIBLE
                btnDescription.text = "Скрыть"
            }
        }
    }

    private fun ButtonSpecifications()=with(binding) {
        btnSpecifications.setOnClickListener{
            if(specificationsTF==true){
                specificationsTF=false
                ingredients.maxLines =2
                btnSpecifications.text = "Подробнее"
            }
            else{
                specificationsTF=true
                ingredients.maxLines =1000
                btnSpecifications.text = "Скрыть"
            }
        }
    }


    companion object {
        fun newInstance() = ProductCardFragment()
    }
}