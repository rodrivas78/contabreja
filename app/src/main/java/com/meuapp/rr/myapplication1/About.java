package com.meuapp.rr.myapplication1;

import android.app.Activity;
import java.lang.Runnable;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by RR on 18/02/2019.
 */
public class About extends Activity implements Runnable {

    private final int DELAY = 14000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sobre);
        final MediaPlayer player = MediaPlayer.create(this, R.raw.thankyou);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.boxingbell);
        final Intent intent = new Intent(this,MainActivity.class);
        player.start();

        Handler handler = new Handler();
        handler.postDelayed(this, DELAY);


    Button button = (Button)findViewById(R.id.button);

    button.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
            player2.start();
            startActivity(intent);
            finish();
        }
    });

    }

    @Override
    public void run(){

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animFadeIn.reset();
        image.clearAnimation();
        image.startAnimation(animFadeIn);
    }

    @Override
    public void onBackPressed() {
        // nao chame o super desse metodo
    }
}
