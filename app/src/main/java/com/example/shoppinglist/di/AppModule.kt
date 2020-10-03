package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shoppinglist.R
import com.example.shoppinglist.data.local.ShoppingDao
import com.example.shoppinglist.data.local.ShoppingItemDatabase
import com.example.shoppinglist.data.remote.PixabayApi
import com.example.shoppinglist.other.Constants.Companion.BASE_URL
import com.example.shoppinglist.other.Constants.Companion.DATABASE_NAME
import com.example.shoppinglist.repositories.DefaultShoppingRepository
import com.example.shoppinglist.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()



    @Singleton
    @Provides
    fun providesShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()



    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayApi
        ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun providesPixabayApi() : PixabayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayApi::class.java)
    }
}















