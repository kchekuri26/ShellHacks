package com.codepath.shellhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getExtras().getString("name"); // 'string1' needs to match the key we used when we put the string in the Intent
            String bloodType = data.getExtras().getString("bloodType");
            String allergies = data.getExtras().getString("allergies");
            String number1 = data.getExtras().getString("number1");
            String number2 = data.getExtras().getString("number2");
            String dangerText = data.getExtras().getString("text");
            String medicalText = data.getExtras().getString("textMedical");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmergencyInformation.class);

                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.danger).setVisibility(View.VISIBLE);
                findViewById(R.id.medical).setVisibility(View.VISIBLE);
                findViewById(R.id.helpMsg).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.medical).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              findViewById(R.id.medical).setVisibility(View.INVISIBLE);
                                                              findViewById(R.id.danger).setVisibility(View.INVISIBLE);
                                                              findViewById(R.id.helpMsg).setVisibility(View.VISIBLE);

                                                              //all the data required to send is in the onActivityResult method

                                                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                                                  if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                                                                      sendSMS("9501322345", "3453535355", "Test Message");


                                                                  } else {


                                                                      requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                                                                  }
                                                              }
                                                          }
                                                      });

        findViewById(R.id.danger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.danger).setVisibility(View.INVISIBLE);
                findViewById(R.id.medical).setVisibility(View.INVISIBLE);
                findViewById(R.id.helpMsg).setVisibility(View.VISIBLE);

                //all the data required to send is in the onActivityResult method

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                        sendSMS("9501322345", "3453535355", "Test Message");


                    }else {


                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                    }
        }
            }
        });
    }

    private void sendSMS(String number1, String number2, String textMessage){

       /* String number1 = data.getExtras().getString("number1");
        String number2 = data.getExtras().getString("number2");
        String textMessage = data.getExtras().getString("text");*/

        try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(number1, null, textMessage, null, null);
            smsmanager.sendTextMessage(number2, null, textMessage, null, null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
            //Run error message here!!!
            Toast.makeText(this, "ERROR!!! CAN NOT SEND", Toast.LENGTH_SHORT).show();
        }


    }
}