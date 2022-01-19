package com.uninassau.unilivros.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uninassau.unilivros.R;
import com.uninassau.unilivros.banco.BancoContralador;

public class telaCadastro extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnVoltar;
    private EditText edtTitulo;
    private EditText edtAutor;
    private EditText edtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtAno = findViewById(R.id.edtAno);
        edtAutor = findViewById(R.id.edtAutor);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                String titulo = edtTitulo.getText().toString();  // obtendo valores do componente interface
                String autor = edtAutor.getText().toString();  // obtendo valores do componente interface
                String ano = edtAno.getText().toString(); // obtendo valores do componente interface

                cv.put("titulo", titulo); // inserindo valores na lista cv
                cv.put("autor", autor);
                cv.put("ano", ano);

                BancoContralador bd = new BancoContralador(telaCadastro.this); // variável para manipulação do banco
                String msg = "";

                // outra maneira de fazer a inserção do dados na base
                // long res = db.inserir (cv)
                // if(res > 0 ){

                if(bd.inserir(cv) > 0 ){  // inserção do dados na base
                    msg = "Operação realizada com sucesso!";
                    edtTitulo.setText("");
                    edtAutor.setText("");
                    edtAno.setText("");
                    edtTitulo.requestFocus();
                } else {
                    msg = "Ocorreu um erro durante a operação!";
                }
                Toast.makeText(telaCadastro.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}