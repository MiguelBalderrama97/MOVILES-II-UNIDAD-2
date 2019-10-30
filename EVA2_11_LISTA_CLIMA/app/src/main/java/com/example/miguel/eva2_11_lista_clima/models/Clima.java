/**
 * Created by Miguel Balderrama on 10/24/2019
 * Clima.java
 * Copyright © 2019 Miguel Balderrama. All rights reserved.
 */

package com.example.miguel.eva2_11_lista_clima.models;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.example.miguel.eva2_11_lista_clima.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Clima {
    private String ciudad, clima, desc_clima;
    private String tempe;

    private Context context;

    public static final String DIRECTORY = "eva2_11_lista_clima";

    public Clima(String ciudad, String clima, String desc_clima, String tempe, Context context) {
        this.ciudad = ciudad;
        this.clima = clima;
        this.desc_clima = desc_clima;
        this.tempe = tempe;
        this.context = context;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getDesc_clima() {
        return desc_clima;
    }

    public void setDesc_clima(String desc_clima) {
        this.desc_clima = desc_clima;
    }

    public String getTemp() {
        return tempe;
    }

    public void setTemp(String temp) {
        this.tempe = temp;
    }

    /**
     *
     * @param imgClima View where the image is going to be
     * @param image Name of the sd card's file
     */
    public void setImage(ImageView imgClima, String image){
        String sdPath = Environment.getExternalStorageDirectory().getPath();
        File fPath = new File(sdPath + "/" + DIRECTORY + "/");

        if(!fPath.exists()) {
            fPath.mkdir();
        }

        Uri file = Uri.fromFile(new File(fPath, image));

        Picasso.with(context).load(file).placeholder(R.drawable.ic_launcher_background).into(imgClima);
    }
}
