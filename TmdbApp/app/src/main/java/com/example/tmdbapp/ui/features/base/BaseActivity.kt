//package com.example.tmdbapp.ui.features.base
//
//import android.content.Context
//import android.os.Bundle
//import android.view.View
//import androidx.annotation.LayoutRes
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import androidx.databinding.library.baseAdapters.BR
//import androidx.fragment.app.FragmentActivity
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import dagger.android.AndroidInjection
//import dagger.android.support.DaggerAppCompatActivity
//import javax.inject.Inject
//
//abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> :
//    AppCompatActivity() {
//
//    protected lateinit var viewBinding: ViewBinding
//
//    protected abstract val viewModel: ViewModel
//
//    @get:LayoutRes
//    protected abstract val layoutId: Int
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (::viewBinding.isInitialized.not()) {
//            viewBinding = DataBindingUtil.setContentView(this, layoutId)
//            viewBinding.setVariable(com.example.tmdbapp.BR., viewModel)
//        }
//    }
//
//
//}