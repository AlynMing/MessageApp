package com.example.teamup.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamup.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class ActivityProfile extends AppCompatActivity {
    private static final String TAG = "ActivityProfile";
    private RecyclerView rvFriends;
    private TextView tvNoFriends;
    private String userID;
    private AlertDialog dialogAddFriend;
    private RecyclerView rvAddFriends;
    private TextView tvNoFriendsToAdd;
    private ProgressBar pbFriendsToAdd;
    private AddFriendRVAdapter addFriendsAdapter;
    private List<ParseObject> newFriendsList;
    private final String SIGNUP = "SIGNUP";
    private final String ERROR = "ERROR";
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText password;
    private EditText username;
    //private EditText etphonenum;
    private Button btn_Cancel;
    private Button btn_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            // show the signup or login screen
        }
    }
}
