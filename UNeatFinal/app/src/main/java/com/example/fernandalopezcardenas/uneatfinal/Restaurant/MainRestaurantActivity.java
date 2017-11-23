package com.example.fernandalopezcardenas.uneatfinal.Restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.Client.MainClientActivity;
import com.example.fernandalopezcardenas.uneatfinal.Client.RestaurantActivity;
import com.example.fernandalopezcardenas.uneatfinal.Signup.LoginActivity;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class MainRestaurantActivity extends AppCompatActivity {

    private TextView email;
    private Button signOut, buttonD;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurant);

        buttonD = (Button) findViewById(R.id.buttonD);
        buttonD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent int1= new Intent(MainRestaurantActivity.this,ShowOrderActivity.class);
                startActivity(int1);
            }
        });
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainRestaurantActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        signOut = (Button) findViewById(R.id.sign_out);
        email = (TextView) findViewById(R.id.email);

        if (user != null) {
            email.setText(user.getEmail());
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }


    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
