//package com.example.tmdbapp
//
//import com.example.tmdbapp.ui.features.MyApp
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjector
//import dagger.android.support.AndroidSupportInjectionModule
//import javax.inject.Singleton
//
//
//@Singleton
//@Component(modules = [(AndroidSupportInjectionModule::class),(ActivityModule::class)])
//interface AppComponent : AndroidInjector<MyApp> {
//    override fun inject(application: MyApp)
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: MyApp): Builder
//
//        fun build(): AppComponent
//    }
//}