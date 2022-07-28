package com.meuapp.rr.myapplication1

import android.app.Activity
import android.os.Bundle
import com.meuapp.rr.myapplication1.R
import android.media.MediaPlayer
import android.content.Intent
import android.os.Handler
import android.view.View
import com.meuapp.rr.myapplication1.MainActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

/**
 * Created by RR on 18/02/2019.
 */
class About : Activity(), Runnable {
    private val DELAY = 14000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sobre)
        val player = MediaPlayer.create(this, R.raw.thankyou)
        val player2 = MediaPlayer.create(this, R.raw.boxingbell)
        val intent = Intent(this, MainActivity::class.java)
        player.start()
        val handler = Handler()
        handler.postDelayed(this, DELAY.toLong())
        val button = findViewById<View>(R.id.button) as Button
        button.setOnClickListener {
            player2.start()
            startActivity(intent)
            finish()
        }
    }

    override fun run() {
        val image = findViewById<View>(R.id.imageView) as ImageView
        val animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein)
        animFadeIn.reset()
        image.clearAnimation()
        image.startAnimation(animFadeIn)
    }

    override fun onBackPressed() {
        // nao chame o super desse metodo
    }
}