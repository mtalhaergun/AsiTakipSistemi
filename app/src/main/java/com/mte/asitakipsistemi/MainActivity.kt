package com.mte.asitakipsistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.mte.asitakipsistemi.adapter.AsilarAdapter
import com.mte.asitakipsistemi.api.ApiInterface
import com.mte.asitakipsistemi.api.MyDataItem
import com.mte.asitakipsistemi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var list:List<MyDataItem>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AsilarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(
            ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object: Callback<List<MyDataItem>> {
            override fun onResponse(
                call: Call<List<MyDataItem>>,
                response: Response<List<MyDataItem>>
            ) {

                list = response.body()!!
                val layoutManager= GridLayoutManager(this@MainActivity,2)
                binding.asilarRecyclerView.layoutManager=layoutManager
                adapter=AsilarAdapter(this@MainActivity,list)
                binding.asilarRecyclerView.adapter=adapter

            }

            override fun onFailure(call: Call<List<MyDataItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })
    }
}