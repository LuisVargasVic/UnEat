package com.example.fernandalopezcardenas.uneatfinal.Signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fernandalopezcardenas.uneatfinal.Client.MainClientActivity;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailPlate;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailUser;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.example.fernandalopezcardenas.uneatfinal.Restaurant.MainRestaurantActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public EditText nameUser;
    public Spinner rolUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        myRef = database.getReference("").child("users");

        nameUser = (EditText) findViewById(R.id.nameUser);
        rolUser = (Spinner) findViewById(R.id.rolUser);

        FirebaseAuth.getInstance().getCurrentUser().getUid();

        Button map = (Button) findViewById(R.id.buttonUser);
        map.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                writeNewUser(nameUser.getText().toString(),rolUser.getSelectedItem().toString());
                if (rolUser.getSelectedItem().toString().equals("Restaurant")){
                    writeNewRestaurant(nameUser.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Intent Restaurant = new Intent(DataActivity.this, MainRestaurantActivity.class);
                    startActivity(Restaurant);
                }
                else{
                    Intent Client= new Intent(DataActivity.this, MainClientActivity.class);
                    startActivity(Client);
                }
            }
        });
    }
    private void writeNewUser(String name, String rol) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailUser user = new DetailUser(name, rol);
        myRef = database.getReference("");
        myRef.child("users").child("data").child(userId).setValue(user);
    }
    private void writeNewRestaurant(String Name, String UID){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailRestaurant user = new DetailRestaurant(Name, UID);
        myRef = database.getReference("");
        myRef.child("users").child("restaurant").child(userId).setValue(user);
    }
}