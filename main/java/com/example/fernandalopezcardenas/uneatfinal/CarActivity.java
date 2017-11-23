package com.example.fernandalopezcardenas.uneatfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public TextView name, price;
    private DetailPlate item;
    private DetailRestaurant res;
    private ArrayAdapter<String> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        item = (DetailPlate) getIntent().getSerializableExtra("place");
        res = (DetailRestaurant) getIntent().getSerializableExtra("res");

        Log.wtf("Place", item.getName());
        //myRef = database.getReference("").child(item);

        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);

        name.setText(item.getName());
        price.setText(item.getPrice() + "");

        ArrayList<String> arrayOfUsers = new ArrayList<String>();
        // Create the adapter to convert the array to views
        IngredientsAdapter adapter = new IngredientsAdapter(this, item.getIngredients());
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        FirebaseAuth.getInstance().getCurrentUser().getUid();

        Button map = (Button) findViewById(R.id.buttonAC);
        map.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                // adapter.gtingredientsselected()
                writeNewPurchase(name.getText().toString(),Integer.parseInt(price.getText().toString()), item.getIngredients());
                Intent MapOne= new Intent(CarActivity.this, MainClientActivity.class);
                startActivity(MapOne);
            }
        });
    }
    private void writeNewPurchase(String name, int price, ArrayList<String> ingredients) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailPlate user = new DetailPlate(name, price, ingredients);
        myRef = database.getReference("");
        myRef.child("users").child("cart").child(userId).setValue(user);
    }
}
