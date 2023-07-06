package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {
    Button back,save;
    TextView disp;
    double weight,height,bmr;
    int umur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_result);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        back=findViewById(R.id.btnBack);


        disp=findViewById(R.id.tvDisp);
// getting the bundle from the intent
        Bundle bundle = getIntent().getExtras();
// setting the text in the textview
        weight=bundle.getDouble("berat");
        height=bundle.getDouble("tinggi");
        bmr=bundle.getDouble("bmr");
        umur=bundle.getInt("umur");
        disp.setText("Weight: "+weight+"kg\nHeight: "+height+"cm\nAge: "+umur+"yrs\nBMR: "+bmr+"calories/day");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), BMI.class);
                startActivity(intent);
            }
        });



    }
}
