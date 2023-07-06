package com.example.myhealthapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyBPApp extends AppCompatActivity {
    //declare component
    Button btnCalculate,btnClear;
    EditText date,time,systolicvalue,diastolicvalue;
    int Systolicvalue = 0 , Diastolicvalue = 0;
    String result,tarikhinput,masainput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bpapp);

        //bind
        date = findViewById(R.id.etDate);
        time = findViewById(R.id.etTime);
        systolicvalue = findViewById(R.id.etSvalue);
        diastolicvalue = findViewById(R.id.etDvalue);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);


        btnCalculate.setOnClickListener(new View.OnClickListener() {//function for btn calculate
            @Override
            public void onClick(View view) {

                Systolicvalue = Integer.parseInt(systolicvalue.getText().toString());
                Diastolicvalue = Integer.parseInt(diastolicvalue.getText().toString());
                tarikhinput = date.getText().toString();
                masainput = time.getText().toString();
                if (Systolicvalue<120 && Diastolicvalue<80)
                {
                    result = "Normal";
                }
                else if ((Systolicvalue>=120 && Systolicvalue<=129)&&(Diastolicvalue<80))
                {
                    result = "Elevated";
                }
                else if ((Systolicvalue>=130 && Systolicvalue<=139)||(Diastolicvalue>=80 && Diastolicvalue<=89))
                {
                    result = "High Blood Pressure STAGE 1";
                }
                else if ((Systolicvalue>=140&&Systolicvalue<=180)||(Diastolicvalue>=90&&Diastolicvalue<=120))
                {
                    result = "High Blood Pressure STAGE 2";
                }
                else if (Systolicvalue>180 && Systolicvalue>120)
                {
                    result = "Hypertensive Crisis \n CONSULT YOUR DOCTOR IMMEDIATELY";
                }
                Intent i = new Intent(getApplicationContext(),ResultBP.class);
                Bundle call= new Bundle();
                call.putString("date",tarikhinput);
                call.putString("time",masainput);
                call.putString("result",result);
                call.putInt("systolicvalue",Systolicvalue);
                call.putInt("diastolicvalue",Systolicvalue);
                i.putExtras(call);
                startActivity(i);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {//function btn clear
            @Override
            public void onClick(View view) {
                date.setText("");
                time.setText("");
                systolicvalue.setText("");
                diastolicvalue.setText("");
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