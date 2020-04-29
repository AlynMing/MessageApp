package com.example.teamup.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.teamup.R;

public class ChatActivity extends AppCompatActivity {
    EditText etMessage;
    Button btSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);

        setupMessagePosting();
    }

    private void setupMessagePosting() {
        btSend.setOnClickListener(v -> {
            String data = etMessage.getText().toString();

            Log.i("Nattie -- Func_s", "onCreate() .(ChatActivity.java:21)");
            Log.i("Nattie -- Func_s", String.format("data %s", data));
            Log.i("Nattie -- Func_s", "onCreate() .(ChatActivity.java:21)");
        });
    }
}
