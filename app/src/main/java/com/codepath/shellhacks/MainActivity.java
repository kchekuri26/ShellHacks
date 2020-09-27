package com.codepath.shellhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                findViewById(R.id.helpMsg).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.danger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.danger).setVisibility(View.INVISIBLE);
                findViewById(R.id.helpMsg).setVisibility(View.VISIBLE);

                //all the data required to send is in the onActivityResult method
            }
        });
    }
}