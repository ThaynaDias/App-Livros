package com.uninassau.unilivros.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.uninassau.unilivros.R;
import com.uninassau.unilivros.banco.BancoContralador;

import java.util.ArrayList;
import java.util.List;

public class telaPesquisa extends AppCompatActivity {

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa);

        btnVoltar = findViewById(R.id.btnVoltar);
        TableLayout tb = findViewById(R.id.tbPesquisar);

        Intent it = getIntent();
        int tipo = it.getIntExtra("tipo", 0);
        String busca = it.getStringExtra("busca");
        List<ContentValues> lista = new ArrayList<>();
        BancoContralador bd = new BancoContralador(telaPesquisa.this);

        if(tipo == R.id.rbTitulo){
            lista = bd.pesquisarPorTitulo(busca);
        } else if(tipo == R.id.rbAno){
            lista = bd.pesquisarPorAno(Integer.parseInt(busca));
        } else if(tipo == R.id.rbTodos){
            lista = bd.pesquisarPorTodos();
        }

        if(lista != null){ // irá preeencher a tabela com os valores da consulta do banco de dados
            if(lista.size() > 0){
                for(ContentValues cv : lista){ // irá criar as linhas a cada iteração
                    TableRow tr = new TableRow(telaPesquisa.this);
                    TextView col1 = new TextView(telaPesquisa.this);
                    col1.setText(String.valueOf(cv.getAsInteger("id")));
                    tr.addView(col1);

                    TextView col2 = new TextView(telaPesquisa.this);
                    col2.setText(cv.getAsString("titulo"));
                    tr.addView(col2);

                    TextView col3 = new TextView(telaPesquisa.this);
                    col3.setText(cv.getAsString("autor"));
                    tr.addView(col3);

                    TextView col4 = new TextView(telaPesquisa.this);
                    col4.setText(String.valueOf(cv.getAsInteger("ano")));
                    tr.addView(col4);

                    tb.addView(tr);
                }
            }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}