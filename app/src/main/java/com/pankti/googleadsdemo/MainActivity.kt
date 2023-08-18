package com.pankti.googleadsdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pankti.googleadsdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        performClicks()
    }

    private fun performClicks() {
        binding.btnBannerAds.setOnClickListener { startActivity(Intent(this, BannerAdsActivity::class.java)) }
        binding.btnAdaptiveBannerAds.setOnClickListener { startActivity(Intent(this, AdaptiveBannerActivity::class.java)) }
        binding.btnInterstitialAds.setOnClickListener { startActivity(Intent(this, InterstitialAdsActivity::class.java)) }
        binding.btnNativeAds.setOnClickListener { startActivity(Intent(this, NativeAdsActivity::class.java)) }
        binding.btnRewardedAds.setOnClickListener { startActivity(Intent(this, RewardedAdsActivity::class.java)) }
        binding.btnRewardedInterstitialAds.setOnClickListener { startActivity(Intent(this, RewardedInterstitialAdActivity::class.java)) }

        binding.btnAppOpenAds.setOnClickListener {
            Toast.makeText(this, "It will show when your app launch", Toast.LENGTH_LONG).show()
        }
    }

}