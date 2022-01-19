package com.uninassau.unilivros.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoContralador extends SQLiteOpenHelper {

    //Atributos
    public static final String DATABASE_NAME = "biblioteca"; // nome do banco
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE_CATALOGO =
            "CREATE TABLE catalogo (" // criar o banco com o nome da tabela catalogo
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, " // campo
                    + "titulo TEXT, autor TEXT,"
                    + "ano INTEGER);";

    public BancoContralador (Context context){ // construtor da class, vai ter que chamar a super classe
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // cria o banco de dados

    }
    @Override
    public void onCreate(SQLiteDatabase db) { // esse metedo é que vai fazer a criação do nosso  banco
        db.execSQL(CREATE_TABLE_CATALOGO); // cria a tabela catalogo no banco
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // passar a condicao se existe a mesma versao do banco
            db.execSQL("DROP TABLE IF EXISTS catalogo"); // caso tenha o mesmo banco, ele exclui o banco e cria um novo. só se tiver o mesmo id..
            onCreate(db);
    }

    public long inserir(ContentValues cv){ // ContentValues trata - se para construir a lista de dados
        SQLiteDatabase db  = this.getWritableDatabase(); // getWritableDatabase trata - se leitura e escrita
        long id = db.insert("catalogo", null, cv); // deixa por padrão null
        return id;
    }

    public List<ContentValues> pesquisarPorTitulo(String titulo){ // vai pegar o parametro que for digitar no campo de edit
        String sql = "SELECT * FROM catalogo WHERE titulo LIKE ?"; // like é para caracteres
        String where [] = new String[]{"%"+titulo+"%"}; // outro metedo de ser utilizado é:  String w = "%"+titulo+"%";
        return pesquisar(sql, where);
    }

    public List<ContentValues> pesquisarPorAno(int ano){ // vai pegar o parametro que for digitar no campo de edit
        String sql = "SELECT * FROM catalogo WHERE ano=?"; // = é para inteiro
        String where [] = new String[]{String.valueOf(ano)}; // valueOf para converter para string
        return pesquisar(sql, where);
    }

    public List<ContentValues> pesquisarPorTodos(){ // vai pegar o parametro que for digitar no campo de edit
        String sql = "SELECT * FROM catalogo ORDER BY id"; // ORDER BY é para ordenar pelo id, se tirar vai ficar desorganizado conforme for colocado
        String where [] = null;
        return pesquisar(sql, where);
    }

    private List<ContentValues> pesquisar (String sql, String where[]) { // só para listar
        List<ContentValues> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // leitura
        Cursor c = db.rawQuery(sql, where); // consulta no BD

        if (c.moveToFirst()) {  // buscar dados na tabela
            do {
                ContentValues cv = new ContentValues(); // é uma lista que vai pegar da tabela
                cv.put("id", c.getInt(c.getColumnIndex("id"))); // colunas
                cv.put("titulo", c.getInt(c.getColumnIndex("titulo")));
                cv.put("autor", c.getInt(c.getColumnIndex("autor")));
                cv.put("ano", c.getInt(c.getColumnIndex("ano")));
                lista.add(cv);

            } while (c.moveToNext());
        }
        return lista;
    }

    public void alterarRegistro(int id , String titulo, String autor, int ano){

        ContentValues valores;
        String where;

        SQLiteDatabase db = this.getReadableDatabase();

        where = "id=" + id;

        valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("autor", autor);
        valores.put("ano", ano);

        db.update("catalogo", valores, where, null);
        db.close();

    }

    public void deletaRegistro(int id){
        String where = "id=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("catalogo", where, null);
        db.close();
    }
}
