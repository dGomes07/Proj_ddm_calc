package com.example.calculadora;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class ResultadoActivity extends AppCompatActivity {

    TextView txt_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txt_res = findViewById(R.id.txtResultadoFinal);

        String resultado = getIntent().getStringExtra("res");
        txt_res.setText(resultado);
    }
}
