package com.example.jules.simonsay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//Declaro los botonoes los objetos de tipo Handler que son para los retardos,ademas del array botones que contiene los botones para pulsar con sus colores,los tiempos de encendido del boton
    //ademas del array MediaPlayer que contiene los sonidos.
    Button iniciar;
    Handler retardo1=new Handler();
    Handler retardo2=new Handler();
    Button[]botones;
    MediaPlayer sonido[];
    int numeros[];
    int ordenJugador[];
    int botonesPulsados=0;
    private int tiempoEncendido=400;
    private int tiempoApagado=800;
    boolean enabledPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//indico el tamaño del array de botones,de sonidos y cada sonido y boton con sus correspondientes sonidos y colores
        ordenJugador=new int[4];
        numeros=new int[4];
        botones=new Button[4];
        iniciar=(Button)findViewById(R.id.bplay);
        botones[0] = (Button) findViewById(R.id.bazul);
        botones[1] = (Button) findViewById(R.id.brojo);
        botones[2] = (Button) findViewById(R.id.bverde);
        botones[3] = (Button) findViewById(R.id.bamarillo);
        sonido=new MediaPlayer[4];
        sonido[0]=MediaPlayer.create(this,R.raw.ladrilloroto);
        sonido[1]=MediaPlayer.create(this,R.raw.moneda);
        sonido[2]=MediaPlayer.create(this,R.raw.salto);
        sonido[3]=MediaPlayer.create(this,R.raw.tmuerta);



    }

    //Este metodo hace que al pulsar un boton se ilumine con su color y sonido,la variable indice se usa para despues poder ser comprobado en el metodo Check
    public void Click(View v) {
        int indice;
        int id = v.getId();
        final Button b = (Button)v;

        if (id == R.id.bazul) {

            v.setBackgroundResource(R.color.azulEncendido);
            sonido[0].start();
            indice=0;

        } else if (id == R.id.brojo) {


            v.setBackgroundResource(R.color.rojoEncendido);
            sonido[1].start();
            indice=1;

        } else if (id == R.id.bverde) {


            v.setBackgroundResource(R.color.verdeEncendido);
            sonido[2].start();
            indice=2;

        } else {

            v.setBackgroundResource(R.color.amarilloEncendido);
            sonido[3].start();
            indice=3;

        }//El handler hace que una vez se enciendan los botones,pase ciertos milisegundo y se resete al color original
        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetear(b.getId());
            }
        },500);

        if(enabledPlay) {

            ordenJugador[botonesPulsados] = indice;
            botonesPulsados++;

            if (botonesPulsados == 4) {

                check();

            }
        }

    }

//El metodo iniciar ,inicia la secuencia de colores,el for genera cuatro numeros aleatorio comprendidos entre 0 y 4 y los guarda en el array de numeros,y con el delay hacemos que se encienda durante X milisegundos
    public void iniciar(View v) {
        enabledPlay=true;
        for (int i = 0; i < numeros.length; i++) {

            numeros[i] = (int) (Math.random() * 4);
            final Button b=botones[numeros[i]];
            System.out.println(numeros[i]);

            if(b.getId()==R.id.bazul){
                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.azulEncendido);
                        sonido[0].start();
                    }
                },tiempoEncendido);



            }else if(b.getId()==R.id.brojo){

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.rojoEncendido);
                        sonido[1].start();
                    }
                },tiempoEncendido);
            }else if(b.getId()==R.id.bverde){

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.verdeEncendido);
                        sonido[2].start();
                    }
                },tiempoEncendido);
            }else {

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.amarilloEncendido);
                        sonido[3].start();
                    }
                },tiempoEncendido);
            }

            final Button boton= b;

            retardo2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetear(boton.getId());
                }
            },tiempoApagado);

            tiempoEncendido+=300;
            tiempoApagado+=600;


        }


    }
//Resetear,mediante if comprobamos la id del boton y le indicamos que si esa id es la correcta pasan a tener el color original
    public void resetear(int id) {


        if (id == R.id.bazul) {

            botones[0].setBackgroundResource(R.color.azulApagado);


        } else if (id == R.id.brojo) {


            botones[1].setBackgroundResource(R.color.rojoApagado);


        } else if (id == R.id.bverde) {


            botones[2].setBackgroundResource(R.color.verdeApagado);


        } else {

            botones[3].setBackgroundResource(R.color.amarilloApagado);


        }
    }

//Check comprueba que si la secuencia de botones iniciada por el for es la misma que la pulsada por el jugador,nos manda un toast con un mensdaje de "ganando" y otro toast con un mensaje de "perdido"


    public void check(){
            Intent intento=new Intent(MainActivity.this,MensajeActivity.class);
        String mensaje="";
        int acertados=0;

        for(int i=0;i<numeros.length;i++){

            if(numeros[i]!=ordenJugador[i]){


                Toast.makeText(this, "Has perdido", Toast.LENGTH_SHORT).show();
                mensaje ="oh has perdido";
                intento.putExtra("VICTORIA_DERROTA",mensaje);
                startActivity(intento);
            }else{

                acertados++;

            }


            if(acertados==4){

                Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
                mensaje="has ganadoooo";
                intento.putExtra("VICTORIA_DERROTA",mensaje);
            }
            enabledPlay=false;
            botonesPulsados=0;
            numeros=new int[4];
            ordenJugador=new int[4];

        }




    }
//COMIT FINAL
}
