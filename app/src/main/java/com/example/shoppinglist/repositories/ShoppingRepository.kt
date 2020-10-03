package com.example.shoppinglist.repositories

import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.local.ShoppingItem
import com.example.shoppinglist.data.remote.responses.ImageResponse
import com.example.shoppinglist.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}