package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultBP extends AppCompatActivity {
    //declare components
    Button back,save;
    TextView Result;
    String Outputdate,OutputTime,OutputBPStats;
    int OutputSysVal,OutputDiaVal;
    dbHandler Healthdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bp);
        Bundle bundle = getIntent().getExtras();
        //bind
        Outputdate=bundle.getString("date");
        OutputTime=bundle.getString("time");
        OutputSysVal=bundle.getInt("systolicvalue");
        OutputDiaVal=bundle.getInt("diastolicvalue");
        OutputBPStats=bundle.getString("result");
        back = findViewById(R.id.btnBack1);
        save = findViewById(R.id.btnSaveData);
        Healthdatabase=new dbHandler(ResultBP.this);

        Result=findViewById(R.id.tvResult);
        Result.setText("Date : "+Outputdate+"\nTime : "+OutputTime+
                "\nSystolic value : "+OutputSysVal+" mm Hg\nDiastolic value : "+OutputDiaVal+
                " mm Hg\nBP Status : Result : "+OutputBPStats);

        String Dispdate2 = Outputdate;
        String Disptime2 = OutputTime;
        String Dispsystolic_value2 = String.valueOf(OutputSysVal);
        String Dispdiastolic_value2 = String.valueOf(OutputDiaVal);
        String DispbpStatus2 = OutputBPStats;

        back.setOnClickListener(new View.OnClickListener() {//function for button back
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BloodPressureMainPage.class);
                startActivity(i);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Healthdatabase.bpData(Dispdate2,Disptime2,Dispsystolic_value2,Dispdiastolic_value2,DispbpStatus2)){
                    Toast.makeText(ResultBP.this, "Data save successful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),ViewResult.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ResultBP.this, "Not successful", Toast.LENGTH_SHORT).show();
                }
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

