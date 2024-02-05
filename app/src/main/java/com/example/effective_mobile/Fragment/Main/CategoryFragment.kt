package com.example.effective_mobile.Fragment.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.doctor.Retrofit.ProductApi
import com.example.effective_mobile.Adapter.FilterAdapter
import com.example.effective_mobile.Adapter.ProductCardAdapter
import com.example.effective_mobile.Fragment.Main.ProductCard.ProductCardFragment
import com.example.effective_mobile.Model.DB.ProductHeart
import com.example.effective_mobile.Model.FilterModel
import com.example.effective_mobile.Model.Product.HeartModel
import com.example.effective_mobile.Model.Product.ItemsProductModel
import com.example.effective_mobile.Model.Product.ProductModel
import com.example.effective_mobile.Model.ViewModel
import com.example.effective_mobile.R
import com.example.effective_mobile.Room.EntityHeart
import com.example.effective_mobile.Room.ProductDB
import com.example.effective_mobile.databinding.FragmentCategoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CategoryFragment : Fragment(),
    FilterAdapter.Listener,
    FilterAdapter.Listener2,
    ProductCardAdapter.Listener,
    ProductCardAdapter.Listener2
{
    private lateinit var binding: FragmentCategoryBinding
    lateinit var  adapterFilter: FilterAdapter
    lateinit var  adapterProductCard: ProductCardAdapter

    private val viewModel: ViewModel by activityViewModels()

    lateinit var listProduct : List<ProductModel>

    var listProductFilter = listOf<ItemsProductModel>()
    var listProductFilter2 = mutableListOf<ItemsProductModel>()
    var listProductHeart = mutableListOf<ItemsProductModel>()

    var listFilter = listOf<FilterModel>()
    var listFilter2 = mutableListOf<FilterModel>()

    private lateinit var productApi: ProductApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFilter = listOf(FilterModel(1,"Смотреть все",false), FilterModel(2,"Лицо",false),FilterModel(3,"Тело",false),FilterModel(4,"Загар",false),FilterModel(5,"Маска",false))


        initRcView()
        initRcViewProductCard()
        viewModel.filter.observe(viewLifecycleOwner){
            adapterFilter.submitList(it)
        }

        viewModel.product.observe(viewLifecycleOwner){
            listHeart(it)
        }

        viewModel.heartList.observe(viewLifecycleOwner){
            adapterProductCard.submitList(it)
        }



        //Заполняем фильтры
        viewModel.filter.value = listFilter

        GetProduct()

        popMenu()



    }

    private fun popMenu() {
        //Созданиеменю
        val popMenu = PopupMenu(
            requireContext(),
            binding.btnFilterList
        )

        //Кнопуи
        popMenu.menu.add(Menu.NONE, 0, 0, "По популярности")
        popMenu.menu.add(Menu.NONE, 1, 1, "По возростанию цены")
        popMenu.menu.add(Menu.NONE, 2, 2, "По убыванию цены")
        popMenu.setOnMenuItemClickListener {
            val id = it.itemId
            //Ситуции при нажатие на один из пунктов
            when(id){
                0->MaxAndMinRating()
                1->MaxAndMinPrice()
                2->MinAndMaxPrice()
            }

            false
        }
        binding.btnFilterList.setOnClickListener {
            //Активируем меню
            popMenu.show()
        }
    }

    private fun MaxAndMinRating() {
        //Временная ячейка
        listProductFilter2 = listProductFilter.toMutableList()

        //Сортируем
        val feedback = Comparator.comparing { movie: ItemsProductModel -> movie.feedback.count }
        listProductFilter2.sortWith(feedback.thenComparing(feedback))


        //Обновляем recyclerView
        //initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)

    }

    private fun MinAndMaxPrice() {

        //Временная ячейка
        listProductFilter2 = listProductFilter.toMutableList()

        //Сортируем
        val feedback = Comparator.comparing { movie: ItemsProductModel -> movie.price.priceWithDiscount.toInt() }
        listProductFilter2.sortWith(feedback.thenComparing(feedback))

        //Переворачиваем
        listProductFilter2.reverse()

        //Обновляем recyclerView
        //initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }

    private fun MaxAndMinPrice() {
        //Временная ячейка
        listProductFilter2 = listProductFilter.toMutableList()

        //Сортируем
        val feedback = Comparator.comparing { movie: ItemsProductModel -> movie.price.priceWithDiscount.toInt() }
        listProductFilter2.sortWith(feedback.thenComparing(feedback))


        //Обновляем recyclerView
       // initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }




    private fun GetProduct(){
        initRetrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.GetProduct()

            activity?.runOnUiThread {


                //Фиксируем полученные данные
                val List = list.body()

                for(i in 0..List?.items?.size!!-1){
                    Log.i("dfdfgfgd",List.items[i].toString())
                }
                listProductFilter = List.items
                listFilter = listOf(FilterModel(1,"Смотреть все",false), FilterModel(2,"Лицо",false),FilterModel(3,"Тело",false),FilterModel(4,"Загар",false),FilterModel(5,"Маска",false))

                listHeart(List.items)
            }
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

    private fun initRcView() = with(binding) {
        rcView.layoutManager = GridLayoutManager(requireContext(), 1,GridLayoutManager.HORIZONTAL, false)//По вертикали будет выводить по умолчанию
        adapterFilter = FilterAdapter(this@CategoryFragment,this@CategoryFragment)
        rcView.adapter = adapterFilter
    }

    private fun initRcViewProductCard() = with(binding) {
        rcViewProduct.layoutManager = GridLayoutManager(requireContext(), 2,GridLayoutManager.VERTICAL, false)//По вертикали будет выводить по умолчанию
        adapterProductCard = ProductCardAdapter(this@CategoryFragment,this@CategoryFragment)
        rcViewProduct.adapter = adapterProductCard
    }


    companion object {

        fun newInstance() = CategoryFragment()
    }

    override fun onClickFilter(item: FilterModel) {
        Log.i("2","2")
        //Очищаем список для товаров под выбранный фильтр
        listProductFilter2.clear()

        //Вариации выбора фильтра
        when(item.id){
            1->allFilter()
            2->faceFilter()
            3->bodyFilter()
            4->tanFilter()
            5->maskFilter()
        }
        filterVisible(item)

    }

    private fun filterVisible(item: FilterModel) {
        initRcView()
        listFilter2 = listFilter.toMutableList()
        for(i in 0..listFilter.size-1){
            if(listFilter[i].name == item.name){
                listFilter2[i].status = true
            }
        }
        viewModel.filter.value = listFilter
    }

    private fun allFilter() {
        //Временная ячейка
        listProductFilter2 = listProductFilter.toMutableList()
        //Обновляем recyclerView
        //initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }
    private fun faceFilter() {
        //Проходимся и вытакиваем товары с нужным фильтром
        for(i in 0..listProductFilter.size-1){
            if(listProductFilter[i].info[1].value == "Лицо"){
                listProductFilter2.add(listProductFilter[i])
            }
        }
        //Обновляем recyclerView
        //initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }
    private fun bodyFilter() {
        //Проходимся и вытакиваем товары с нужным фильтром
        for(i in 0..listProductFilter.size-1){
            if(listProductFilter[i].info[1].value == "Тело"){
                listProductFilter2.add(listProductFilter[i])
            }
        }
        //Обновляем recyclerView
       // initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }
    private fun tanFilter() {
        //Проходимся и вытакиваем товары с нужным фильтром
        for(i in 0..listProductFilter.size-1){
            if(listProductFilter[i].info[1].value == "Загар"){
                listProductFilter2.add(listProductFilter[i])
            }
        }
        //Обновляем recyclerView
       // initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }
    private fun maskFilter() {
        //Проходимся и вытакиваем товары с нужным фильтром
        for(i in 0..listProductFilter.size-1){
            if(listProductFilter[i].info[1].value == "Маска"){
                listProductFilter2.add(listProductFilter[i])
            }
        }
        //Обновляем recyclerView
      //  initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
    }

    override fun onClickClearFilter(item: FilterModel) {

        initRcView()
        //Заполняем фильтры
        viewModel.filter.value = listFilter

        //Временная ячейка
        listProductFilter2 = listProductFilter.toMutableList()
        //Обновляем recyclerView
        //initRcViewProductCard()
        //Передаем обновленный список
        listHeart(listProductFilter2)
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
            if(listHeart.isEmpty()){
                if(!productId.isEmpty()){
                    val db = ProductDB.getProduct(requireActivity())
                    CoroutineScope(Dispatchers.IO).launch {

                        db.getDao().insertItem(productId)

                    }
                }
            }
            else{
                deleteHeart(productId)
            }


        }
    }

    fun deleteHeart(productid:String){
        val db = ProductDB.getProduct(requireActivity())
        CoroutineScope(Dispatchers.IO).launch {

            db.getDao().deleteProductHeart(productid)

        }
        GetProduct()
    }

    fun listHeart(list: List<ItemsProductModel>){
        val db = ProductDB.getProduct(requireContext())
        val listHeart = mutableListOf<HeartModel>()

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
                            listProductHeart[j].status = true
                        }
                    }
                }
                Log.i("2listHeart",listHeart.toString())
                Log.i("2listProductHeart",listProductHeart.toString())
                viewModel.heartList.value = listProductHeart

        }
    }

    override fun onClickCardProduct(item: ItemsProductModel) {
        viewModel.productCard.value = item
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.CLMainFragment, ProductCardFragment.newInstance())
            ?.commit()
    }


}