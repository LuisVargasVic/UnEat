package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailPlate;
import com.example.fernandalopezcardenas.uneatfinal.Detail.IngredientsAdapter;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public TextView name, price;
    private DetailPlate item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        item = (DetailPlate) getIntent().getSerializableExtra("place");

        Log.wtf("Place", item.getName());
        //myRef = database.getReference("").child(item);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        name.setText(item.getName());
        price.setText(item.getPrice() + "");

        // Create the adapter to convert the array to views
        final IngredientsAdapter adapter = new IngredientsAdapter(this, item.getIngredients());
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String message = "To car";
        final String key = database.getReference("users").child("cart").child(userId).push().getKey();

        Button map = findViewById(R.id.buttonAC);
        map.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                // adapter.gtingredientsselected()
                item.setIngredients(adapter.getIngredientsChosen());
                writeNewPurchase(userId, item.getUidrestaurant(),key, message, item);
                Intent MapOne= new Intent(CarActivity.this, MainClientActivity.class);
                startActivity(MapOne);
                finish();
            }
        });
    }
    private void writeNewPurchase(String UIDC, String UIDR, String UIDO, String message, DetailPlate plate) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailCart user = new DetailCart(UIDC, UIDR,UIDO, message, plate);
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userId).child(UIDO).setValue(user);
    }
}
