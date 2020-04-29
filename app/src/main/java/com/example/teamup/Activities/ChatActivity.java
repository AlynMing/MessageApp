package com.example.teamup.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamup.Adapters.ChatAdapter;
import com.example.teamup.ParseClasses.Message;
import com.example.teamup.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.teamup.Activities.MainActivity.EXTRA_USER_ID;
import static com.example.teamup.ParseClasses.Message.BODY_KEY;
import static com.example.teamup.ParseClasses.Message.USER_ID_KEY;

public class ChatActivity extends AppCompatActivity {
    RecyclerView rvChat;
    EditText etMessage;
    Button btSend;
    String loggedInUserId;
    ArrayList<Message> mMessages;
    ChatAdapter mAdapter;
    boolean mFirstLoad = true;
    static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;

    static final int POLL_INTERVAL = 1000; // milliseconds
    Handler myHandler = new Handler();  // android.os.Handler
    Runnable mRefreshMessagesRunnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            myHandler.postDelayed(this, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        etMessage = findViewById(R.id.etMessage);
        btSend = findViewById(R.id.btSend);
        rvChat = findViewById(R.id.rvChat);

        loggedInUserId = getIntent().getStringExtra(EXTRA_USER_ID);

        mMessages = new ArrayList<>();
        mAdapter = new ChatAdapter(ChatActivity.this, loggedInUserId, mMessages);
        rvChat.setAdapter(mAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setReverseLayout(true);
        rvChat.setLayoutManager(layout);

        setupMessagePosting();

        // pulling server every 1 second to fetch new messages
        myHandler.postDelayed(mRefreshMessagesRunnable, POLL_INTERVAL);
    }

    private void setupMessagePosting() {
        btSend.setOnClickListener(v -> {

            String data = etMessage.getText().toString();
            Message message = new Message();
            message.setBody(data);
            message.setUserId(ParseUser.getCurrentUser().getObjectId());

            message.saveInBackground(e -> {
                if(e == null) {
                    Toast.makeText(ChatActivity.this, "Successfully created message on Parse",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Failed to created message on Parse",
                            Toast.LENGTH_SHORT).show();
                }
            });
            etMessage.setText(null);
        });
    }

    // Query messages from Parse so we can load them into the chat adapter
    void refreshMessages() {
        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);

        // get the latest 50 messages, order will show up newest to oldest of this group
        query.orderByDescending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground((messages, e) -> {
            if (e == null) {
                mMessages.clear();
                mMessages.addAll(messages);
                mAdapter.notifyDataSetChanged(); // update adapter
                // Scroll to the bottom of the list on initial load
                if (mFirstLoad) {
                    rvChat.scrollToPosition(0);
                    mFirstLoad = false;
                }
            } else {
                Log.e("message", "Error Loading Messages" + e);
            }
        });
    }
}
