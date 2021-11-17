package com.mintic.helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToActivityProducto(View view){
        Intent newIntent = new Intent( this, ProductoActivity.class);

        //paso de parametros
        newIntent.putExtra("msg", "HolaEter");
        newIntent.putExtra("year", 2021);

        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // inicializacion de nueva activity
        startActivity(newIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if(id == R.id.mnu_producto){
            Intent newIntent = new Intent( this, ProductoActivity.class);
            newIntent.putExtra("msg", "HolaEter");
            newIntent.putExtra("year", 2021);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }else if(id == R.id.mnu_categoria){
            Intent newIntent = new Intent( this, CategoriaActivity.class);
            newIntent.putExtra("msg", "HolaEter");
            newIntent.putExtra("year", 2021);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //Confirmacion de salida
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert )
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Informacion")
                    .setMessage("¿Desea cerrar la aplicacion?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}