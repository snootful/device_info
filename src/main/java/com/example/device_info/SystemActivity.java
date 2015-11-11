package com.example.device_info;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        final ArrayList<String[]> list = new ArrayList<>();
        list.add(new String[]{"CODENAME", Build.VERSION.CODENAME});
        list.add(new String[]{"RELEASE", Build.VERSION.RELEASE});
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            list.add(new String[]{"SECURITY_PATCH", Build.VERSION.SECURITY_PATCH});
        }
        list.add(new String[]{"The user-visible SDK version of the framework", Integer.toString(Build.VERSION.SDK_INT)});
        list.add(new String[]{"The name of the underlying board", Build.BOARD});
        list.add(new String[]{"A build ID string meant for displaying to the user", Build.DISPLAY});
        list.add(new String[]{"BOOTLOADER", Build.BOOTLOADER});



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
    public void onUserLeaveHint () {
        finish();
    }
}
