package com.bbt.ProyectoAndroidGrupo08;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultar ActionBar
        getSupportActionBar().hide();

        TextView t1 = (TextView) findViewById(R.id.textView);
        t1.setText("¡So High!");
        t1.setTextSize(20);

        //Toast.makeText(this, "" + t1.getRight(), Toast.LENGTH_SHORT).show();

    }
}