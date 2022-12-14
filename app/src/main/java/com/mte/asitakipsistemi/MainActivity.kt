package com.mte.asitakipsistemi

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
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
    private lateinit var dogumTarihi : SharedPreferences
    private lateinit var ilkGiris : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        dogumTarihi = this.getSharedPreferences("DogumTarihi", Context.MODE_PRIVATE)
        var editor = dogumTarihi.edit()

        var tarih = dogumTarihi.getString("dogumtarihi","")

        if(tarih != null)
        {
          binding.editTextTarih.setText(dogumTarihi.getString("dogumtarihi",""))
        }
        getMyData()

        binding.submitButton.setOnClickListener {

            editor.putString("dogumtarihi", binding.editTextTarih.text.toString()).apply()
            var delimiter = "/"
            var tarihler: List<String>? = dogumTarihi.getString("dogumtarihi","")?.split(delimiter)
            if (tarihler?.size == 3) {
                if(tarihler[2].toInt() > 1999){
                    if(tarihler[1].toInt() < 13 && tarihler[1].toInt() > 0){
                        if(tarihler[0].toInt() < 32 && tarihler[0].toInt() > 0){
                            getMyData()
                        }else{
                            basicAlert()
                            editor.putString("dogumtarihi", tarih).apply()
                        }
                    }else{
                        basicAlert()
                        editor.putString("dogumtarihi", tarih).apply()
                    }
                }else{
                    basicAlert()
                    editor.putString("dogumtarihi", tarih).apply()
                }
            }else{
                basicAlert()
                editor.putString("dogumtarihi", tarih).apply()
            }
        }

        binding.hintButton.setOnClickListener {
            val showPopUp = HintFragment()
            showPopUp.show((this).supportFragmentManager,"showPopUp")
        }

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
                adapter=AsilarAdapter(this@MainActivity,list,dogumTarihi)
                binding.asilarRecyclerView.adapter=adapter

            }

            override fun onFailure(call: Call<List<MyDataItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })
    }

    fun basicAlert(){

        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Ge??ersiz Tarih!")
            setMessage("L??tfen ge??erli bir tarih aral?????? giriniz")
            show()
        }


    }
}