package com.example.teamup.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


import com.example.teamup.Fragments.FragmentHome;
import com.example.teamup.R;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;
//import com.example.teamup.fragments.ProfileFragment;

//----------------------------------------------------------------------------------
// The activity that sets up the framework for the app
// Shows the main page the user gets redirected to
//----------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentHome.IFragmentHome {

    public static final String EXTRA_USER_ID = "username_ID";
    private String userID;
    //private static final String LISTORDERS = "LISTORDERS";
    //private final String WHERE = "WHERE";
    //private final String ActivityMessages = "MESSAGES";
    //private final String ActivityFriends = "FRIENDS";
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private ImageView back;
    private Boolean home = false;
    private DrawerLayout drawer;



    //----------------------------------------------------------------------------------
    // Sets the view
    //----------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            goToLogin();
        }
        userID = getIntent().getStringExtra(EXTRA_USER_ID);


        //----------------------------------------------------------------------------------
        // Create Navigation Drawer
        //----------------------------------------------------------------------------------
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flContainer, new FragmentHome());
        ft.commit();

        //----------------------------------------------------------------------------------
        //Set Container for Fragments
        //----------------------------------------------------------------------------------
        //CategoryFragment homeFragment = new CategoryFragment(cart);
        //fragmentManager.beginTransaction()
        //      .replace(R.id.flContainer, homeFragment, CATEGORY)
        //    .addToBackStack(CATEGORY)
        //  .commit();


    }

    private void goToLogin() {
        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch(item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
               //Toast.makeText(MainActivity.this, "nav_home", Toast.LENGTH_SHORT).show();
            case R.id.nav_messages:
                //Toast.makeText(MainActivity.this, "nav_messages", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ActivityMessages.class));
                break;
            case R.id.nav_friends:
                //Toast.makeText(MainActivity.this, "nav_friends", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ActivityFriends.class));
                break;
            case R.id.nav_profile:
                //Toast.makeText(MainActivity.this, "nav_profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ActivityProfile.class));
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //----------------------------------------------------------------------------------
    //Functionality to move navigation back off screen
    //----------------------------------------------------------------------------------
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    //----------------------------------------------------------------------------------
    //Show toolbar
    //----------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_nav, menu);
        return true;
    }

    //----------------------------------------------------------------------------------
    //Creating Functionality for buttons on toolbar
    //----------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch (item.getItemId()) {
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onViewFriends() {
        Intent i = new Intent(this, ActivityFriends.class);
        i.putExtra(EXTRA_USER_ID, userID);
        startActivity(i);
    }

    @Override
    public void onLogout() {
        ParseUser.logOut();
        goToLogin();
    }

    @Override
    public void onTakePhoto() {
        Intent i = new Intent(this, TakePhotoActivity.class);
        startActivity(i);
    }

    /* @Override
    public void onViewGroups() {
        Intent i = new Intent(this, ActivityGroups.class);
        i.putExtra(EXTRA_USER_ID, userID);
        startActivity(i);
    }*/


    @Override
    public void onViewMessages() {
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra(EXTRA_USER_ID, userID);
        startActivity(i);
    }
}