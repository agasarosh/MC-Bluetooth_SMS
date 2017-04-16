package com.example.saroshaga.bluetoothmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMS_Activity extends AppCompatActivity implements OnClickListener{

    Button send;
    EditText phone_Number, message;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_);
        send = (Button) findViewById(R.id.Send_msg_Btn);
        phone_Number = (EditText) findViewById(R.id.EditText_PhoneNumber);
        message = (EditText) findViewById(R.id.MainActivity_Message);
        backButton=(Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
        send.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String phone_Num = phone_Number.getText().toString();
        String send_msg = message.getText().toString();
        try {
            SmsManager sms = SmsManager.getDefault();  // using android SmsManager
            sms.sendTextMessage(phone_Num, null, send_msg, null, null);  // adding number and text
        } catch (Exception e) {
            Toast.makeText(this, "Sms not Send", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }
}

