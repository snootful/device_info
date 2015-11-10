package com.example.device_info;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void aboutBattery(View view) {
        Intent intent = new Intent(this, BatteryActivity.class);
        startActivity(intent);
    }

    public void aboutSystem(View view) {
        Intent intent = new Intent(this, SystemActivity.class);
        startActivity(intent);
    }
}
