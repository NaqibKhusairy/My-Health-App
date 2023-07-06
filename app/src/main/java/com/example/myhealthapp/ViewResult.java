package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewResult extends AppCompatActivity {
    private ArrayList<blood> dirModalArrayList;
    private dbHandler dh;
    private DirRVAdapter dirRVAdapter;
    private RecyclerView dirRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        // initializing our all variables.
        dirModalArrayList = new ArrayList<>();
        dh = new dbHandler(ViewResult.this);

        // getting our course array
        // list from db handler class.
        dirModalArrayList = dh.readDir();

        int recordCount = dh.count();
        TextView textViewRecordCount = findViewById(R.id.tvRecord);
        textViewRecordCount.setText(recordCount +" records found.");

        // on below line passing our array list to our adapter class.
        dirRVAdapter = new DirRVAdapter(dirModalArrayList, ViewResult.this);
        dirRV = findViewById(R.id.rvDir);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewResult.this, RecyclerView.VERTICAL, false);
        dirRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        dirRV.setAdapter(dirRVAdapter);

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