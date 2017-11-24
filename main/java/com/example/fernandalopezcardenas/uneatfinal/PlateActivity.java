package com.example.fernandalopezcardenas.uneatfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlateActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private ListView listView;
    private DetailRestaurant restaurant;
    private ArrayAdapter<DetailPlate> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
        listView=(ListView) findViewById(R.id.listview);

        restaurant = (DetailRestaurant)getIntent().getSerializableExtra("place");

        /*
        myRef = database.getReference("").child(restaurant);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> areas = new ArrayList<String>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailPlate value1 = areaSnapshot.getValue(DetailPlate.class);
                    areas.add(value1.getName());
                }
                areasAdapter = new ArrayAdapter<String>(PlateActivity.this,android.R.layout.simple_expandable_list_item_1, areas);
                listView.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });
        */

        areasAdapter = new ArrayAdapter<DetailPlate>(PlateActivity.this,android.R.layout.simple_expandable_list_item_1, restaurant.getPlate());
        listView.setAdapter(areasAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                Intent intent = new Intent(PlateActivity.this, CarActivity.class);
                intent.putExtra("place",areasAdapter.getItem(position));
                intent.putExtra("res",areasAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}