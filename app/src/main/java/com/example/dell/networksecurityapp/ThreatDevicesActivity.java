package com.example.dell.networksecurityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThreatDevicesActivity extends AppCompatActivity {

    Button blockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_devices);
        blockButton = findViewById(R.id.blockButton);

        blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThreatDevicesActivity.this,SelectDeviceToBlock.class);
                startActivity(intent);
            }
        });
    }
}
