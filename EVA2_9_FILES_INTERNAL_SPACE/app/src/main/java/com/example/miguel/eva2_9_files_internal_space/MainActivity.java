package com.example.miguel.eva2_9_files_internal_space;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etxtInfo;
    private Button btnDisplay;
    private TextView txtInfo;

    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private OutputStream outputStream;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;

    private final String NAME_FILE = "mi_archivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtInfo = findViewById(R.id.etxtInfo);
        txtInfo = findViewById(R.id.txtInfo);
        btnDisplay = findViewById(R.id.btnDisplay);

        try{
            String sCade;
            inputStream = openFileInput(NAME_FILE);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while((sCade = bufferedReader.readLine()) != null){
                txtInfo.setText(sCade);
                txtInfo.append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInfo.setText("");
                try{
                    String[] cade = etxtInfo.getText().toString().split("\n");
                    outputStream = openFileOutput(NAME_FILE, 0);
                    outputStreamWriter = new OutputStreamWriter(outputStream);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);

                    for(int i = 0; i < cade.length; i++){
                        bufferedWriter.append(cade[i]);
                        bufferedWriter.append("\n");
                    }

                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
