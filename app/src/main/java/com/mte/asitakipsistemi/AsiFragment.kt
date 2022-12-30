package com.mte.asitakipsistemi

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mte.asitakipsistemi.api.MyDataItem
import com.mte.asitakipsistemi.databinding.FragmentAsiBinding

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
        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}