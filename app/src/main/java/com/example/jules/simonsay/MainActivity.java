package com.example.jules.simonsay;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button iniciar;
    Handler retardo1=new Handler();
    Handler retardo2=new Handler();
    Button[]botones;

    int numeros[];
    int ordenJugador[];
    int botonesPulsados=0;
    private int tiempoEncendido=1000;
    private int tiempoApagado=1500;
    boolean enabledPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordenJugador=new int[4];
        numeros=new int[4];
        botones=new Button[4];
        iniciar=(Button)findViewById(R.id.start);
        botones[0] = (Button) findViewById(R.id.b_azul);
        botones[1] = (Button) findViewById(R.id.b_rojo);
        botones[2] = (Button) findViewById(R.id.b_verde);
        botones[3] = (Button) findViewById(R.id.b_amarillo);



    }


    public void Clickar(View v){
        enabledPlay=true;
        for (int i = 0; i < numeros.length; i++) {

            numeros[i] = (int) (Math.random() * 4);
            final Button b=botones[numeros[i]];
            System.out.println(numeros[i]);

            if(b.getId()==R.id.b_azul){

                        b.setBackgroundResource(R.color.azulEncendido);





            }else if(b.getId()==R.id.b_rojo){


                        b.setBackgroundResource(R.color.rojoEncendido);


            }else if(b.getId()==R.id.b_verde){


                        b.setBackgroundResource(R.color.verdeEncendido);


            }else {



                        b.setBackgroundResource(R.color.amarilloEncendido);


            }



    }






    }
}
