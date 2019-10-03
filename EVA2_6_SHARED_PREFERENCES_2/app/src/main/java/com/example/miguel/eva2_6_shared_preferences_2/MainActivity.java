package com.example.miguel.eva2_6_shared_preferences_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent inSecond;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String opcion1 = sharedPreferences.getString("opcion1","SIN ASIGNAR");
        boolean opcion2 = sharedPreferences.getBoolean("opcion2", false);
        boolean opcion3 = sharedPreferences.getBoolean("opcion3",false);

        Toast.makeText(this, opcion1 + " " +opcion2 + " " + opcion3, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mis_menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.config){
//            SE LANZA LA SECOND ACTIVITY
            inSecond = new Intent(this, SecondActivity.class);
            startActivity(inSecond);
        }
        return super.onOptionsItemSelected(item);
    }
}
