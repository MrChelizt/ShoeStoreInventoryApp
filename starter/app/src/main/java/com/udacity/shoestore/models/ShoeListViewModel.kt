package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {
    //TODO: step:10 -> add LiveData that returns list of shoes

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    init {
        _shoeList.value = initShoeList()
        resetShoe()
    }

    private fun initShoeList(): MutableList<Shoe>? {
        return mutableListOf(
            Shoe("adidas", 12.0, "adidas", "running", mutableListOf("adidas.jpg")),
            Shoe("nike", 9.0, "nike", "football", mutableListOf("nike.jpg")),
            Shoe("puma", 10.0, "puma", "tennis", mutableListOf("puma.jpg"))
        )
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

    fun resetShoe (){
        _shoe.value = Shoe("", 0.0, "", "", mutableListOf())
    }
}