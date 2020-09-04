package com.example.tmdbapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//    override fun getContentViewId(): Int {
//       return R.layout.activity_main
//    }
//
//    override fun getRootLayoutContainer(): View {
//        return root_container
//    }
//
//    override fun getViewModelClass(): Class<CommonSampleViewModel> {
//        return CommonSampleViewModel::class.java
//    }
}