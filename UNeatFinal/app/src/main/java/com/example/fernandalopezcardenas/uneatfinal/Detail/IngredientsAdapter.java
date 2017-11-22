package com.example.fernandalopezcardenas.uneatfinal.Detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by LuisVargas on 30/10/2017.
 */

public class IngredientsAdapter extends ArrayAdapter<String>{

    // arraylist de ingerdientes

    public IngredientsAdapter(Context context, ArrayList<String> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_car, parent, false);
        }
        // Lookup view for data population
        TextView Ingredient = (TextView) convertView.findViewById(R.id.ingredient);
        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

        // chekbox
        // checkbox.onvlueh
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    // meter ingredient (user) al arraylit global
                }
                else {
                    // quitas ingreiente (user) del arraylist global
                }
            }
        });
        // Populate the data into the template view using the data object
        Ingredient.setText(user);
        // Return the completed view to render on screen
        return convertView;
    }

    // metodo para regresar arraylist de iredientes global
}
