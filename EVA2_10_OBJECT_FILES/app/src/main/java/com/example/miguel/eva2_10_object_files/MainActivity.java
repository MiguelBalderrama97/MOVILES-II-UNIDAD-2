package com.example.miguel.eva2_10_object_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button btnSafe;
    private EditText etxtName, etxtApe;
    private RadioButton rbMas, rbFem, rbOtro;
    private TextView txtData;

    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSafe = findViewById(R.id.btnSafe);
        etxtName = findViewById(R.id.etxtName);
        etxtApe = findViewById(R.id.etxtApe);
        rbMas = findViewById(R.id.rbMas);
        rbFem = findViewById(R.id.rbFem);
        rbOtro = findViewById(R.id.rbOtro);
        txtData = findViewById(R.id.txtData);

        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
                leer();
            }
        });
    }

    public void guardar(){

        String name = etxtName.getText().toString();
        String ape = etxtApe.getText().toString();
        int gener = 0;
        if(rbMas.isChecked()){
            gener = 1;
        }else if(rbFem.isChecked()){
            gener = 2;
        }else if(rbOtro.isChecked()){
            gener = 3;
        }

        Personas personas = new Personas(name, ape, gener);

        try {
            fileOutputStream = openFileOutput("datos.xxx",0);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(personas);
            objectOutputStream.writeObject(new Personas());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void leer(){
        try {
            fileInputStream = openFileInput("datos.xxx");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Personas personas = (Personas) (objectInputStream.readObject());
            while(true){
                txtData.append("Nombre: " + personas.getNombre() + " " + personas.getApellido() + "\n");
                txtData.append("Genero: " + personas.getGenero() + "\n");
                txtData.append("----------------\n");
                personas = (Personas) (objectInputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Personas implements Serializable {
    private String nombre;
    private String apellido;
    private int genero;

    public Personas(){
        this.nombre = "Miguel";
        this.apellido = "Balderrama";
        this.genero = 1;
    }

    public Personas(String nombre, String apellido, int genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
