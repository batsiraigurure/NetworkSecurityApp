package com.example.dell.networksecurityapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    Button exportButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        exportButton = findViewById(R.id.exportButton);

        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("Internal storage/NetworkSecurityApp/NetworkSecurityStatisticsReport.pdf"));
                intent.setType("application/pdf");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                    Toast.makeText(ReportsActivity.this, "Successfully opened", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ReportsActivity.this, "Unable to export to PDF. Please try again later", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
