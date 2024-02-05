package com.example.effective_mobile.Fragment.Main.HeartList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.doctor.Retrofit.ProductApi
import com.example.effective_mobile.Adapter.ProductCardAdapter
import com.example.effective_mobile.Adapter.ProductCardHeartAdapter
import com.example.effective_mobile.Model.FilterModel
import com.example.effective_mobile.Model.Product.HeartModel
import com.example.effective_mobile.Model.Product.ItemsProductModel
import com.example.effective_mobile.Model.ViewModel
import com.example.effective_mobile.R
import com.example.effective_mobile.Room.ProductDB
import com.example.effective_mobile.databinding.FragmentHeartBrandmarkeBinding
import com.example.effective_mobile.databinding.FragmentHeartProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HeartProductFragment : Fragment(),ProductCardHeartAdapter.Listener,ProductCardHeartAdapter.Listener2 {
   private lateinit var binding: FragmentHeartProductBinding
    lateinit var  adapterProductCard: ProductCardHeartAdapter
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var productApi: ProductApi

    var listProductFilter = listOf<ItemsProductModel>()
    var listFilter = listOf<FilterModel>()
    var listProductHeart = mutableListOf<ItemsProductModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartProductBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcViewProductCard()
        GetProduct()

        viewModel.heartList2.observe(viewLifecycleOwner){
            Log.i("sd221231231213","21321123213231")
            adapterProductCard.submitList(it)
        }


    }
    //Инициализируем Retrofit
    private fun initRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productApi = retrofit.create(ProductApi::class.java)

    }


    private fun GetProduct(){
        initRetrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.GetProduct()

            activity?.runOnUiThread {
                //Фиксируем полученные данные
                val List = list.body()

                listHeart(List!!.items)
            }
        }
    }

    fun listHeart(list: List<ItemsProductModel>){
        val db = ProductDB.getProduct(requireContext())
        val listHeart = mutableListOf<HeartModel>()
        val listProductHeart2 = mutableListOf<ItemsProductModel>()

        db.getDao().getAllItem().asLiveData().observe(requireActivity()) {itHeart->
            itHeart.forEach {
                val item = HeartModel(
                    it.id!!.toInt(),
                    it.productId,
                )
                listHeart.add(item)//Передали заполненый список
            }
            Log.i("1listHeart",listHeart.toString())
            Log.i("1listProductHeart",listProductHeart.toString())

            listProductHeart = list.toMutableList()
            for(i in 0..listHeart.size-1){
                for(j in 0..list.size-1){
                    if(listHeart[i].productId==list[j].id){
                        listProductHeart2.add(list[j])
                    }
                }
            }
            Log.i("2listHeart",listHeart.toString())
            Log.i("2listProductHeart",listProductHeart.toString())
            viewModel.heartList2.value = listProductHeart2

        }
    }

//    private fun GetProduct(){
//        Log.i("55555555","55555555")
//        initRetrofit()
//        CoroutineScope(Dispatchers.IO).launch {
//            val list = productApi.GetProduct()
//
//            activity?.runOnUiThread {
//                //Фиксируем полученные данные
//                val List = list.body()
//                Log.i("66666666666",List.toString())
//                Log.i("777777777",List!!.items.toString())
//                listHeart(List!!.items)
//            }
//        }
//    }

//    fun listHeart(list: List<ItemsProductModel>){
//       // initRcViewProductCard()
//        val db = ProductDB.getProduct(requireContext())
//        val listHeart = mutableListOf<HeartModel>()
//             listProductHeart.clear()
//            db.getDao().getAllItem().asLiveData().observe(requireActivity()) { itHeart ->
//                itHeart.forEach {
//                    val item = HeartModel(
//                        it.id!!.toInt(),
//                        it.productId,
//                    )
//                    listHeart.add(item)//Передали заполненый список
//                }
//
//                if (!listHeart.isEmpty()) {
//                    for (i in 0..list.size - 1) {
//                        for (j in 0..listHeart.size - 1) {
//                            if (list[i].id == listHeart[j].productId) {
//                                listProductHeart.add(list[i])
//                            }
//                        }
//                    }
//            }
//
//                viewModel.heartList2.value = listProductHeart
//
//            }
//    }
    private fun initRcViewProductCard() = with(binding) {
        rcViewProductHeart.layoutManager = GridLayoutManager(requireContext(), 2,
            GridLayoutManager.VERTICAL, false)//По вертикали будет выводить по умолчанию
        adapterProductCard = ProductCardHeartAdapter(this@HeartProductFragment,this@HeartProductFragment)
        rcViewProductHeart.adapter = adapterProductCard
    }

    companion object {

        fun newInstance() = HeartProductFragment()
    }

    override fun onClickHeart(item: ItemsProductModel) {
        searchHeart(item.id)
    }

    fun searchHeart(productId:String){
        val db = ProductDB.getProduct(requireContext())
        val listHeart = mutableListOf<HeartModel>()

        db.getDao().searchHeart(productId).asLiveData().observe(requireActivity()) {itHeart->
            itHeart.forEach {
                val item = HeartModel(
                    it.id!!.toInt(),
                    it.productId,
                )
                listHeart.add(item)//Передали заполненый список

            }
            Log.i("111111","111111")
            if(!listHeart.isEmpty()){
                Log.i("222222","222222")
                deleteHeart(productId)
            }

        }
        GetProduct()
    }
    fun deleteHeart(productid:String){
        val db = ProductDB.getProduct(requireActivity())
        CoroutineScope(Dispatchers.IO).launch {

            db.getDao().deleteProductHeart(productid)
            Log.i("33333","3333")
            activity?.runOnUiThread{
                Log.i("444444444","444444444")
                GetProduct()
            }

        }


    }

    override fun onClickCardProduct(item: ItemsProductModel) {

    }
}