package com.example.intentcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        textView=findViewById(R.id.textView);
        String dataStr;
        if(getIntent().getStringExtra("StringData")!=null) {
            dataStr = getIntent().getStringExtra("StringData");
        }else{
            dataStr="";
        }
        int dataInt=getIntent().getIntExtra("IntData",-1);
        textView.setText(dataStr.concat(Integer.toString(dataInt)));


    }
}