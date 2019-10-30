/**
 * Created by Miguel Balderrama on 10/24/2019
 * ClimaAdapter.java
 * Copyright © 2019 Miguel Balderrama. All rights reserved.
 */

package com.example.miguel.eva2_11_lista_clima.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miguel.eva2_11_lista_clima.R;
import com.example.miguel.eva2_11_lista_clima.models.Clima;
import java.util.List;

public class ClimaAdapter extends ArrayAdapter<Clima> {

    private Context cApp;
    private int iLayout;
    private List<Clima> acDatos;

    public ClimaAdapter(Context context, int resource, List<Clima> objects) {
        super(context, resource, objects);
        cApp = context;
        iLayout = resource;
        acDatos = objects;
    }


    /**
     *
     * @param position Current position of the item
     * @param convertView View of each item
     * @param parent
     * @return  Returns the inflated view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imgClima;
        TextView txtCiudad, txtClima, txtDesc, txtTemp;

        if(convertView == null){ //NO EXISTE LA FILA, HAY QUE CREARLA
            LayoutInflater layoutInflater = ((Activity)cApp).getLayoutInflater();
            convertView = layoutInflater.inflate(iLayout, parent, false);
        }

        imgClima = convertView.findViewById(R.id.imgClima);
        txtCiudad = convertView.findViewById(R.id.txtCiudad);
        txtDesc = convertView.findViewById(R.id.txtDesc);
        txtTemp = convertView.findViewById(R.id.txtTemp);
        txtClima = convertView.findViewById(R.id.txtClima);

        Clima cClima = acDatos.get(position);

        String clima = cClima.getClima();

        if(clima.equals("Soleado")){
            cClima.setImage(imgClima,"sunny.png");
        }else if(clima.equals("Nublado") || clima.equals("Nublaod")){
            cClima.setImage(imgClima,"rainy.png");
        }else if(clima.equals("Parcialmente nublado")){
            cClima.setImage(imgClima,"light_rain.png");
        }else if(clima.equals("Tornado")){
            cClima.setImage(imgClima,"tornado.png");
        }else if(clima.equals("Niebla")){
            cClima.setImage(imgClima,"snow.png");
        }

        txtCiudad.setText(cClima.getCiudad());
        txtDesc.setText(cClima.getDesc_clima());
        txtTemp.setText(cClima.getTemp()+" °C");
        txtClima.setText(cClima.getClima());

        return convertView;
    }

}
