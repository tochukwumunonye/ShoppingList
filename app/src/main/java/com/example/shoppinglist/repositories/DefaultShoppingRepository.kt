package com.example.shoppinglist.repositories

import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.local.ShoppingDao
import com.example.shoppinglist.data.local.ShoppingItem
import com.example.shoppinglist.data.remote.PixabayApi
import com.example.shoppinglist.data.remote.responses.ImageResponse
import com.example.shoppinglist.other.Resource
import retrofit2.Response
import javax.inject.Inject


class DefaultShoppingRepository@Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayApi
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
       return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.error("Network Error, check your internet connection", null)
        }
    }
}