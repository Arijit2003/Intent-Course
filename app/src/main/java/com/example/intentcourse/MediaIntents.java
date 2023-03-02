package com.example.intentcourse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MediaIntents extends AppCompatActivity {
    Button startCameraInPhoto;
    Button startCameraAndGetImage;
    Bitmap bitmapImage;
    ImageView imageViewN;
    Button openUrl;
    Button smsMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_intents);
        imageViewN=findViewById(R.id.imageViewN);

        startCameraInPhoto=findViewById(R.id.startCameraInPhoto);
        startCameraInPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivity(i);
                }
            }
        });

        startCameraAndGetImage=findViewById(R.id.startCameraAndGetImage);
        startCameraAndGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(i,1001);
                }
            }
        });

        openUrl=findViewById(R.id.openUrl);
        openUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage=Uri.parse("https://www.facebook.com");
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(webpage);
                if(i.resolveActivity(getPackageManager())!=null){
                    startActivity(i);
                }
            }
        });

        smsMessage=findViewById(R.id.smsMessage);
        smsMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message="This is a message";
                String phNo="8101105178";
                Uri phNoUri=Uri.parse("sms:"+phNo);
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(phNoUri);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==1001){
                assert data != null;
                Bundle newData=data.getExtras();
                bitmapImage=(Bitmap)newData.get("data");
                imageViewN.setImageBitmap(bitmapImage);

            }
        }
    }
}