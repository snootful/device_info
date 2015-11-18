package com.example.device_info;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_READ_PHONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void aboutBattery(View view) {
        Intent intent = new Intent(this, BatteryActivity.class);
        startActivity(intent);
    }

    public void aboutPhone(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkReadPhonePermission = ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_PHONE_STATE);
            if (checkReadPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_PHONE_STATE},
                        PERMISSION_REQUEST_READ_PHONE);
            } else {
                Intent intent = new Intent(this, PhoneActivity.class);
                startActivity(intent);            }
        } else {
            Intent intent = new Intent(this, PhoneActivity.class);
            startActivity(intent);
        }
    }

    public void aboutSystem(View view) {
        Intent intent = new Intent(this, SystemActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_REQUEST_READ_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, PhoneActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "READ_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}