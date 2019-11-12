package com.example.miguel.eva2_12_api_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<JSONObject> miLista = new ArrayList<JSONObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        new MySQLAPIConnection().execute();
    }

    /**
     * CREATE CONNECTION
     */
    class MySQLAPIConnection extends AsyncTask<String, Void, String>{

        private final String MY_URL = "http://10.8.17.6:3000/Tasks";
        private String result = null;

        @Override
        protected String doInBackground(String... strings) {
            try {
                //LECTURA GET
//                URL path = new URL(MY_URL);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) path.openConnection();
//                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
//                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    result = bufferedReader.readLine();
//                }
                URL path = new URL(MY_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) path.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type","application/json;charset=utf-8");
                httpURLConnection.connect();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ProductName","ALAAAA");
                jsonObject.put("UnitPrice","85");

                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(jsonObject.toString().getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                while((result = bufferedReader.readLine()) != null){
                    stringBuffer.append(result);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            if(s != null){
//                try {
//                    JSONArray jsonArray = new JSONArray(s);
//                    for(int i = 0; i < jsonArray.length(); i++){
//                        JSONObject productos = jsonArray.getJSONObject(i);
//                        miLista.add(productos);
//                    }
//
//                    listView.setAdapter(new JSONAdapter(MainActivity.this, R.layout.json_item, miLista));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
