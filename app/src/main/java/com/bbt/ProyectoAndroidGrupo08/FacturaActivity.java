package com.bbt.ProyectoAndroidGrupo08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FacturaActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin; //DB
    private SQLiteDatabase db; //DB
    private Cursor filas;
    private ContentValues cv; //DB

    private EditText et1, et2;
    private Spinner sp1;

    //LLenar un spinner con datos de la DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        //DB
        admin = new MyDBSQLiteHelper(this, variables.nomDB, null, variables.version);

        et1 = findViewById(R.id.txt_codigo);
        et2 = findViewById(R.id.txt_fecha);
        sp1 = findViewById(R.id.sp_productos);

        db = admin.getReadableDatabase();
        filas = db.rawQuery("SELECT _id, (nombre || ' - ' || marca) AS nom FROM producto ORDER BY nombre", null);
        MatrixCursor extras = new MatrixCursor(new String[]{"_id", "nom"});
        extras.addRow(new String[]{"-1", "Seleccione..."});
        Cursor[] cursores = {extras, filas};
        Cursor cursorExtendido = new MergeCursor(cursores);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_dropdown_item,
                cursorExtendido, new String[]{"nom"}, new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        db.close();

        //Escuchador spinner, cual es la seleccion
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String producto = cursorExtendido.getString(cursorExtendido.getColumnIndex("nom"));
                Toast.makeText(getApplicationContext(), position + "-" + producto, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }
}