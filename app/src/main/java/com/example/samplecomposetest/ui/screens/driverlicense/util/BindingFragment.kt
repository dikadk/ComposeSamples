package com.example.samplecomposetest.ui.screens.driverlicense.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Should be extended for using ViewBinding
 */
abstract class BindingFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    /**
     * Used for accessing the binding as a nullable field, which can be used in [view.post] and other
     * queued Runnables, during which the null can be destroyed.
     */
    protected val nullableBinding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindView(inflater, container)
        return binding.root
    }

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
