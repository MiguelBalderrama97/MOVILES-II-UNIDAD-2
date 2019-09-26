package com.example.miguel.eva2_3_content_values;

import android.content.ContentValues;
import android.database.Cursor;
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
    private TextView txtData;
    private Button btnInsert;

    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        txtNom = findViewById(R.id.txtNom);
        txtApe = findViewById(R.id.txtApe);
        txtData = findViewById(R.id.txtScroll);

        sqLiteDatabase = openOrCreateDatabase("db_prueba", MODE_PRIVATE, null);

        try{
            sqLiteDatabase.execSQL("CREATE TABLE datos(datosid integer PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM datos", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            txtData.append(cursor.getString(cursor.getColumnIndex("nombre"))+" ");
            txtData.append(cursor.getString(cursor.getColumnIndex("apellido")));
            txtData.append("\n");
            cursor.moveToNext();
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("nombre", txtNom.getText().toString());
                contentValues.put("apellido", txtApe.getText().toString());
                sqLiteDatabase.insert("datos",null,contentValues);

                cursor = sqLiteDatabase.rawQuery("SELECT * FROM datos", null);
                cursor.moveToLast();
                txtData.append(cursor.getString(cursor.getColumnIndex("nombre"))+" ");
                txtData.append(cursor.getString(cursor.getColumnIndex("apellido")));
                txtData.append("\n");

                txtNom.setText("");
                txtApe.setText("");
            }
        });
    }
}
