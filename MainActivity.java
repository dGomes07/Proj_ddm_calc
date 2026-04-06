package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nota_p1, nota_p2, nota_t1, nota_t2;
    Button btn_calc;
    TextView txt_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nota_p1 = findViewById(R.id.notaP1);
        nota_p2 = findViewById(R.id.notaP2);
        nota_t1 = findViewById(R.id.notaT1);
        nota_t2 = findViewById(R.id.notaT2);
        btn_calc = findViewById(R.id.btnCalcular);
        txt_res = findViewById(R.id.resultado);

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_p1 = nota_p1.getText().toString();
                String s_p2 = nota_p2.getText().toString();
                String s_t1 = nota_t1.getText().toString();
                String s_t2 = nota_t2.getText().toString();


                if (s_p1.isEmpty() || s_p2.isEmpty() || s_t1.isEmpty() || s_t2.isEmpty()) {
                    txt_res.setText("Preencha todas as notas!");
                    return;
                }

                try {

                    double p1 = Double.parseDouble(s_p1.replace(",", "."));
                    double p2 = Double.parseDouble(s_p2.replace(",", "."));
                    double t1 = Double.parseDouble(s_t1.replace(",", "."));
                    double t2 = Double.parseDouble(s_t2.replace(",", "."));

                    double med_prov = (p1 + p2) / 2;
                    double med_trab = (t1 + t2) / 2;

                    double med_fin = (med_prov * 0.8) + (med_trab * 0.2);

                    String sit;

                    if (med_fin >= 7) {
                        sit = "Aprovado";
                    } else {
                        sit = "Reprovado";
                    }

                    txt_res.setText(
                            "Média: " + String.format("%.2f", med_fin) +
                                    "\nSituação: " + sit
                    );

                } catch (Exception e) {
                    txt_res.setText("Erro: digite apenas números válidos");
                }
            }
        });
    }
}