package com.example.fernandalopezcardenas.uneatfinal.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fernandalopezcardenas.uneatfinal.Restaurant.MainRestaurantActivity;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliverActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        Button prep = (Button) findViewById(R.id.prep);
        prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNewOrderPrep();
                Toast.makeText(DeliverActivity.this,"Message Sent",Toast.LENGTH_SHORT).show();
                Intent MapOne= new Intent(DeliverActivity.this, MainRestaurantActivity.class);
                startActivity(MapOne);
            }
        });
        Button deliver = (Button) findViewById(R.id.deliver);
        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNewOrderDeliver();
                Toast.makeText(DeliverActivity.this,"Message Sent",Toast.LENGTH_SHORT).show();
                Intent MapOne= new Intent(DeliverActivity.this, MainRestaurantActivity.class);
                startActivity(MapOne);
            }
        });
    }
    private void modNewOrderPrep(){
        myRef = database.getReference("users").child("order").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1);
                    value1.setMessage("In Preparation");
                    String userIDC = value1.getUidclient().toString();
                    String userIDR = value1.getUidrestaurant().toString();
                    myRef = database.getReference("");
                    myRef.child("users").child("order").child(userIDR).setValue(areas);
                    myRef.child("users").child("paid").child(userIDC).setValue(areas);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Eror", "Something happened");
            }
        });
    }
    private void modNewOrderDeliver(){
        myRef = database.getReference("users").child("order").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1);
                    value1.setMessage("Order ready we are waiting for you");
                    String userIDC = value1.getUidclient().toString();
                    String userIDR = value1.getUidrestaurant().toString();
                    myRef = database.getReference("");
                    myRef.child("users").child("order").child(userIDR).setValue(areas);
                    myRef.child("users").child("paid").child(userIDC).setValue(areas);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Eror", "Something happened");
            }
        });
    }
}