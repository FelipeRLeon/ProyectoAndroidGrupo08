package com.bbt.ProyectoAndroidGrupo08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CamaraActivity extends AppCompatActivity {
    private ImageButton btnCamara;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView);

        btnCamara.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abrirCamara(view);
                    }
                });
    }
    public void abrirCamara(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
    }
}
