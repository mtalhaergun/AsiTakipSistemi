package com.mte.asitakipsistemi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mte.asitakipsistemi.databinding.FragmentAsiBinding
import com.mte.asitakipsistemi.databinding.FragmentHintBinding

class HintFragment : DialogFragment() {
    private var _binding : FragmentHintBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHintBinding.inflate(inflater, container, false)

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