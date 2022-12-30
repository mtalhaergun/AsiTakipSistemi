package com.mte.asitakipsistemi

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mte.asitakipsistemi.api.MyDataItem
import com.mte.asitakipsistemi.databinding.FragmentAsiBinding
import java.time.LocalDate
import java.time.Period

class AsiFragment(var dogumTarihi:SharedPreferences,val postList:List<MyDataItem>,var index:Int): DialogFragment() {
    private var _binding : FragmentAsiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAsiBinding.inflate(inflater, container, false)

        var delimiter = "/"
        var tarihler: List<String>? = dogumTarihi.getString("dogumtarihi","")?.split(delimiter)
        if(postList[index].birinci != ""){
            tarihler?.let {
                var date = LocalDate.of(tarihler[2].toInt(),tarihler[1].toInt(),tarihler[0].toInt())
                var modifiedDate = date.plus(Period.of(0,postList[index].birinci.toInt(),0))
                binding.birinciTarih.text = modifiedDate.toString()
            }
        }
        if(postList[index].ikinci != ""){
            binding.ikinciLayout.visibility = VISIBLE
            tarihler?.let {
                var date = LocalDate.of(tarihler[2].toInt(),tarihler[1].toInt(),tarihler[0].toInt())
                var modifiedDate = date.plus(Period.of(0,postList[index].ikinci.toInt(),0))
                binding.ikinciTarih.text = modifiedDate.toString()
            }
        }else{
            binding.ikinciLayout.visibility = GONE
        }
        if(postList[index].ucuncu != ""){
            binding.ucuncuLayout.visibility = VISIBLE
            tarihler?.let {
                var date = LocalDate.of(tarihler[2].toInt(),tarihler[1].toInt(),tarihler[0].toInt())
                var modifiedDate = date.plus(Period.of(0,postList[index].ucuncu.toInt(),0))
                binding.ucuncuTarih.text = modifiedDate.toString()
            }
        }else{
            binding.ucuncuLayout.visibility = GONE

        }

        binding.exitButton.setOnClickListener {
            onDestroyView()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}