package com.example.teamup.Activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.teamup.R;

public class ActivityMessages extends AppCompatActivity implements View.OnClickListener {
    //RecyclerView rvFriends;
    //TextView tvNoFriends;
    RecyclerView rvMessages;
    TextView tvNoMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNoMessages = findViewById(R.id.tv_no_messagess);
        rvMessages = findViewById(R.id.rv_messagess);

        rvMessages.setVisibility(View.GONE);
        tvNoMessages.setVisibility(View.VISIBLE);

        findViewById(R.id.ll_add_group).setOnClickListener(this);
        findViewById(R.id.ll_add_friends).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll_add_friends: {
                // TODO: implement code for adding new friend
                Snackbar.make(rvMessages, getString(R.string.under_developmennt), Snackbar.LENGTH_SHORT).show();
                break;
            }
            case R.id.ll_add_group: {
                // TODO: implement code for adding new group
                Snackbar.make(rvMessages, getString(R.string.under_developmennt), Snackbar.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
