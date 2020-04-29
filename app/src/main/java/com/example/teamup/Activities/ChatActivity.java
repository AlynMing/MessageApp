package com.example.teamup.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamup.ParseClasses.Message;
import com.example.teamup.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.example.teamup.Activities.MainActivity.EXTRA_USER_ID;
import static com.example.teamup.ParseClasses.Message.BODY_KEY;
import static com.example.teamup.ParseClasses.Message.USER_ID_KEY;

public class ChatActivity extends AppCompatActivity {
    EditText etMessage;
    Button btSend;
    String loggedInUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);
        loggedInUserId = getIntent().getStringExtra(EXTRA_USER_ID);
        Log.i("Test -- Func_s", String.format("userId %s", loggedInUserId));
        setupMessagePosting();
    }

    private void setupMessagePosting() {
        btSend.setOnClickListener(v -> {

            String data = etMessage.getText().toString();
//            ParseObject message = ParseObject.create("Message");
//            message.put(USER_ID_KEY, loggedInUserId);
//            message.put(BODY_KEY, data);

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
}
