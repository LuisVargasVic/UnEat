package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private DetailCart item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child("paid").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        item = (DetailCart) getIntent().getSerializableExtra("place");
        message = findViewById(R.id.message);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    DetailCart detail = areaSnapshot.getValue(DetailCart.class);
                    if (detail.getUidrequest().equals(item.getUidrequest())){
                        String mess = detail.getMessage();
                        message.setText(mess);
                        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify=new Notification.Builder
                                (getApplicationContext()).setContentTitle(mess).setContentText(mess).
                                setContentTitle(mess).setSmallIcon(R.drawable.ic_person).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif.notify(0, notify);
                    }
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
