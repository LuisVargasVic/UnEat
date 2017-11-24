package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.AreasPlAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailPlate;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.Detail.ListModelPlate;
import com.example.fernandalopezcardenas.uneatfinal.Detail.ListModelRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PlateActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ListView listView;
    private DetailRestaurant restaurant;
    private AreasPlAdapter areasAdapter;
    public ArrayList<ListModelPlate> CustomListViewValuesArr = new ArrayList<ListModelPlate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
        listView=(ListView) findViewById(R.id.listview);


        restaurant = (DetailRestaurant)getIntent().getSerializableExtra("place");

        areasAdapter = new AreasPlAdapter(PlateActivity.this, restaurant.getPlatesList());
        listView.setAdapter(areasAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                Intent intent = new Intent(PlateActivity.this, CarActivity.class);
                intent.putExtra("place",(DetailPlate) areasAdapter.getItem(position));
                intent.putExtra("res",restaurant);
                startActivity(intent);
            }
        });
    }
    public void setListData() {

        for (int i = 0; i < 11; i++) {

            final ListModelPlate sched = new ListModelPlate();

            /******* Firstly take data in model object ******/
            sched.setPlate("Restaurant "+i);
            sched.setImagePlate("image"+i);
            sched.setPlateURL("http:\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }

    public void onItemClick(int mPosition) {
        ListModelPlate tempValues = ( ListModelPlate ) CustomListViewValuesArr.get(mPosition);

    }
}
