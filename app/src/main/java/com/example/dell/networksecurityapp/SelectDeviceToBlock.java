package com.example.dell.networksecurityapp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectDeviceToBlock extends AppCompatActivity {

    TextView ipAddress;
    Button executeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device_to_block);

        ipAddress = findViewById(R.id.ipAddress);
        executeButton = findViewById(R.id.execute);

        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAccessPoint();
            }
        });
    }

    private void stopAccessPoint() {
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
        Toast.makeText(this.getApplicationContext(), "Device disconnected!", Toast.LENGTH_LONG).show();
    }
}
