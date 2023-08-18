package com.pankti.googleadsdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.VideoController
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.rvadapter.AdmobNativeAdAdapter
import com.pankti.googleadsdemo.databinding.ActivityNativeAdsBinding
import com.pankti.googleadsdemo.databinding.LayoutNativeAdBinding

class NativeAdsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeAdsBinding
    private val myAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_native_ads)
        initView()
    }

    private fun initView() {
        MobileAds.initialize(this) { }

        binding.rvNativeAdList.layoutManager = LinearLayoutManager(this@NativeAdsActivity)
        val admobNativeAdAdapter: AdmobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with(
                "ca-app-pub-3940256099942544/2247696110", myAdapter, "medium")
            .adItemIterval(2).build()
        binding.rvNativeAdList.adapter = admobNativeAdAdapter
    }
}