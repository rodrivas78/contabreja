package com.meuapp.rr.myapplication1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.meuapp.rr.myapplication1.R
import android.media.MediaPlayer
import android.content.Intent
import android.view.View
import android.widget.TextView

//import android.support.v7.app.ActionBarActivity;
class SegundaTela : AppCompatActivity() {
    var count2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //controle--;
        setContentView(R.layout.segunda_tela)
        val player = MediaPlayer.create(this, R.raw.enchecopo)
        player.start()
        val it = intent
        if (it != null) {
            val params = it.extras
            if (params != null) {
                count2 = params.getInt("count", count2)
            }
        }
        val resultadoFinal = findViewById<View>(R.id.resultado) as TextView
        resultadoFinal.text = " $count2"
        //Associa uma imagem com o numero da contagem e a carrega.
        //ImageView img = (ImageView) findViewById(R.id.emblem+count2);
        //img.setVisibility((View.VISIBLE));
    }

    override fun onPause() {
        super.onPause()
        val player = MediaPlayer.create(this, R.raw.enchecopo)
        player.stop()
        finish()
    }
}