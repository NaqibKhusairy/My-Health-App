package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BMI extends AppCompatActivity {
    //1. declare
    EditText weight,height;
    TextView kilo,meter;
    TextView output;
    Button calculate,reset;
    String ClassBMI,m,k,BMITable[][]= {
            {" Category", "BMI Minimum", "BMI Maximum"},
            {" Underweight", "-", "18.49"},
            {" Normal", "18.5", "25"},
            {" Overweight", "25.1", "30"},
            {" Obese", "30.1", "-"}
    };
    double berat,tinggi,bmi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
//2. bind
        kilo=findViewById(R.id.textView4);
        meter=findViewById(R.id.textView5);
        weight=findViewById(R.id.etWeight);
        height=findViewById(R.id.etHeight);
        output=findViewById(R.id.tvDisp);
        calculate=findViewById(R.id.btnCalculate);
        reset=findViewById(R.id.btnClear);
//3. register listener
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
//onClick is the implementation method
            public void onClick(View view) {
//4. get value
                berat=Double.parseDouble(weight.getText().toString());
                if (berat>=1000)
                {
                    berat/=1000;
                    k="g";
                }
                else
                {
                    berat=berat;
                    k="kg";
                }

                tinggi=Double.parseDouble(height.getText().toString());
                if (tinggi>=100)
                {
                    tinggi/=100;
                    m="cm";
                }
                else
                {
                    tinggi=tinggi;
                    m="m";
                }
//calculation
                bmi=berat/(tinggi*tinggi);

                if (bmi<=18.49)
                {
                    ClassBMI=BMITable[1][0];
                }
                else if (bmi>=18.5 && bmi<=25)
                {
                    ClassBMI=BMITable[2][0];
                }
                else if (bmi>25 && bmi<=30)
                {
                    ClassBMI=BMITable[3][0];
                }
                else if (bmi<=30.1)
                {
                    ClassBMI=BMITable[4][0];
                }
//5. display output
//String.format("%.2f",bmi) to set floating point to two decimal places
                output.setText(String.format("BMI: %.2f%n%s", bmi, ClassBMI));
                kilo.setText(k);
                meter.setText(m);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight.setText("");
                height.setText("");
                output.setText("BMI=0");
                kilo.setText(" [ g / kg ]");
                meter.setText(" [ cm / m ]");
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
