package com.example.fernandalopezcardenas.uneatfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CarActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public TextView nameOne, priceOne;
    private DetailPlate item;
    private ArrayAdapter<String> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        item = (DetailPlate) getIntent().getSerializableExtra("place");

        Log.wtf("Place", item.getName());
        //myRef = database.getReference("").child(item);

        TextView name = (TextView) findViewById(R.id.name);
        TextView price = (TextView) findViewById(R.id.price);

        nameOne = (TextView) findViewById(R.id.WriteName);
        priceOne = (TextView) findViewById(R.id.WritePrice);

        name.setText(item.getName());
        price.setText(item.getPrice() + "");

        FirebaseAuth.getInstance().getCurrentUser().getUid();

        Button map = (Button) findViewById(R.id.buttonAC);
        map.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                writeNewPurchase(nameOne.getText().toString(),priceOne.getText().toString());
                Intent MapOne= new Intent(CarActivity.this, MainClientActivity.class);
                startActivity(MapOne);
            }
        });

        /*
        myRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                nameOne.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("price").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long price = dataSnapshot.getValue(Long.class);
                priceOne.setText(price + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        */
    }
    private void writeNewPurchase(String name, String price) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailCart user = new DetailCart(name, price);
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userId).setValue(user);
    }
}
