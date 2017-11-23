package com.example.fernandalopezcardenas.uneatfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

    }
    public void showPay(View v){

        Toast.makeText(this,"Payed Correctly",Toast.LENGTH_SHORT).show();

    }


}
