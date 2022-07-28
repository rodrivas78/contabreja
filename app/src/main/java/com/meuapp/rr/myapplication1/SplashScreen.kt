package com.meuapp.rr.myapplication1

import android.app.Activity
import android.os.Bundle
import com.meuapp.rr.myapplication1.R
import android.content.Intent
import com.meuapp.rr.myapplication1.MainActivity
import com.meuapp.rr.myapplication1.About
import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import android.widget.ImageView

/**
 * Created by RR on 13/02/2019.
 */
class SplashScreen : Activity(), Runnable {
    private val DELAY = 13000
    private var off = false
    private var easterOn = false
    var contador = 0
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        // Exibe o layout com a imagem...
        setContentView(R.layout.splash_screen)
        val intent = Intent(this, MainActivity::class.java)
        val intent2 = Intent(this, About::class.java)
        val image2 = findViewById<View>(R.id.bottransp) as ImageView
        val image = findViewById<View>(R.id.botaoeaster) as ImageView
        val player = MediaPlayer.create(this, R.raw.bottlesong)
        val player2 = MediaPlayer.create(this, R.raw.lessone2)
        player.start()

        //ativador do easteregg
        image.setOnClickListener {
            contador++
            //player2.start();
            if (contador == 15) {
                off = true
                easterOn = true
                player.stop()
                startActivity(intent2)
                finish()
            }
        }

        //botao transparente no canto inferior, chama o activity principal
        image2.setOnClickListener {
            off = true
            easterOn = true
            player.stop()
            startActivity(intent)
            finish()
        }

        // Solicita para o Handler executar o Runnable (this), fechando a Splash
        // Screen depois de alguns segundos
        val h = Handler()
        h.postDelayed(this, DELAY.toLong())
    }

    override fun run() {
        //Se o botao de avancar nao for pressionado off=false e assim sera chamada a proxima activity
        //apos o prazo definido em DELAY ser atingido
        if (!off || !easterOn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            off = true
        }
    }

    override fun onPause() {
        super.onPause()
        //Toast.makeText(this, "On Pause", Toast.LENGTH_LONG).show();
        val player2 = MediaPlayer.create(this, R.raw.ding2)
        player2.start()
        val player = MediaPlayer.create(this, R.raw.bottlesong)
        player.stop()
    }

    override fun onBackPressed() {
        // não chame o super desse método
    }

    override fun onStop() {
        super.onStop()
        //Toast.makeText(this, "On Pause", Toast.LENGTH_LONG).show();
        val player = MediaPlayer.create(this, R.raw.bottlesong)
        player.stop()
    }
}