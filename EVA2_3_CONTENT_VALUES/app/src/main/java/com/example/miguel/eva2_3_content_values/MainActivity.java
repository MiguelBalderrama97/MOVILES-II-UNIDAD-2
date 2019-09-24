package com.example.miguel.eva2_3_content_values;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNom, txtApe;
    private TextView txtScroll;
    private Button btnInsert;

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        txtNom = findViewById(R.id.txtNom);
        txtApe = findViewById(R.id.txtApe);
        txtScroll = findViewById(R.id.txtScroll);

        sqLiteDatabase = openOrCreateDatabase("db_prueba", MODE_PRIVATE, null);

        try{
            sqLiteDatabase.execSQL("CREATE TABLE datos(datosid integer PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("nombre", txtNom.getText().toString());
                contentValues.put("apellido", txtApe.getText().toString());
                sqLiteDatabase.insert("datos",null,contentValues);
//                contentValues.clear();
            }
        });
    }
}
