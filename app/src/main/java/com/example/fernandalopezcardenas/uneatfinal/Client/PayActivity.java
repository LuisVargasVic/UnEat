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
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ArrayList<String> adapter;
    ArrayList<DetailCart> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        adapter = getIntent().getStringArrayListExtra("order");
        final ListView listView = findViewById(R.id.listPay);
        item = (ArrayList<DetailCart>) getIntent().getSerializableExtra("orders");
        ArrayList<String> orders = new ArrayList<>();
        for (DetailCart namePay : item){
            orders.add(namePay.getCart().getName());
        }
        final ArrayAdapter adapter  = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orders);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailCart cart  = item.get(i);
                Log.wtf("g", item.get(i).toString());

                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(PayActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.row_pay, null);

                TextView name = mView.findViewById(R.id.namePay);
                TextView price = mView.findViewById(R.id.pricePay);
                TextView hour = mView.findViewById(R.id.hourPay);
                ListView listView1 = mView.findViewById(R.id.listPay);
                ArrayAdapter adapter1 = new ArrayAdapter(PayActivity.this, android.R.layout.simple_list_item_1, cart.getCart().getIngredients());
                listView1.setAdapter(adapter1);
                name.setText(cart.getCart().getName());
                price.setText("$" + cart.getCart().getPrice() + " MXN");
                hour.setText(cart.getPickuptime());

                mBuilder.setView(mView);
                final android.app.AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewOrder();
                addNewPayed();
                removeNewPurchase();
                Intent MapOne= new Intent(PayActivity.this, MainClientActivity.class);
                startActivity(MapOne);
                Toast.makeText(PayActivity.this,"Paid Correctly",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void addNewOrder(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users").child("cart").child(userId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    String key = value1.getUidrequest();
                    //areas.add(value1);
                    if(adapter.contains(value1.getUidrequest())){
                        value1.setMessage("Paid");
                        String userIDR = value1.getUidrestaurant();
                        myRef = database.getReference("");
                        myRef.child("users").child("order").child(userIDR).child(key).setValue(value1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });

    }
    public void addNewPayed(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users").child("cart").child(userId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //final List<DetailCart> areas = new ArrayList<DetailCart>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    //areas.add(value1);
                    if(adapter.contains(value1.getUidrequest())){
                        String key = value1.getUidrequest();
                        value1.setMessage("Paid");
                        myRef = database.getReference("");
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        myRef.child("users").child("paid").child(userId).child(key).setValue(value1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });

    }
    private void removeNewPurchase() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef = database.getReference("users").child("cart").child(userId);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    if(adapter.contains(value1.getUidrequest())){
                        myRef = database.getReference("");
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        myRef.child("users").child("cart").child(userId).child(value1.getUidrequest()).removeValue();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", "Something happened");
            }
        });
    }
}
