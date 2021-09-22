package com.example.task6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import com.example.task6.data.Constants
import com.example.task6.databinding.DownFragmentBinding
import com.thechance.rxjavaapp.ui.fragment.BaseFragment


class DownFragment : BaseFragment<DownFragmentBinding>(){
    private var value = ""

    override val LOG_TAG: String
        get() = javaClass.simpleName
    override val bindingInflater: (LayoutInflater) -> DownFragmentBinding= DownFragmentBinding::inflate

    override fun onStart() {
        super.onStart()
        arguments?.let {
            value = it.getString(Constants.VALUE).toString()
            binding?.viewText?.text = ""
            binding?.viewText?.text = value
        }
    }
    override fun setup() {
        binding.let {
            it?.viewText?.text = value
        }
    }
    override fun addCallBack() {

    }
}