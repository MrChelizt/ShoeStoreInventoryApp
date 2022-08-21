package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {
    //TODO: step:10 -> add LiveData that returns list of shoes

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = initShoeList()
    }

    private fun initShoeList(): List<Shoe>? {
        return mutableListOf(
            Shoe("adidas", 12.0, "adidas", "running", mutableListOf("adidas.jpg")),
            Shoe("nike", 9.0, "nike", "football", mutableListOf("nike.jpg")),
            Shoe("puma", 10.0, "puma", "tennis", mutableListOf("puma.jpg"))
        )
    }
}