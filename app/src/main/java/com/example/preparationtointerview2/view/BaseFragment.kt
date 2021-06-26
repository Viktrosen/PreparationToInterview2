package com.example.preparationtointerview2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.preparationtointerview2.MainActivity

abstract class BaseFragment<B: ViewBinding> : Fragment() {
    // provides ViewBinding
    protected lateinit var binding: B



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        return binding.root


    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
}