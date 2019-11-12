package com.example.miguel.eva2_12_api_mysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class JSONAdapter extends ArrayAdapter<JSONObject> {

    private Context context;
    private int layout;
    private List<JSONObject> objects;

    public JSONAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layout = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView txtID, txtName, txtQuantity, txtPrice;

        if(convertView == null){ //NO EXISTE LA FILA, HAY QUE CREARLA
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(layout, parent, false);
        }

        txtID = convertView.findViewById(R.id.txtID);
        txtName = convertView.findViewById(R.id.txtName);
        txtQuantity = convertView.findViewById(R.id.txtQuantity);
        txtPrice = convertView.findViewById(R.id.txtPrice);

        try {
            txtID.setText(objects.get(position).getString("ProductID"));
            txtName.setText("Nombre: " + objects.get(position).getString("ProductName"));
            txtQuantity.setText("Cantidad: " + objects.get(position).getString("QuantityPerUnit"));
            txtPrice.setText("Precio Unitario: " + objects.get(position).getString("UnitPrice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
