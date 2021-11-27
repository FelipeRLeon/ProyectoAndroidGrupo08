package com.bbt.ProyectoAndroidGrupo08;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin; //DB
    private SQLiteDatabase db; //DB
    private Cursor filas;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //activar soporte action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Funcion para flecha regresar

        admin = new MyDBSQLiteHelper(this, variables.nomDB, null, variables.version);
        
        lv = (ListView) findViewById(R.id.listView);
        ArrayList<String> listado = new ArrayList<String>();
        /*listado.add("uno");
        listado.add("dos");
        for(int i = 3; i<=20; i++){
            listado.add("Espacio " + i);
        }*/

        //Recibir datos de otra actividad
        Bundle extras = getIntent().getExtras();
        String nomtabla = extras.getString("nomtabla");
        //Convertir primera letra a mayuscula
        String nomTablaCapitalize = nomtabla.substring(0,1).toUpperCase(Locale.ROOT) + nomtabla.substring(1);

        //Cambiar titulo a activity
        setTitle(nomTablaCapitalize);

        db = admin.getReadableDatabase();

        if(nomtabla.equals("producto")){
            filas = db.rawQuery("SELECT * FROM producto", null);
            while(filas.moveToNext()){
                listado.add(filas.getInt(0) + "-" +
                        filas.getString(1) + "-"+
                        filas.getString(2));
            }
            db.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext() ,"Pos: "+ position + "-" + lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Inicio metodos flecha volver en el menu
    public void onBackPressed(){
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == android.R.id.home){
            onBackPressed();//Funcion para flecha regresar
        }
        return super.onOptionsItemSelected(menuItem);
    }
    //fin metodos flecha volver en el menu

}