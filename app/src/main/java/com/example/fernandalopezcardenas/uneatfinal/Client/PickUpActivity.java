package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;

import com.example.fernandalopezcardenas.uneatfinal.R;

public class PickUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTimePicker;
    EditText txtTime;
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtTime = (EditText) findViewById(R.id.in_time);

        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


}
