package com.kdf.more.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.hubert.guide.NewbieGuide
import com.app.hubert.guide.model.GuidePage
import com.kdf.more.R
import com.kdf.more.databinding.ActivityRooikeGuideBinding

class RooikeGuideActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityRooikeGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRooikeGuideBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setGuide()
    }

    private fun setGuide() {
        //v7.0
        NewbieGuide.with(this)
            .setLabel("grid_view_guide")
            .alwaysShow(false)
            .setShowCounts(1)
            .addGuidePage(
                GuidePage.newInstance()
                    .setBackgroundColor(resources.getColor(R.color.guide_view_background))
                    .setLayoutRes(R.layout.guide_header_view_v7_1, R.id.guide_next_btn)
                    .setEverywhereCancelable(false)
            )
            .addGuidePage(
                GuidePage.newInstance()
                    .setBackgroundColor(resources.getColor(R.color.guide_view_background))
                    .setLayoutRes(R.layout.guide_header_view_v7_2, R.id.guide_next_btn)
                    .setEverywhereCancelable(false)
            )
            .addGuidePage(
                GuidePage.newInstance()
                    .setBackgroundColor(resources.getColor(R.color.guide_view_background))
                    .setLayoutRes(R.layout.guide_header_view_v7_3, R.id.guide_next_btn)
                    .setEverywhereCancelable(false)
            ).show()

        mBinding.apply {
            btnReset.setOnClickListener {
                NewbieGuide.resetLabel(applicationContext,"grid_view_guide")
            }
        }

    }

}