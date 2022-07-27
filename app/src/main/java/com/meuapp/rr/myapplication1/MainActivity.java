//App by rodrivas78 - 2019
// versão 1.3_2
//Nota da versao: igual a 1.3 mas com modificaçao na inicialização dos objetos MediaPlayer. Aqui
// todos como variaveis globais

package com.meuapp.rr.myapplication1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//public class MainActivity extends Activity {
//public class MainActivity extends ActionBarActivity implements Runnable{
    public class MainActivity extends AppCompatActivity implements Runnable{

    int count; //conta a quantidade de cervejas, isto eh, os cliques na garrafa
    private final int DELAY = 1000;
    private Handler handler;
    private boolean on;
    private boolean lastBeer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LayoutInflater layoutInflater = null;

        handler = new Handler();
        final Intent intent = new Intent(this,SegundaTela.class);

        final CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        Button button2 = (Button)findViewById(R.id.button2);
       //Carrega a imagem numero 1
        ImageView image2 = (ImageView)findViewById(R.id.cerva1);
        final ImageView image3 = (ImageView)findViewById(R.id.proibido);
        final TextView textResultado = (TextView) findViewById(R.id.textoResultado);

        final MediaPlayer player = MediaPlayer.create(this, R.raw.abrindo4);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.alert1);
        final MediaPlayer player3 = MediaPlayer.create(this, R.raw.ding2);

        //Checkbox  SAIDEIRA
        check.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean concorda = check.isChecked();
                //Toca campainha ao ao chhcar checkbox
                if (concorda) {
                    //agora (v1.3) faz o som de abertura: "abrindo4" e não "ding"
                    player.start();
                    count++;
                    ImageView img2 = (ImageView) findViewById(R.id.cerva2);
                    ImageView img1 = (ImageView) findViewById(R.id.cerva1);
                    img2.setVisibility((View.VISIBLE));
                    img1.setVisibility((View.INVISIBLE));
                    fazHora();
                    minhaFuncao3();
                    //alteracao 20/02
                    //lastBeer = true;
                } else {
                    //toca a campainha: "ding"
                    player3.start();
                    //Faz o simbolo de proibido sumir
                    image3.setVisibility(View.GONE);
                    //chama Toast. Saideira desabilitada.
                    minhaFuncao2();
                }
            }
        });

        //Imagem clicavel "cerv6"
        image2.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                if (!check.isChecked()) {
                    player.start();
                    count++;
                    ImageView img2 = (ImageView) findViewById(R.id.cerva2);
                    ImageView img1 = (ImageView) findViewById(R.id.cerva1);
                    img2.setVisibility((View.VISIBLE));
                    img1.setVisibility((View.INVISIBLE));
                    fazHora();
                    //alteracao 20/02
                    //lastBeer=false;
                    }
                 else {  //Se o checkBox estiver ON (habilitado)

                    // Mostra o sinal de proibido e soa o alarme
                    image3.setVisibility(View.VISIBLE);
                    //Toca alarme
                    player2.start();
                    //Msg: "Modo saideira ativo. Desabilite..."
                    minhaFuncao4();
                }
            }
        });

            //Botao "TOTAL", vai para a segunda_tela
            button2.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v) {
                    Bundle params = new Bundle();
                    params.putInt("count", count);
                    //passa o valor de count para a proxima tela
                    intent.putExtras(params);
                    startActivity(intent);
                }
            });

    } //Fim de onCreate

    @Override
    //Cuida do Handler/Runnable, para contagem de tempo
    public void run() {

        if (on) {
            ImageView img2 = (ImageView) findViewById(R.id.cerva2);
            ImageView img1 = (ImageView) findViewById(R.id.cerva1);
            img2.setVisibility((View.INVISIBLE));
            img1.setVisibility((View.VISIBLE));
        }
    }

    //Funcao que mantem a segunda imagem visivel por algum tempo (DELAY)
     public void fazHora(){
         handler.postDelayed(this,DELAY);
         on = true;
         }


    public void minhaFuncao2(){
        Toast.makeText(this, "Saideira desativada. Bora beber mais!!", Toast.LENGTH_LONG).show();
    }

    public void minhaFuncao3(){
        Toast.makeText(this, "Saideira contabilizada!!", Toast.LENGTH_LONG).show();

    }

    public void minhaFuncao4(){
        Toast.makeText(this, "Modo saideira ativo. Desabilite para adicionar mais cervejas!!", Toast.LENGTH_LONG).show();
    }

    //Daqui para baixo partes referentes ao Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Desabilita botao voltar
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    //Funcao implementada na tentativa de sanar bug de audio
    protected void onStop() {
        super.onStop();

        final MediaPlayer player = MediaPlayer.create(this, R.raw.abrindo4);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.alert1);
        final MediaPlayer player3 = MediaPlayer.create(this, R.raw.ding2);
        final MediaPlayer player4 = MediaPlayer.create(this, R.raw.boxingbell);
        final MediaPlayer player5 = MediaPlayer.create(this, R.raw.menosuma);
        final MediaPlayer player6 = MediaPlayer.create(this, R.raw.lessone2);
        player.stop();
        player2.stop();
        player3.stop();
        player4.stop();
        player5.stop();
        player6.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final MediaPlayer player6 = MediaPlayer.create(this, R.raw.lessone2);
            player6.start();
            //return true;
        }

        if (id == R.id.menos_uma) {

            if(count>=1) {
                count--;
                Toast.makeText(this, "Subtraindo uma cerveja da contagem.", Toast.LENGTH_LONG).show();
                final MediaPlayer player5 = MediaPlayer.create(this, R.raw.menosuma);
                player5.start();
            }
            else {
                Toast.makeText(this, "Total = 0 (Zero)", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        if (id == R.id.nova_rodada) {
            //Zera a contagem, fecha e recarrega esta activity
            count = 0;
           final MediaPlayer player4 = MediaPlayer.create(this, R.raw.ding2);
            player4.start();

            final Intent intent2 = new Intent(this,MainActivity.class);
            startActivity(intent2);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
