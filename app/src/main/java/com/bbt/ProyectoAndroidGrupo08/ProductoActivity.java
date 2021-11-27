package com.bbt.ProyectoAndroidGrupo08;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ProductoActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin; //DB
    private SQLiteDatabase db; //DB
    private Cursor filas;
    private ContentValues cv; //DB
    private EditText et1, et2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        //activar soporte action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Funcion para flecha regresar
        getParametros();

        //DB
        admin = new MyDBSQLiteHelper(this, variables.nomDB, null, variables.version);

        et1 = (EditText) findViewById(R.id.input_nombre);
        et2 = (EditText) findViewById(R.id.input_descripcion);
    }

    public void getParametros(){
        /*Bundle extras = getIntent().getExtras();
        String msg = extras.getString("msg");
        int year = extras.getInt("year");
        Toast.makeText(this, msg + " " + year, Toast.LENGTH_LONG).show();*/
    }

    public void agregarDatos(View view){ //Database
        String nom = et1.getText().toString();
        String des = et2.getText().toString();

        db = admin.getWritableDatabase();
        cv = new ContentValues();
        cv.put("nombre", nom);
        cv.put("descripcion",des);

        long reg = db.insert("producto", null, cv);

        if (reg != -1){
            Toast.makeText(this, "¡Registro almacenado exitosamente!", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
        }else{
            Toast.makeText(this, "¡El registro no se pudo almacenar!", Toast.LENGTH_SHORT).show();
        }


    }

    public void ListarDatos(View view){
        /*db = admin.getReadableDatabase();
        filas = db.rawQuery("SELECT * FROM producto", null);

        while(filas.moveToNext()){
            Toast.makeText(this, filas.getInt(0) + "-" +
                    filas.getString(1) + "-"+
                    filas.getString(2), Toast.LENGTH_SHORT).show();
        }
        db.close();*/
        Intent intent = new Intent(this , ListViewActivity.class);
        intent.putExtra("nomtabla", "producto");
        startActivity(intent);
    }

    public void eliminarDatos(View view){
        String nom = et1.getText().toString();

        db = admin.getWritableDatabase();
        if (!nom.equals("")){
            //int reg = db.delete("producto", "mombre='"+nom+"'", null);
            //String[] args = new String[]{nom};
            //int reg = db.delete("producto", "mombre=?", args);
            db.execSQL("DELETE FROM producto WHERE nombre="+"'"+nom+"'");
            /*if(reg == 0){
                Toast.makeText(this, "¡El registro no se pudo eliminar!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "¡El registro se ha eliminado exitosamente!", Toast.LENGTH_SHORT).show();
            }*/
        }else{
            Toast.makeText(this, "¡Por favor escriba el nombre del producto!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editarDatos(View view){
        String nom = et1.getText().toString();
        String des = et2.getText().toString();

        db = admin.getWritableDatabase();
        //cv = new ContentValues();

        //cv.put("descripcion",des);
        db.execSQL("UPDATE producto SET descripcion='"+des+"' WHERE nombre='"+nom+"'");
        /*int reg =db.update("producto", cv, "mombre='"+nom+"'", null);

        if(reg == 0){
            Toast.makeText(this, "¡El registro no se pudo eliminar!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "¡El registro se ha eliminado exitosamente!", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void buscarDatos(View view){
        String nom = et1.getText().toString();

        if (!nom.equals("")) {
            db = admin.getReadableDatabase();
            filas = db.rawQuery("SELECT * FROM producto WHERE nombre='" + nom + "'", null);
            if (filas.moveToFirst()) {
                et2.setText(filas.getString(2));
            } else {
                Toast.makeText(this, "¡El producto no existe!", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "¡Por favor escriba el nombre del producto!", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarDatos(View view){
        et1.setText("");
        et2.setText("");
    }

    public void goToActivityMain(View view){
        Intent newIntent = new Intent( this, MainActivity.class);

        //paso de parametros
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // inicializacion de nueva activity
        startActivity(newIntent);
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

    //Administrador base de datos


}