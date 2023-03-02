package com.example.intentcourse;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

public class SystemIntents extends AppCompatActivity {
    Button setAnAlarm;
    Button webSearch;
    Button openPhoneDialog;
    Button startAPhoneCall;
    Button composeAnEmail;
    Button locationInMap;

    ActivityResultLauncher<String> phoneResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result){
                        Uri phoneN=Uri.parse("tel:"+"8101105178");
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_CALL);
                        i.setData(phoneN);
                        if(i.resolveActivity(getPackageManager())!=null){
                            startActivity(i);
                        }
                    }
                    else{
                        showAlertDialog("Phone Permission denied","We can't make phone calls");
                    }
                }
            });


    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_intents);

        setAnAlarm=findViewById(R.id.setAnAlarm);
        setAnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message="Time to wake up!";
                Integer hour=19;
                Integer minute=47;
                Intent i=new Intent();
                i.setAction(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE,message);
                i.putExtra(AlarmClock.EXTRA_HOUR,hour);
                i.putExtra(AlarmClock.EXTRA_MINUTES,minute);
                i.putExtra(AlarmClock.EXTRA_VIBRATE,true);
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivity(i);
                }
            }
        });

        webSearch=findViewById(R.id.webSearch);
        webSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="What is ChatGPT?";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,query);
                startActivity(intent);

            }
        });
        openPhoneDialog=findViewById(R.id.openPhoneDialog);
        openPhoneDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri phNo=Uri.parse("tel:"+"8101105178");
                Intent i= new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(phNo);
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivity(i);
                }

            }
        });
        startAPhoneCall=findViewById(R.id.startAPhoneCall);
        startAPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneResultLauncher.launch(Manifest.permission.CALL_PHONE);
            }
        });
        composeAnEmail=findViewById(R.id.composeAnEmail);
        composeAnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses={"ratanmodak60@gmail.com"};
                String[] ccs={"debolinamodak1960@gmail.com"};
                String[] bccs={"arijtmodak0003@gmail.com"};
                String sub="This is a test mail";
                String body="Hi, I am Arijit an android developer. This is a test mail";

                Intent i = new Intent();
                i.setAction(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL,addresses);
                i.putExtra(Intent.EXTRA_CC,ccs);
                i.putExtra(Intent.EXTRA_BCC,bccs);
                i.putExtra(Intent.EXTRA_SUBJECT,sub);
                i.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(i);

            }
        });
        locationInMap=findViewById(R.id.locationInMap);
        locationInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String location = "geo:37.4220,-122.0841";
                //String location="geo:0,0?q=37.4220,-122.0841(Google Plex)";
                //String location="geo:0,0?q=20+w+34th+st+713409";
                String location="geo:37.4220,-122.0841?q=restaurants";
                String turnByTurnNav="https://www.google.com/maps/dir/'23.2234018,88.3631151'/108+Shivmandir+Rd,+Kalna,+West+Bengal+713409/@23.2227703,88.3617102,17z/data=!3m1!4b1!4m12!4m11!1m3!2m2!1d88.3631151!2d23.2234018!1m5!1m1!1s0x39f8e5a998dae1f3:0xa39e3ce014389a20!2m2!1d88.3644167!2d23.2220676!3e2";
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(turnByTurnNav));
                startActivity(intent);

            }
        });

    }
}