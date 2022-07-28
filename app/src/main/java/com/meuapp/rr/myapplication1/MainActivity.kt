//App by rodrivas78 - 2019
// versão 1.3_2
//Nota da versao: igual a 1.3 mas com modificaçao na inicialização dos objetos MediaPlayer. Aqui
// todos como variaveis globais
package com.meuapp.rr.myapplication1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.meuapp.rr.myapplication1.R
import android.content.Intent
import com.meuapp.rr.myapplication1.SegundaTela
import android.media.MediaPlayer
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.meuapp.rr.myapplication1.MainActivity

//import android.support.v7.app.ActionBarActivity;
//public class MainActivity extends Activity {
//public class MainActivity extends ActionBarActivity implements Runnable{
class MainActivity : AppCompatActivity(), Runnable {
    var count //conta a quantidade de cervejas, isto eh, os cliques na garrafa
            = 0
    private val DELAY = 1000
    private var handler: Handler? = null
    private var on = false
    private val lastBeer = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LayoutInflater layoutInflater = null;
        handler = Handler()
        val intent = Intent(this, SegundaTela::class.java)
        val check = findViewById<View>(R.id.checkBox) as CheckBox
        val button2 = findViewById<View>(R.id.button2) as Button
        //Carrega a imagem numero 1
        val image2 = findViewById<View>(R.id.cerva1) as ImageView
        val image3 = findViewById<View>(R.id.proibido) as ImageView
        val textResultado = findViewById<View>(R.id.textoResultado) as TextView
        val player = MediaPlayer.create(this, R.raw.abrindo4)
        val player2 = MediaPlayer.create(this, R.raw.alert1)
        val player3 = MediaPlayer.create(this, R.raw.ding2)

        //Checkbox  SAIDEIRA
        check.setOnCheckedChangeListener { buttonView, isChecked ->
            val concorda = check.isChecked
            //Toca campainha ao ao chhcar checkbox
            if (concorda) {
                //agora (v1.3) faz o som de abertura: "abrindo4" e não "ding"
                player.start()
                count++
                val img2 = findViewById<View>(R.id.cerva2) as ImageView
                val img1 = findViewById<View>(R.id.cerva1) as ImageView
                img2.visibility = View.VISIBLE
                img1.visibility = View.INVISIBLE
                fazHora()
                minhaFuncao3()
                //alteracao 20/02
                //lastBeer = true;
            } else {
                //toca a campainha: "ding"
                player3.start()
                //Faz o simbolo de proibido sumir
                image3.visibility = View.GONE
                //chama Toast. Saideira desabilitada.
                minhaFuncao2()
            }
        }

        //Imagem clicavel "cerv6"
        image2.setOnClickListener {
            if (!check.isChecked) {
                player.start()
                count++
                val img2 = findViewById<View>(R.id.cerva2) as ImageView
                val img1 = findViewById<View>(R.id.cerva1) as ImageView
                img2.visibility = View.VISIBLE
                img1.visibility = View.INVISIBLE
                fazHora()
                //alteracao 20/02
                //lastBeer=false;
            } else {  //Se o checkBox estiver ON (habilitado)

                // Mostra o sinal de proibido e soa o alarme
                image3.visibility = View.VISIBLE
                //Toca alarme
                player2.start()
                //Msg: "Modo saideira ativo. Desabilite..."
                minhaFuncao4()
            }
        }

        //Botao "TOTAL", vai para a segunda_tela
        button2.setOnClickListener {
            val params = Bundle()
            params.putInt("count", count)
            //passa o valor de count para a proxima tela
            intent.putExtras(params)
            startActivity(intent)
        }
    } //Fim de onCreate

    //Cuida do Handler/Runnable, para contagem de tempo
    override fun run() {
        if (on) {
            val img2 = findViewById<View>(R.id.cerva2) as ImageView
            val img1 = findViewById<View>(R.id.cerva1) as ImageView
            img2.visibility = View.INVISIBLE
            img1.visibility = View.VISIBLE
        }
    }

    //Funcao que mantem a segunda imagem visivel por algum tempo (DELAY)
    fun fazHora() {
        handler!!.postDelayed(this, DELAY.toLong())
        on = true
    }

    fun minhaFuncao2() {
        Toast.makeText(this, "Saideira desativada. Bora beber mais!!", Toast.LENGTH_LONG).show()
    }

    fun minhaFuncao3() {
        Toast.makeText(this, "Saideira contabilizada!!", Toast.LENGTH_LONG).show()
    }

    fun minhaFuncao4() {
        Toast.makeText(
            this,
            "Modo saideira ativo. Desabilite para adicionar mais cervejas!!",
            Toast.LENGTH_LONG
        ).show()
    }

    //Daqui para baixo partes referentes ao Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //Desabilita botao voltar
    override fun onBackPressed() {
        // não chame o super desse método
    }

    //Funcao implementada na tentativa de sanar bug de audio
    override fun onStop() {
        super.onStop()
        val player = MediaPlayer.create(this, R.raw.abrindo4)
        val player2 = MediaPlayer.create(this, R.raw.alert1)
        val player3 = MediaPlayer.create(this, R.raw.ding2)
        val player4 = MediaPlayer.create(this, R.raw.boxingbell)
        val player5 = MediaPlayer.create(this, R.raw.menosuma)
        val player6 = MediaPlayer.create(this, R.raw.lessone2)
        player.stop()
        player2.stop()
        player3.stop()
        player4.stop()
        player5.stop()
        player6.stop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            val player6 = MediaPlayer.create(this, R.raw.lessone2)
            player6.start()
            //return true;
        }
        if (id == R.id.menos_uma) {
            if (count >= 1) {
                count--
                Toast.makeText(this, "Subtraindo uma cerveja da contagem.", Toast.LENGTH_LONG)
                    .show()
                val player5 = MediaPlayer.create(this, R.raw.menosuma)
                player5.start()
            } else {
                Toast.makeText(this, "Total = 0 (Zero)", Toast.LENGTH_LONG).show()
            }
            return true
        }
        if (id == R.id.nova_rodada) {
            //Zera a contagem, fecha e recarrega esta activity
            count = 0
            val player4 = MediaPlayer.create(this, R.raw.ding2)
            player4.start()
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}