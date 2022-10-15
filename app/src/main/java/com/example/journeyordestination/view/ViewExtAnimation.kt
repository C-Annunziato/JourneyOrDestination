package com.example.journeyordestination.view

import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.journeyordestination.R


fun View.slideUp(animTime: Long, startOffset: Long){
    val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up_animation).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}

fun View.slideDown(animTime: Long, startOffset: Long){
    val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideDown)
}