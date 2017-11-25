package com.example.fernandalopezcardenas.uneatfinal.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    private DetailCart item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        TextView name = findViewById(R.id.nameOrder);
        TextView price = findViewById(R.id.priceOrder);
        TextView hour = findViewById(R.id.hour);
        ListView listView = findViewById(R.id.listOrder);
        item = (DetailCart) getIntent().getSerializableExtra("place");
        name.setText(item.getCart().getName());
        price.setText(item.getCart().getPrice() + "");
        hour.setText(item.getPickuptime());
        ArrayAdapter adapter  = new ArrayAdapter(this, android.R.layout.simple_list_item_1, item.getCart().getIngredients());
        listView.setAdapter(adapter);

        Button prep = (Button) findViewById(R.id.prep);
        prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNewOrderPrep();
                Toast.makeText(DeliverActivity.this,"Message Sent",Toast.LENGTH_SHORT).show();
                Intent MapOne= new Intent(DeliverActivity.this, MainRestaurantActivity.class);
                startActivity(MapOne);
                finish();
            }
        });
        Button deliver = (Button) findViewById(R.id.deliver);
        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNewOrderDeliver();
                //removeFromOrder();
                Toast.makeText(DeliverActivity.this,"Message Sent",Toast.LENGTH_SHORT).show();
                Intent MapOne= new Intent(DeliverActivity.this, MainRestaurantActivity.class);
                startActivity(MapOne);
                finish();
            }
        });
    }
    private void modNewOrderPrep(){
        myRef = database.getReference("users").child("order").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    value1.setMessage("In Preparation");
                    if(value1.getUidrequest().equals(item.getUidrequest())){
                        String userIDC = value1.getUidclient();
                        String userIDO = value1.getUidrequest();
                        String userIDR = value1.getUidrestaurant();
                        myRef = database.getReference("");
                        myRef.child("users").child("order").child(userIDR).child(userIDO).setValue(value1);
                        myRef.child("users").child("paid").child(userIDC).child(userIDO).setValue(value1);

                    }

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
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    value1.setMessage("Order ready we are waiting for you");
                    if(value1.getUidrequest().equals(item.getUidrequest())){
                        String userIDC = value1.getUidclient();
                        String userIDO = value1.getUidrequest();
                        String userIDR = value1.getUidrestaurant();
                        myRef = database.getReference("");
                        myRef.child("users").child("order").child(userIDR).child(userIDO).setValue(value1);
                        myRef.child("users").child("paid").child(userIDC).child(userIDO).setValue(value1);

                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Eror", "Something happened");
            }
        });
    }
}