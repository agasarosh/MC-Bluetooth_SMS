package com.example.saroshaga.bluetoothmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btButton;
    Button smsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btButton=(Button)findViewById(R.id.btButton);
        smsButton=(Button)findViewById(R.id.smsButton);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btIntent=new Intent(getApplicationContext(),BTActivity.class);
                startActivity(btIntent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent=new Intent(getApplicationContext(),SMS_Activity.class);
                startActivity(smsIntent);
            }
        });
    }
}
