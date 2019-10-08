package com.example.miguel.eva2_7_sqlite_sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    private final String DIRECTORY = "eva2_7_sqlite_sdcard";
    private final String DATABASE = "prueba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        OBTENER LA RUTA DE LA SD CARD
        String sdPath = Environment.getExternalStorageDirectory().getPath();

        File fPath = new File(sdPath + "/" + DIRECTORY + "/");
        if(!fPath.exists()){
            fPath.mkdir(); // PRIMERA CEZ, CREAMOS LA RUTA
        }

        String sPath = sdPath + "/" + DIRECTORY + "/" + DATABASE;
        sqLiteDatabase = SQLiteDatabase.openDatabase(sPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    }
}
