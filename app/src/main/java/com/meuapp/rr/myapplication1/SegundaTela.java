package com.meuapp.rr.myapplication1;

import android.media.MediaPlayer;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;


public class SegundaTela extends AppCompatActivity {

    int count2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controle--;
        setContentView(R.layout.segunda_tela);
        final MediaPlayer player = MediaPlayer.create(this, R.raw.enchecopo);
        player.start();

        Intent it = getIntent();
        if(it!= null)
        {
            Bundle params = it.getExtras();
            if(params != null)
            {
                count2 = params.getInt("count" , count2);
            }
        }

        final TextView resultadoFinal = (TextView) findViewById(R.id.resultado);


       resultadoFinal.setText(" " + count2);
       //Associa uma imagem com o numero da contagem e a carrega.
        //ImageView img = (ImageView) findViewById(R.id.emblem+count2);
        //img.setVisibility((View.VISIBLE));
    }

    protected void onPause() {
        super.onPause();

        final MediaPlayer player = MediaPlayer.create(this, R.raw.enchecopo);
        player.stop();
        finish();
    }
}
