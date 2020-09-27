package com.codepath.shellhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EmergencyInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_information);

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ((EditText) findViewById(R.id.name)).getText().toString();
                String bloodType = ((EditText) findViewById(R.id.bloodType)).getText().toString();
                String allergies = ((EditText) findViewById(R.id.allergies)).getText().toString();
                String number1 = ((EditText) findViewById(R.id.emNum1)).getText().toString();
                String number2 = ((EditText) findViewById(R.id.emNum2)).getText().toString();

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("name", name); // puts one string into the Intent, with the key as 'question'
                data.putExtra("bloodType", bloodType); // puts another string into the Intent, with the key as 'answer'
                data.putExtra("allergies", allergies);
                data.putExtra("number1", number1);
                data.putExtra("number2", number2);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();
            }
        });
    }
}