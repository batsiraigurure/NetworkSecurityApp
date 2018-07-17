package com.example.dell.networksecurityapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreatDevicesActivity extends AppCompatActivity {

    Button blockButton;
    ProgressBar progressbar;
    Long timeForProgressbar;
    TextView threatsFound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_devices);
        blockButton = findViewById(R.id.blockButton);
        progressbar = findViewById(R.id.progressScanning);
        threatsFound = findViewById(R.id.threatsFound);

        timeForProgressbar = Long.valueOf(5000);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressbar.setVisibility(View.GONE);
                threatsFound.setText("No threats detected!");

            }

        }, timeForProgressbar);


        blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThreatDevicesActivity.this,SelectDeviceToBlock.class);
                startActivity(intent);
            }
        });
    }
}
