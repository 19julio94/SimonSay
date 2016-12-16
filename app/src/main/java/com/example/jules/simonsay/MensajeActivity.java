package com.example.jules.simonsay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MensajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        TextView mensaje=(TextView)findViewById(R.id.mensaje);
        Bundle extra= getIntent().getExtras();
        mensaje.setText(extra.getString("VICTORIA_DERROTA"));

    }


}
