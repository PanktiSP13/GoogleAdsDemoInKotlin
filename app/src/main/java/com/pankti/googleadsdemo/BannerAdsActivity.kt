package com.pankti.googleadsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.pankti.googleadsdemo.databinding.ActivityBannerAdsBinding

class BannerAdsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBannerAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_banner_ads)

        initView()
        performClicks()
    }

    private fun initView() {
        MobileAds.initialize(this) { }

        val adRequest = AdRequest.Builder().build()
        binding.bannerAdViewBANNER.loadAd(adRequest)
        binding.bannerAdViewLARGEBANNER.loadAd(adRequest)
        binding.bannerAdViewMEDIUMRECTANGLE.loadAd(adRequest)
        binding.bannerAdViewSMARTBANNER.loadAd(adRequest)
    }

    private fun performClicks() {

        binding.bannerAdViewBANNER.adListener = object: AdListener() {
            override fun onAdClicked() {
                showLog("Code to be executed when the user clicks on an ad.")
            }

            override fun onAdClosed() {
                showLog("Code to be executed when the user is about to return" +
                        " to the app after tapping on an ad. ")
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                showLog("Code to be executed when an ad request fails.")
            }

            override fun onAdImpression() {
                showLog("Code to be executed when an impression is recorded for an ad.")
            }

            override fun onAdLoaded() {
                showLog("Code to be executed when an ad finishes loading.")
            }

            override fun onAdOpened() {
                showLog("Code to be executed when an ad opens an overlay that covers the screen.")
            }
        }
    }

    fun showMessage(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    fun showLog(msg:String){
        Log.e("@@@", "showLog: $msg")
    }
}
