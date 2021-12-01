package com.bbt.ProyectoAndroidGrupo08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class APIRestActivity extends AppCompatActivity {
    private static final String TAG = APIRestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apirest);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL githubEndpoint = new URL("https://api.github.com/");
                    HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app.v8.1");

                    myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    myConnection.setRequestProperty("Contact-Me", "usuario@gmail.com");

                    if(myConnection.getResponseCode() == 200) {
                        Log.e(TAG, "¡Conexion Exitosa!");
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while(jsonReader.hasNext()){
                            String key = jsonReader.nextName();
                            if (key.equals("organization_url")){
                                String value = jsonReader.nextName();
                                Log.e(TAG, value);
                                break;
                            }else{
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                        myConnection.disconnect();

                    }else {
                        Log.e(TAG, "¡No se pudo realizar la conexion!");
                    }
                } catch (MalformedURLException e) {
                    Log.e(TAG, "" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}