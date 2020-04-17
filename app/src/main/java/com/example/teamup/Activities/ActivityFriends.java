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

public class ActivityFriends extends AppCompatActivity implements View.OnClickListener, AddFriendRVAdapter.IAddFriendRVAdapter {
    private static final String TAG = "ActivityFriends";
    private RecyclerView rvFriends;
    private TextView tvNoFriends;
    private String userID;
    private AlertDialog dialogAddFriend;
    private RecyclerView rvAddFriends;
    private TextView tvNoFriendsToAdd;
    private ProgressBar pbFriendsToAdd;
    private AddFriendRVAdapter addFriendsAdapter;
    private List<ParseObject> newFriendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNoFriends = findViewById(R.id.tv_no_friends);
        rvFriends = findViewById(R.id.rv_friends);

        rvFriends.setVisibility(View.GONE);
        tvNoFriends.setVisibility(View.VISIBLE);

        findViewById(R.id.ll_add_group).setOnClickListener(this);
        findViewById(R.id.ll_add_friends).setOnClickListener(this);
        userID = getIntent().getStringExtra(MainActivity.EXTRA_USER_ID);
        initAddFriendDialog();
    }

    private void initAddFriendDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.add_friends, null);
        rvAddFriends = view.findViewById(R.id.rv_friends_to_add);
        tvNoFriendsToAdd = view.findViewById(R.id.tv_friends_to_add);
        pbFriendsToAdd = view.findViewById(R.id.pb_friends_to_add);
        builder.setView(view);
        dialogAddFriend = builder.create();

        rvAddFriends.setLayoutManager(new LinearLayoutManager(this));
        addFriendsAdapter = new AddFriendRVAdapter(this, newFriendsList, this);
        rvAddFriends.setAdapter(addFriendsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll_add_friends: {
                dialogAddFriend.show();
                // populate list of friends
                populateFriendsToAdd();
                break;
            }
            case R.id.ll_add_group: {
                // TODO: implement code for adding new group
                Snackbar.make(rvFriends, getString(R.string.under_developmennt), Snackbar.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchFriends();
    }

    private void fetchFriends() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Friends");
        query.whereEqualTo("userId", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "failed to find friends: " + e.getMessage());
                } else {
                   for (ParseObject po : objects) {
                       ParseUser friend = po.getParseUser("friend");
                       try {
                           friend = friend.fetch();
                           Log.d(TAG, String.format("friend: lastname=%s, first_name=%s, username=%s", friend.get("lname"), friend.get("fname"), friend.getUsername()));
                       } catch (ParseException ex) {
                           Log.e(TAG, "failed to fetch ParseUser: " + ex.getMessage());
                       }
                   }
                }
            };
        });
    }

    private void populateFriendsToAdd() {
        rvAddFriends.setVisibility(View.GONE);
        tvNoFriendsToAdd.setVisibility(View.GONE);
        pbFriendsToAdd.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        Log.d(TAG,"userID: " + userID);
        query.whereNotEqualTo("objectId", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e != null) {
                    Toast.makeText(getApplicationContext(), "Failed to load users. Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    newFriendsList = objects;
                    addFriendsAdapter.updateUsersList(newFriendsList);
                }

                pbFriendsToAdd.setVisibility(View.GONE);
                if (newFriendsList == null || newFriendsList.size() < 1) {
                    Log.d(TAG, "found users: " + newFriendsList.size());
                    rvAddFriends.setVisibility(View.GONE);
                    tvNoFriendsToAdd.setVisibility(View.VISIBLE);
                } else {
                    tvNoFriendsToAdd.setVisibility(View.GONE);
                    rvAddFriends.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public void onAddFriend(ParseObject user) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Add " + user.get("fname") + " as a friend?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ParseObject po = ParseObject.create("Friends");
                        po.put("friend", user);
                        po.put("userId", userID);
                        po.put("friendId", user.getObjectId());
                        po.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e(TAG, "failed to add friend: " + e.getMessage());
                                    Toast.makeText(getApplicationContext(), "Failed to add " + user.get("fname") + ". Try again later.", Toast.LENGTH_SHORT).show();
                                } else {
                                    populateFriendsToAdd();
                                    Toast.makeText(getApplicationContext(),  user.get("fname") + " is now your Friend.", Toast.LENGTH_SHORT).show();
                                }
                                fetchFriends();
                                dialog.cancel();
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        Dialog d = builder.create();
        d.setCancelable(false);
        d.show();

    }
}
