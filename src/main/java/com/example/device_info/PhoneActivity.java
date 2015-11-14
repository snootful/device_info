package com.example.device_info;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();


        final ArrayList<String[]> list = new ArrayList<>();
        list.add(new String[]{"IMEI", imei});


        ArrayAdapter<String[]> arrayAdapter = new ArrayAdapter<String []>(this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                list){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                String[] entry = list.get(position);
                text1.setText(entry[0]);
                text2.setText(entry[1]);

                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onUserLeaveHint() {
        finish();
    }
}
