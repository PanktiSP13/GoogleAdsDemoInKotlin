package com.pankti.googleadsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.pankti.googleadsdemo.databinding.ActivityRewardedInterstitialAdBinding

class RewardedInterstitialAdActivity : AppCompatActivity(), OnUserEarnedRewardListener {

    private lateinit var binding: ActivityRewardedInterstitialAdBinding
    private var rewardedInterstitialAd: RewardedInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rewarded_interstitial_ad)
        initView()
        performClick()

    }

    private fun initView() {
        MobileAds.initialize(this) { initializationStatus -> loadAd() }
    }

    private fun performClick() {
        binding.btnShowRewardedAd.setOnClickListener { showAd() }
    }


    private fun loadAd() {
        RewardedInterstitialAd.load(this, "ca-app-pub-3940256099942544/5354046379",
            AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedInterstitialAd) {
                Log.e("@@@", "Ad was loaded.")
                rewardedInterstitialAd = ad

                rewardedInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.e("@@@", "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.e("@@@", "Ad dismissed fullscreen content.")
                        rewardedInterstitialAd = null
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Called when ad fails to show.
                        Log.e("@@@", "Ad failed to show fullscreen content.")
                        rewardedInterstitialAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.e("@@@", "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.e("@@@", "Ad showed fullscreen content.")
                    }
                }
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("@@@", adError.toString() ?: "")
                rewardedInterstitialAd = null
            }
        })

    }

    private fun showAd() {
        rewardedInterstitialAd?.let { ad ->
            ad.show(this@RewardedInterstitialAdActivity, this)
//            rewardedInterstitialAd = null

        } ?: run { loadAd() }
    }

    override fun onUserEarnedReward(p0: RewardItem) {
        val amount = p0.amount
        val type = p0.type
        binding.tvResult.text = "User earned reward. amount :$amount  type :$type"
        rewardedInterstitialAd = null
        Log.e("@@@", "User earned reward. amount : $amount type : $type")
    }
}