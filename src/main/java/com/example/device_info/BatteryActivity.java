package com.example.device_info;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BatteryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int status2 = BatteryManager.BATTERY_STATUS_FULL;
        int status3 = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        float temperature = ((float) status3) / 10;
        int status4 = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        float voltage = ((float) status4) /1000;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
        int status5 = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, 1);
        String health = "Unknown";
        switch (status5) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                health = "Unknown";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                health = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                health = "Overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                health = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                health = "Over voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                health = "Unspecified failure";
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                health = "Cold";
                break;
        }


        final ArrayList<String[]> battery = new ArrayList<>();
        battery.add(new String[]{"Temperature", temperature+"°C"});
        battery.add(new String[]{"Voltage", voltage+"Volts"});
        battery.add(new String[]{"Capacity", level+"/"+scale});
        battery.add(new String[]{"Status", status2+"/"});
        battery.add(new String[]{"Health", health});
        battery.add(new String[]{"Total Battery Capacity", getBatteryCapacity()});


        ArrayAdapter<String[]> arrayAdapter = new ArrayAdapter<String []>(this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                battery){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                String[] entry = battery.get(position);
                text1.setText(entry[0]);
                text2.setText(entry[1]);

                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
    }

    public String getBatteryCapacity() {
        Object mPowerProfile_ = null;

        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile_ = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            double batteryCapacity = (Double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getAveragePower", java.lang.String.class)
                    .invoke(mPowerProfile_, "battery.capacity");

            return batteryCapacity + " mah";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No data";

    }

    @Override
    public void onUserLeaveHint() {
        finish();
    }
}