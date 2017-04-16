package com.example.saroshaga.bluetoothmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class BTActivity extends AppCompatActivity {

    Button btn;
    Button backButton;
    String filepath;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt);
        init();
    }


    private void init() {

        //put full path of your file
        f=new File("/storage/sdcard0/bluetooth/abc.jpg");
        Toast.makeText(getApplicationContext(),f.getAbsolutePath(),Toast.LENGTH_LONG).show();
        filepath =f.getAbsolutePath();
        btn = (Button) findViewById(R.id.btnSt);
        backButton = (Button) findViewById(R.id.backButton);


        //check if bluetooth is supported or not
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null) {
            Toast.makeText(BTActivity.this, "Bluetooth not supported.", Toast.LENGTH_LONG).show();
        } else {
            //supported then enable
            btAdapter.enable();
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        //registering send via bt button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableBluetooth();
            }
        });
    }

    private void enableBluetooth() {

/************************************
 //this code will ask user to choose app to send file where user has to select Bluetooth
 Intent intent = new Intent();
 intent.setAction(Intent.ACTION_SEND);
 intent.setType("text/plain");
 intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filepath)));
 startActivity(intent);
 **********************************/

        ///////////////////////////////////////////////
        // instead we can manually choose the BT app using following code
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filepath)));

        //   intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));

        PackageManager pm = getPackageManager();
        List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

        if(appsList.size()>0)
        {
            String packageName = null;
            String className = null;
            boolean found = false;

            for(ResolveInfo info : appsList)
            {
                packageName = info.activityInfo.packageName;
                if(packageName.equals("com.android.bluetooth"))
                {
                    className = info.activityInfo.name;
                    found = true;
                    break;
                }
            }

            if(!found){
                Toast.makeText(this,"Bluetooth app not found" , Toast.LENGTH_LONG).show();
            }
            else
            {
                intent.setClassName(packageName, className);
                startActivity(intent);
            }
        }
    }

}