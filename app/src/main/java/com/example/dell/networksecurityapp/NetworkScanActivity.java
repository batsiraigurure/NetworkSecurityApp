package com.example.dell.networksecurityapp;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.net.InetAddress;

public class NetworkScanActivity extends AppCompatActivity {

    Button scanButton;



    class NetworkSniffTask extends AsyncTask<Void, Void, Void> {
        private static final String TAG =  "nstask";

        TextView netScan;

        private WeakReference<Context> mContextRef;

        public NetworkSniffTask(Context context) {
            mContextRef = new WeakReference<Context>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            netScan=findViewById(R.id.connectedDevicesView);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "Let's sniff the network");

            final StringBuilder displayText= new StringBuilder();


            try {
                Context context = mContextRef.get();

                if (context != null) {

                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                    WifiInfo connectionInfo = wm.getConnectionInfo();
                    int ipAddress = connectionInfo.getIpAddress();
                    String ipString = Formatter.formatIpAddress(ipAddress);


                    Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
                    Log.d(TAG, "ipString: " + String.valueOf(ipString));

                    String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                    Log.d(TAG, "prefix: " + prefix);

                    for (int i = 0; i < 50; i++) {
                        String testIp = prefix + String.valueOf(i);

                        InetAddress address = InetAddress.getByName(testIp);
                        boolean reachable = address.isReachable(1000);
                        final String hostName = address.getCanonicalHostName();


                        if (reachable) {
                            Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ")---Device Connected!");

                            displayText.append("* Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ")---Device Connected!" + "\n\n");



                        }


                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            netScan.setText(displayText);
                        }
                    });
                }
            } catch (Throwable t) {
                Log.e(TAG, "Well that's not good.", t);
            }


            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_scan);

        scanButton = findViewById(R.id.scanForThreats);

        new NetworkSniffTask(NetworkScanActivity.this).execute();

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NetworkScanActivity.this, ThreatDevicesActivity.class);
                startActivity(intent);
            }
        });

    }
}
