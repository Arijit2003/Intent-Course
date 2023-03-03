package com.example.intentcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnExplicitIntent;
    Button btnImplicitIntent;
    Button btnMediaIntent;
    Button btnSystemIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExplicitIntent=findViewById(R.id.btnExplicitIntent);
        btnExplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DestinationActivity.class);
                intent.putExtra("StringData","Came from MainActivity");
                intent.putExtra("IntData",1234);
                startActivity(intent);

            }
        });

        btnImplicitIntent=findViewById(R.id.btnImplicitIntent);
        btnImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt="This is a plain text";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,txt);
                Intent chooser=Intent.createChooser(i,"Select any app");
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
            }
        });

        btnMediaIntent=findViewById(R.id.btnMediaIntent);
        btnMediaIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MediaIntents.class);
                startActivity(i);
            }
        });

        btnSystemIntent=findViewById(R.id.btnSystemIntent);
        btnSystemIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SystemIntents.class);
                startActivity(intent);
            }
        });
    }
}