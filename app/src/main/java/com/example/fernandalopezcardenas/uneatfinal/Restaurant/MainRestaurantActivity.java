package com.example.fernandalopezcardenas.uneatfinal.Restaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fernandalopezcardenas.uneatfinal.Client.MessageActivity;
import com.example.fernandalopezcardenas.uneatfinal.Client.RestaurantActivity;
import com.example.fernandalopezcardenas.uneatfinal.Client.ShowCarActivity;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DrawerListAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DrawerListResAdapter;
import com.example.fernandalopezcardenas.uneatfinal.Signup.LoginActivity;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainRestaurantActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public ListView mDrawerList;
    public RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public ArrayList<MainRestaurantActivity.NavItem> mNavResItems = new ArrayList<MainRestaurantActivity.NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurant);

        Toolbar barLayout = findViewById(R.id.toolbarres);
        setSupportActionBar(barLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavResItems.add(new MainRestaurantActivity.NavItem("Home", "Home Page for Restaurant", R.drawable.ic_home));
        mNavResItems.add(new MainRestaurantActivity.NavItem("Plate", "Create Plate", R.drawable.ic_restaurant));
        mNavResItems.add(new MainRestaurantActivity.NavItem("Orders", "See your orders", R.drawable.ic_person));


        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListResAdapter adapter = new DrawerListResAdapter(this, mNavResItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectItemFromDrawer(position);
                Log.wtf("MaincLIENTaCT", position + " - " + mNavResItems.get(position).mTitle);
                switch(position) {
                    case 0:
                        Intent intent = new Intent(MainRestaurantActivity.this, MainRestaurantActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        Intent intent1 = new Intent(MainRestaurantActivity.this, CreatePlateActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainRestaurantActivity.this, ShowCarActivity.class);
                        startActivity(intent2);
                        break;
                }
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

        Button signOut = findViewById(R.id.sign_out);
        TextView email = findViewById(R.id.email);

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
    public class NavItem {
        public String mTitle;
        public String mSubtitle;
        public int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }

    }
}
