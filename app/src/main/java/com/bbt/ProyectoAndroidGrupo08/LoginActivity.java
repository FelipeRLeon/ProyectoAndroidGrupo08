package com.bbt.ProyectoAndroidGrupo08;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.text.HtmlCompat;

public class LoginActivity extends AppCompatActivity {
    //Variables locales
    TextView t1, t2;
    EditText et1, et2;
    ImageView iv1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultar ActionBar
        getSupportActionBar().hide();

        t1 = (TextView) findViewById(R.id.textView);
        t1.setText("¡So High!");
        t1.setTextSize(20);

        et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        et2 = (EditText) findViewById(R.id.editTextTextPassword);

        iv1 = (ImageView) findViewById(R.id.imageView3);
        b1 = (Button) findViewById(R.id.iniciarSesion);


        t2 = (TextView) findViewById(R.id.textView2);
        //String link = "<a href='https://www.google.com/'>Recordar contraseña</a>";
        String texto = "Recordar contraseña";
        t2.setText(HtmlCompat.fromHtml(texto, HtmlCompat.FROM_HTML_MODE_COMPACT));
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    if (et1.getText().toString().equals("admin"))
                        Toast.makeText(LoginActivity.this,
                                "Su contraseña es: admin",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciarSesion(View view){
        if(et1.getText().toString().equals("admin") && et2.getText().toString().equals("admin")){
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
            finish();
        }
        else if(et1.getText().toString().equals("") && et2.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this,
                    "Por favor ingrese los datos",
                    Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        else if(et1.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this,
                    "Por favor ingrese el usuario",
                    Toast.LENGTH_SHORT).show();
            et2.requestFocus();
        }
        else if(et2.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this,
                    "Por favor ingrese la contraseña",
                    Toast.LENGTH_SHORT).show();
            et2.requestFocus();
        }
        else{
            Toast.makeText(LoginActivity.this,
                    "datos incorrectos",
                    Toast.LENGTH_SHORT).show();

        }
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        /*Toast.makeText(this,
                "Top" + iv1.getTop()+
                        "\nLeft" + iv1.getLeft(),
                Toast.LENGTH_SHORT).show();*/
        Toast.makeText(this,
                "Width" + b1.getWidth()+
                        "\nHeight" + b1.getHeight(),
                Toast.LENGTH_SHORT).show();
    }
}