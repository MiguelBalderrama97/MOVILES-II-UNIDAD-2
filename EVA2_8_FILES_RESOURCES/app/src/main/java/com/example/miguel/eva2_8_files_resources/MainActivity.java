package com.example.miguel.eva2_8_files_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView txtInfo;

    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txtInfo);

        inputStream = getResources().openRawResource(R.raw.mi_text);
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);

        try{
            String sCade;
            while((sCade = bufferedReader.readLine()) != null){
                txtInfo.setText(sCade);
                txtInfo.append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
