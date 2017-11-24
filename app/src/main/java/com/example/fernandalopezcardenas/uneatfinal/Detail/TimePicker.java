package com.example.fernandalopezcardenas.uneatfinal.Detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.fernandalopezcardenas.uneatfinal.Client.PayActivity;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimePicker extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private android.widget.TimePicker timePicker1;
    private Calendar calendar;
    private String format = "";
    private Button btnProc;
    private ArrayList<String> adapter;

    public TimePicker(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        timePicker1 = (android.widget.TimePicker) findViewById(R.id.timePicker1);
        btnProc= (Button)findViewById(R.id.btnProc);
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
        adapter = getIntent().getStringArrayListExtra("paid");
        Log.wtf("fasfas", getIntent().toString());
        btnProc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TimePicker.this, PayActivity.class);
                intent.putExtra("order", adapter);
                sendTime();
                Toast.makeText(getApplicationContext(), "Time saved",Toast.LENGTH_SHORT);
                startActivity(intent);
                finish();
            }
        });
    }


    public TimePicker(android.widget.TimePicker timePicker1,Calendar calendar, String format) {
        this.timePicker1 = timePicker1;
        this.calendar = calendar;
        this.format = format;
    }

    public void setTime(View view) {
        showTime(currentHour(), currentMinute());
    }

    private int currentMinute() {
        int min = timePicker1.getCurrentMinute();
        return min;
    }
    private int currentHour() {
        int hour = timePicker1.getCurrentHour();
        return hour;
    }
    public int getCurrentMinute() {
        return currentMinute();
    }
    public int getCurrentHour() {
        return currentHour();
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }


    }
    //Button send time

    public void sendTime(){
        myRef = database.getReference("users").child("cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.wtf("TimeAct", "datasnapshot: " + dataSnapshot.toString());
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    DetailCart value1 = areaSnapshot.getValue(DetailCart.class);
                    if (adapter.contains(value1.getUidrequest())){
                        String key = value1.getUidrequest();
                        value1.setPickuptime(String.valueOf(timePicker1.getCurrentHour()) + ":" + String.valueOf(timePicker1.getCurrentMinute()) );
                        String pickuptime = value1.getPickuptime();
                        String userIDC = value1.getUidclient();
                        myRef = database.getReference("");
                        myRef.child("users").child("cart").child(userIDC).child(key).setValue(value1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Eror", "Something happened");
            }
        });
    }
}

