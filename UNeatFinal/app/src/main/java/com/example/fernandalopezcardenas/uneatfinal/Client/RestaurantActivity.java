package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Restaurant");
    private ListView listView;
    private ArrayAdapter<DetailRestaurant> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        listView=(ListView) findViewById(R.id.listview);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<DetailRestaurant> areas = new ArrayList<DetailRestaurant>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailRestaurant value1 = areaSnapshot.getValue(DetailRestaurant.class);
                    areas.add(value1);
                }
                areasAdapter = new ArrayAdapter<DetailRestaurant>(RestaurantActivity.this, android.R.layout.simple_expandable_list_item_1, areas);
                listView.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                Intent intent = new Intent(RestaurantActivity.this, PlateActivity.class);
                intent.putExtra("place",areasAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
