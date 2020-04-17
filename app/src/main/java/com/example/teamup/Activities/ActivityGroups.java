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

public class ActivityGroups extends AppCompatActivity implements View.OnClickListener {
    //RecyclerView rvFriends;
    //TextView tvNoFriends;
    RecyclerView rvGroups;
    TextView tvNoGroups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNoGroups = findViewById(R.id.tv_no_groups);
        rvGroups = findViewById(R.id.rv_groups);

        rvGroups.setVisibility(View.GONE);
        tvNoGroups.setVisibility(View.VISIBLE);

        findViewById(R.id.ll_add_group).setOnClickListener(this);
        findViewById(R.id.ll_add_friends).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll_add_friends: {
                // TODO: implement code for adding new friend
                Snackbar.make(rvGroups, getString(R.string.under_developmennt), Snackbar.LENGTH_SHORT).show();
                break;
            }
            case R.id.ll_add_group: {
                // TODO: implement code for adding new group
                Snackbar.make(rvGroups, getString(R.string.under_developmennt), Snackbar.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
