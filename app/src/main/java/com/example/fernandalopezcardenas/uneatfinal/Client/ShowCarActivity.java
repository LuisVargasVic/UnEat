package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.Detail.OrdersAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Detail.TimePicker;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowCarActivity extends AppCompatActivity {

    /*aqui va el nuevo adaptador con el orders adapter, que es igual como el de plate activity,
    se implementa igual que con el ingredients adapter*/

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    //final DatabaseReference myRef = database.getReference("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    final DatabaseReference myRef = database.child("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    private ListView listView;
    private OrdersAdapter areasAdapter;
    //public EditText orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        listView= findViewById(R.id.listview);
        Button btnTimePicker = findViewById(R.id.btnTimePicker);

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShowCarActivity.this, TimePicker.class);
                intent.putExtra("paid",areasAdapter.getOrdersChosen());
                startActivity(intent);
                finish();
            }
        });

        //orders = (EditText) findViewById(R.id.order);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<DetailCart> areas = new ArrayList<>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    areas.add(value1);
                }
                //areasAdapter = new EditText<>(ShowCarActivity.this, android.R.layout.simple_expandable_list_item_1, areas);
                areasAdapter = new OrdersAdapter(ShowCarActivity.this, areas);
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
                Intent intent = new Intent(ShowCarActivity.this, PayActivity.class);
                intent.putExtra("paid",areasAdapter.getOrdersChosen());
                startActivity(intent);
            }
        });
    }
}
