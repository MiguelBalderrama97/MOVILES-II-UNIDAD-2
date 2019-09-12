package com.example.miguel.eva2_1_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =openOrCreateDatabase("myFriendsDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE HOLA (id int, nombre text)");
        db.execSQL("INSERT INTO HOLA VALUES (1,'Miguel')");
    }
}
