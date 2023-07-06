package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1st declare
    Button login;
    EditText uName,uPass;
    TextView register;
    String userName,userPassword;
    dbHandler Healthdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.btnLogin);
        uName = findViewById(R.id.txtName);
        uPass = findViewById(R.id.txtPassword);
        register = findViewById(R.id.tvRegister);
        Healthdatabase=new dbHandler(MainActivity.this);


        //3rd give function
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = uName.getText().toString().trim();
                userPassword = uPass.getText().toString().trim();



                if(userName.isEmpty() && userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill in username and password",Toast.LENGTH_LONG).show();
                }else{

                if(Healthdatabase.loginUser(userName,userPassword)){
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),MainMenu.class);
                    i.putExtra("USERN",userName);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Username or Password invalid",Toast.LENGTH_LONG).show();
                }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(intent);
            }
        });


    }
}