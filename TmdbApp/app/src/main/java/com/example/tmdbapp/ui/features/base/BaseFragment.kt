package com.example.tmdbapp.ui.features.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {

    abstract fun getViewModelClass(): Class<VM>
    abstract fun layoutId(): Int
    private var isRetryOnNetwork: Boolean = false
    protected var isNetworkAvailable = true
    private var isLoaded: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::binding.isInitialized) {
            return binding.root
        }
        binding = DataBindingUtil.bind(inflater.inflate(layoutId(), container, false))!!
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    abstract fun getViewModelOwner(): ViewModelStoreOwner
    abstract fun setObserver()

    abstract fun toBeCalledOnce()

    fun navController() = Navigation.findNavController(requireView())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(getViewModelOwner(), viewModelFactory)[getViewModelClass()]

        setObserver()
    }



}