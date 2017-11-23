package com.example.fernandalopezcardenas.uneatfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Button pay = (Button) findViewById(R.id.pay);
        Toast.makeText(this,"Payed Correctly",Toast.LENGTH_SHORT).show();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewOrder();
                removeNewPurchase();
                Intent MapOne= new Intent(PayActivity.this, MainClientActivity.class);
                startActivity(MapOne);
            }
        });
    }
    private void addNewOrder(){
        myRef = database.getReference("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final DetailCart[] detailCart = {new DetailCart()};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.wtf("asdas",dataSnapshot.toString());
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    detailCart[0] = snap.getValue(DetailCart.class);
                    Log.wtf("asdas",detailCart[0].uidrestaurant);
                    Log.wtf("asdas",dataSnapshot.toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });
        String userIDR = detailCart[0].getUidrestaurant();
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userIDR).setValue(detailCart[0]);
    }
    private void removeNewPurchase() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userId).removeValue();
    }
}
