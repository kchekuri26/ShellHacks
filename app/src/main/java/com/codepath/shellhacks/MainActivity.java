package com.codepath.shellhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String name;
    public static String bloodType;
    public static String allergies;
    public static String number1;
    public static String number2;
    public static String dangerText;
    public static String medicalText;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            name = data.getExtras().getString("name"); // 'string1' needs to match the key we used when we put the string in the Intent
            bloodType = data.getExtras().getString("bloodType");
            allergies = data.getExtras().getString("allergies");
            number1 = data.getExtras().getString("number1");
            number2 = data.getExtras().getString("number2");
            dangerText = data.getExtras().getString("text");
            medicalText = data.getExtras().getString("textMedical");

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
                openActivity2("Med");

            }
        });

        findViewById(R.id.danger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.danger).setVisibility(View.INVISIBLE);
                findViewById(R.id.medical).setVisibility(View.INVISIBLE);
                findViewById(R.id.helpMsg).setVisibility(View.VISIBLE);

                //all the data required to send is in the onActivityResult method

                openActivity2( "Emergency");
                }

        });
    }

    public void openActivity2( String typeEmergency ) {

        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("stringKey2", bloodType);
        intent.putExtra("stringKey3", allergies);
        intent.putExtra("stringKey4", number1);
        intent.putExtra("stringKey4", number2);
        intent.putExtra("stringKey4", dangerText);
        intent.putExtra("stringKey4", medicalText);
        intent.putExtra("typeEmergency", typeEmergency);

        MainActivity.this.startActivity(intent);
    }

}