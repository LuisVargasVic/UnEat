package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailCart;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageActivity extends AppCompatActivity {
    public TextView message;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child("payed").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        message = (TextView) findViewById(R.id.message);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    DetailCart detail = areaSnapshot.getValue(DetailCart.class);
                    String mess = detail.getMessage();
                    message.setText(mess);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
}
