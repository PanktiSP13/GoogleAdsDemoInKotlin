package com.pankti.googleadsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.pankti.googleadsdemo.databinding.ActivityRewardedAdsBinding

class RewardedAdsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRewardedAdsBinding
    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_rewarded_ads)

        initView()
        performClick()
    }

    private fun initView() {
//        loadAd()
    }



    private fun performClick() {

        binding.btnShowRewardedAd.setOnClickListener {
            loadAd()
        }

        rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.e("@@@", "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.e("@@@", "Ad dismissed fullscreen content.")
                rewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e("@@@", "Ad failed to show fullscreen content.")
                rewardedAd = null
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

    private fun loadAd(){
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917",
            adRequest, object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e("@@@", adError.toString() ?:"")
                    rewardedAd = null
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.e("@@@@", "Ad was loaded.")
                    rewardedAd = ad
                    showAd()
                }
            })
    }

    private fun showAd() {
        rewardedAd?.let { ad ->
            ad.show(this, OnUserEarnedRewardListener { rewardItem ->
                // Handle the reward.
                val rewardAmount = rewardItem.amount
                val rewardType = rewardItem.type
                binding.tvResult.text ="reward Amount : $rewardAmount reward type: $rewardType"
                rewardedAd = null
                Log.e("@@@", "showAd: reward Amount : $rewardAmount reward type: $rewardType")
                Log.e("@@@", "User earned the reward.")
            })
        } ?: run {
            loadAd()
            Log.e("@@@", "The rewarded ad wasn't ready yet.")
        }
    }
}