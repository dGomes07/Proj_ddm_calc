package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nota_p1, nota_p2, nota_t1, nota_t2;
    Button btn_calc;
    TextView txt_res;
    Spinner spn_mat;

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
        spn_mat = findViewById(R.id.spinnerMateria);

        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,
                R.array.materias, android.R.layout.simple_spinner_item);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_mat.setAdapter(adp);

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_p1 = nota_p1.getText().toString().trim();
                String s_p2 = nota_p2.getText().toString().trim();
                String s_t1 = nota_t1.getText().toString().trim();
                String s_t2 = nota_t2.getText().toString().trim();

                // validação básica
                if (s_p1.isEmpty() || s_p2.isEmpty() || s_t1.isEmpty() || s_t2.isEmpty()) {
                    txt_res.setText("Preencha todas as notas!");
                    return;
                }

                try {
                    // normaliza vírgula/ponto
                    s_p1 = s_p1.replace(",", ".");
                    s_p2 = s_p2.replace(",", ".");
                    s_t1 = s_t1.replace(",", ".");
                    s_t2 = s_t2.replace(",", ".");

                    double p1 = Double.parseDouble(s_p1);
                    double p2 = Double.parseDouble(s_p2);
                    double t1 = Double.parseDouble(s_t1);
                    double t2 = Double.parseDouble(s_t2);

                    // limite de notas
                    if (p1 < 0 || p1 > 10 || p2 < 0 || p2 > 10 ||
                            t1 < 0 || t1 > 10 || t2 < 0 || t2 > 10) {

                        txt_res.setText("Notas devem ser de 0 a 10");
                        return;
                    }

                    double med_p = (p1 + p2) / 2;
                    double med_t = (t1 + t2) / 2;

                    String mat = spn_mat.getSelectedItem().toString();

                    double peso_p, peso_t;

                    if (mat.equals("Matemática") || mat.equals("Português")) {
                        peso_p = 0.8;
                        peso_t = 0.2;
                    } else if (mat.equals("Geografia") || mat.equals("História")) {
                        peso_p = 0.7;
                        peso_t = 0.3;
                    } else {
                        peso_p = 0.5;
                        peso_t = 0.5;
                    }

                    double med_fin = (med_p * peso_p) + (med_t * peso_t);

                    String sit = (med_fin >= 6) ? "Aprovado" : "Reprovado";

                    String res = "Matéria: " + mat +
                            "\nMédia: " + String.format("%.2f", med_fin) +
                            "\nSituação: " + sit;

                    if (med_fin < 6) {
                        double falta = 6 - med_fin;
                        res += "\nFalta: " + String.format("%.2f", falta);
                    }

                    // envia pra segunda tela
                    Intent it = new Intent(MainActivity.this, ResultadoActivity.class);
                    it.putExtra("res", res);
                    startActivity(it);

                } catch (Exception e) {
                    txt_res.setText("Erro: digite números válidos");
                    e.printStackTrace();
                }
            }
        });
    }
}
