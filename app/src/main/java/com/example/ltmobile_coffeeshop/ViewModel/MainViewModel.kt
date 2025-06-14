package com.example.ltmobile_coffeeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ltmobile_coffeeshop.Repository.MainRepository
import com.example.ltmobile_coffeeshop.domain.BannerModel
import com.example.ltmobile_coffeeshop.domain.CategoryModel
import com.example.ltmobile_coffeeshop.domain.ItemsModel

class MainViewModel:ViewModel() {
    private val repository=MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular():LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId:String):LiveData<MutableList<ItemsModel>> {
        return repository.loadItemCategory(categoryId)
    }
}