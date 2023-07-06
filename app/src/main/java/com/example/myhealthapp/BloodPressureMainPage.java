package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BloodPressureMainPage extends AppCompatActivity {
    Button cbp,vbp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_main_page);

        cbp = findViewById(R.id.btnCBP);
        vbp = findViewById(R.id.btnVBP);
        cbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(),MyBPApp.class);
                startActivity(j);
            }
        });

        vbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ViewResult.class);
                startActivity(i);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mBMI:
                Toast.makeText(getApplicationContext(), "Calculate BMI", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),BMI.class);
                startActivity(i);
                return true;

            case R.id.mBP:
                Toast.makeText(getApplicationContext(), "Calculate BP", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),BloodPressureMainPage.class);
                startActivity(intent);
                return (true);

            case R.id.mDiabetes:
                Toast.makeText(getApplicationContext(), "Shows call icon", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),diabetesTestActivity.class);
                startActivity(in);
                return (true);

            case R.id.logout:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}