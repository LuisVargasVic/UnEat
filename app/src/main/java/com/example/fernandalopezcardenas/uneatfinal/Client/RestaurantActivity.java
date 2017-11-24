package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fernandalopezcardenas.uneatfinal.Detail.AreasResAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.Detail.ListModelRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("users").child("restaurant");
    private ListView listView;
    private ArrayAdapter<DetailRestaurant> areasAdapter;
    private AreasResAdapter adapter;
    public  ArrayList<ListModelRestaurant> CustomListViewValuesArr = new ArrayList<ListModelRestaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        listView=(ListView) findViewById(R.id.listview);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<DetailRestaurant> areas = new ArrayList<DetailRestaurant>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailRestaurant value1 = areaSnapshot.getValue(DetailRestaurant.class);
                    areas.add(value1);
                }
                //areasAdapter = new ArrayAdapter<DetailRestaurant>(RestaurantActivity.this, android.R.layout.simple_expandable_list_item_1, areas);
                adapter = new AreasResAdapter(RestaurantActivity.this, areas);
                listView.setAdapter(adapter);

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
                intent.putExtra("place", (DetailRestaurant)adapter.getItem(position));
                startActivity(intent);
            }
        });
    }
    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final ListModelRestaurant sched = new ListModelRestaurant();

            /******* Firstly take data in model object ******/
            sched.setRestaurantName("Restaurant "+i);
            sched.setImageRestaurant("image"+i);
            sched.setRestaurantURL("http:\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }

    public void onItemClick(int mPosition) {
        ListModelRestaurant tempValues = ( ListModelRestaurant ) CustomListViewValuesArr.get(mPosition);

    }

}
