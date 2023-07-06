package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class diabetesTestActivity extends AppCompatActivity {
    RadioButton rbtn,rbtnA1C,rbtnFBS,rbtnGT,rbtnRBS;
    EditText Value;
    Button Calculate;
    RadioGroup Selection;
    String test,result1;
    Double value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_test);

        Calculate = findViewById(R.id.btnCalculate);
        Selection = findViewById(R.id.rgSelection);
        rbtnA1C = findViewById(R.id.rbtnA1C);
        rbtnFBS = findViewById(R.id.rbtnFBS);
        rbtnGT = findViewById(R.id.rbtnGT);
        rbtnRBS = findViewById(R.id.rbtnRBS);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedID=Selection.getCheckedRadioButtonId();
                Value = findViewById(R.id.etValue);
                value = Double.parseDouble(Value.getText().toString());
                int radioID = Selection.getCheckedRadioButtonId();
                rbtn = findViewById(radioID);
                test = rbtn.getText().toString();


                if(rbtnA1C.isChecked()){
                    if(value<5.7){
                        result1 = "normal";
                    }
                    else if(value>=5.7 && value<=6.4){
                        result1 = "Prediabetes";
                    }
                    else if(value>=6.5){
                        result1 = "Diabetes";
                    }
                }
                else if(rbtnFBS.isChecked()){
                    if(value<=99){
                        result1 = "normal";
                    }
                    else if(value>=100 && value<=125){
                        result1 = "Prediabetes";
                    }
                    else if(value>=126){
                        result1 = "Diabetes";
                    }
                }
                else if(rbtnGT.isChecked()){
                    if(value<140){
                        result1 = "normal";
                    }
                    else if(value>=140 && value<=199){
                        result1 = "Prediabetes";
                    } else if (value >= 200) {
                        result1 = "Diabetes";
                    }
                }
                else if(rbtnRBS.isChecked()){
                    if(value>=200){
                        result1 = "Diabetes";
                    }
                    else{
                        result1 = "N/A";
                    }
                }
                Intent g = new Intent(getApplicationContext(),diabetesResultActivity.class);
                Bundle report = new Bundle();
                report.putString("test",test);
                report.putString("result1",result1);
                report.putDouble("value",value);
                g.putExtras(report);
                startActivity(g);
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