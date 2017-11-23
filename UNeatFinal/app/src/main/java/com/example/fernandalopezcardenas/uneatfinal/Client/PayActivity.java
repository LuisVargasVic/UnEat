package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.example.fernandalopezcardenas.uneatfinal.Restaurant.DeliverActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Button pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewOrder();
                addNewPayed();
                removeNewPurchase();
                Intent MapOne= new Intent(PayActivity.this, MainClientActivity.class);
                startActivity(MapOne);
                Toast.makeText(PayActivity.this,"Payed Correctly",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addNewOrder(){
        myRef = database.getReference("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1);
                    value1.setMessage("Payed");
                    String userIDR = value1.getUidrestaurant().toString();
                    Log.wtf("khé berga?", value1.getUidrestaurant().toString());
                    myRef = database.getReference("");
                    myRef.child("users").child("order").child(userIDR).setValue(areas);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });

    }
    private void addNewPayed(){
        myRef = database.getReference("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1);
                    value1.setMessage("Payed");
                    myRef = database.getReference("");
                    myRef.child("users").child("payed").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(areas);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });

    }
    private void removeNewPurchase() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userId).removeValue();
    }
}
