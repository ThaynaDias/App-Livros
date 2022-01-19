package com.uninassau.unilivros.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.uninassau.unilivros.R;

import static com.uninassau.unilivros.R.id.btnCadastrar;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar, btnPesquisar;
    private RadioGroup rdgPesquisarPor;
    private EditText edtPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        rdgPesquisarPor =  findViewById(R.id.rdgPesquisarPor);
        edtPesquisar = findViewById(R.id.edtPesquisar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, telaCadastro.class);
                startActivity(it);
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, telaPesquisa.class);
                int id = rdgPesquisarPor.getCheckedRadioButtonId();
                String busca = edtPesquisar.getText().toString();
                it.putExtra("tipo", id);
                it.putExtra("busca", busca);
                startActivity(it);
            }
        });
    }

}