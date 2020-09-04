//package com.example.tmdbapp.ui.features
//
//
//import com.example.tmdbapp.AppComponent
//import com.example.tmdbapp.DaggerAppComponent
//import dagger.android.DaggerApplication
//
//
//class MyApp: BaseApplication(){
//
//    override val appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder()
//        .application(this)
//        .build()
//    }
////
////    private val applicationInjector = DaggerApplicationComponent.builder()
////        .application(this)
////        .build()
////
////    override fun applicationInjector() = applicationInjector
//
//}