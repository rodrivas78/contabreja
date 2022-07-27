package com.meuapp.rr.myapplication1;

/**
 * Created by RR on 13/02/2019.
 */

import java.lang.Runnable;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends Activity implements Runnable {

    private final int DELAY = 13000;
    private boolean off = false;
    private boolean easterOn = false;
    int contador;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Exibe o layout com a imagem...
        setContentView(R.layout.splash_screen);

        final Intent intent = new Intent(this,MainActivity.class);
        final Intent intent2 = new Intent(this,About.class);

        ImageView image2 = (ImageView)findViewById(R.id.bottransp);
        ImageView image = (ImageView)findViewById(R.id.botaoeaster);
        final MediaPlayer player = MediaPlayer.create(this, R.raw.bottlesong);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.lessone2);
        player.start();

        //ativador do easteregg
        image.setOnClickListener(new ImageView.OnClickListener() {
        public void onClick(View v) {
            contador++;
            //player2.start();
            if(contador==15){
                off=true;
                easterOn=true;
                player.stop();
                startActivity(intent2);
                finish();
            }
        }
    });

        //botao transparente no canto inferior, chama o activity principal
        image2.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                off = true;
                easterOn = true;
                player.stop();
                startActivity(intent);
                finish();
            }
        });

        // Solicita para o Handler executar o Runnable (this), fechando a Splash
        // Screen depois de alguns segundos
        Handler h = new Handler();
        h.postDelayed(this, DELAY);

    }

@Override
public void run(){
   //Se o botao de avancar nao for pressionado off=false e assim sera chamada a proxima activity
    //apos o prazo definido em DELAY ser atingido
    if(!off || !easterOn) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        off = true;
    }
}
    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "On Pause", Toast.LENGTH_LONG).show();
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.ding2);
        player2.start();
        final MediaPlayer player = MediaPlayer.create(this, R.raw.bottlesong);
        player.stop();
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "On Pause", Toast.LENGTH_LONG).show();
        final MediaPlayer player = MediaPlayer.create(this, R.raw.bottlesong);
        player.stop();
    }
}