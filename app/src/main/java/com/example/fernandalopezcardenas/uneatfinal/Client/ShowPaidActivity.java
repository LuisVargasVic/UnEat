package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.Detail.OrdersAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Detail.TimePicker;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.example.fernandalopezcardenas.uneatfinal.Restaurant.DeliverActivity;
import com.example.fernandalopezcardenas.uneatfinal.Restaurant.ShowOrderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowPaidActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("users").child("paid").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    private ListView listView;
    private ArrayAdapter<String> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        listView=(ListView) findViewById(R.id.listview);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> areas = new ArrayList<>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1.getUidrequest());
                }
                areasAdapter = new ArrayAdapter<>(ShowPaidActivity.this, android.R.layout.simple_expandable_list_item_1, areas);
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
                Intent intent = new Intent(ShowPaidActivity.this, MessageActivity.class);
                intent.putExtra("place",areasAdapter.getItem(position));
                startActivity(intent);
                finish();
            }
        });
    }
}
