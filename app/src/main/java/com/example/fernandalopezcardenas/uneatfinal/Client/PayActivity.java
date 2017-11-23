package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PayActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private DetailCart item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        item = (DetailCart) getIntent().getSerializableExtra("paid");
        Button pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewOrder();
                addNewPaid();
                removeNewPurchase();
                Intent MapOne= new Intent(PayActivity.this, MainClientActivity.class);
                startActivity(MapOne);
                Toast.makeText(PayActivity.this,"Paid Correctly",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addNewOrder(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users").child("cart").child(userId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    String key = value1.getUidrequest();
                    Log.wtf("key", key);
                    value1.setMessage("Paid");
                    String userIDR = value1.getUidrestaurant();
                    Log.wtf("user", userIDR);
                    if (key.equals(item.getUidrequest())){
                        myRef.child("users").child("order").child(userIDR).child(key).setValue(value1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });

    }
    private void addNewPaid(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users").child("cart").child(userId);
        Log.wtf("SIUUUU", myRef.toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    Log.wtf("S",value1.toString());
                    String key = value1.getUidrequest();
                    value1.setMessage("Paid");
                    if (key.equals(item.getUidrequest())){
                        myRef = database.getReference("");
                        myRef.child("users").child("paid").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).setValue(value1);
                    }
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
        myRef.child("users").child("cart").child(userId).child(item.getUidrequest()).removeValue();
    }
}