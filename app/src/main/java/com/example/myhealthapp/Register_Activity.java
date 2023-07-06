package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Register_Activity extends AppCompatActivity {
    Button registerAcc;
    EditText Name,DOB,Weight,Height,Email,Password;
    RadioGroup Jantina;
    RadioButton BtnGender;
    String Nama,DateOfBirthday,UserEmail,UserPass,Gender;
    double Berat,Tinggi;
    int SelectedGander;
    dbHandler Healthdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerAcc=findViewById(R.id.btnUpdate);
        Name=findViewById(R.id.etEntername);
        DOB=findViewById(R.id.etEnterDOB);
        Weight=findViewById(R.id.etEnterWeight);
        Height=findViewById(R.id.etEnterHeight);
        Email=findViewById(R.id.etEnterEmail);
        Password=findViewById(R.id.etEnterPassword);
        Jantina=findViewById(R.id.rgGenderMF);
        Healthdatabase=new dbHandler(Register_Activity.this);

        registerAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Nama=Name.getText().toString();
                DateOfBirthday=DOB.getText().toString();
                Berat=Double.parseDouble(Weight.getText().toString());
                Tinggi=Double.parseDouble(Height.getText().toString());
                UserEmail=Email.getText().toString();
                UserPass=Password.getText().toString();
                //radio button = get value
                SelectedGander=Jantina.getCheckedRadioButtonId();
                BtnGender=findViewById(SelectedGander);
                Gender=BtnGender.getText().toString();

                if(Healthdatabase.checkUser(UserEmail)){
                    //if user existed..give toast
                    Toast.makeText(getApplicationContext(), "User already existed!!", Toast.LENGTH_LONG).show();

                }else{
                    //if user doesn't exist,push new registeration
                    if (Healthdatabase.newRegistration(Nama, DateOfBirthday, Berat, Tinggi, Gender,  UserEmail,UserPass)) {

                        Toast.makeText(getApplicationContext(), "Registration Succsesfull !!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Failed !!!", Toast.LENGTH_LONG).show();
                    }
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
