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
                openActivity2();

            }
        });

        findViewById(R.id.danger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.danger).setVisibility(View.INVISIBLE);
                findViewById(R.id.medical).setVisibility(View.INVISIBLE);
                findViewById(R.id.helpMsg).setVisibility(View.VISIBLE);

                //all the data required to send is in the onActivityResult method

                openActivity2();
                }

        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}