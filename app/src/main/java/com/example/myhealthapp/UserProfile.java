package com.example.myhealthapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    EditText name,dob,weight,height,mail,pass;
    RadioButton male, female;
    Button update;
    dbHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //calling action bar
        ActionBar actionBar = getSupportActionBar();
        //show back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        //get value from intent
        String email = extras.getString("USERNAME");

        name = findViewById(R.id.etUName);
        dob = findViewById(R.id.etUDoB);
        weight = findViewById(R.id.etUWeight);
        height = findViewById(R.id.etUHeight);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        mail = findViewById(R.id.etUEmail);
        pass = findViewById(R.id.etUPass);
        update = findViewById(R.id.btnUpdate);

        dbh = new dbHandler(UserProfile.this);
        ArrayList<userModel> umd = dbh.getUserProfile(email);
        userModel mdl = umd.get(0);
        name.setText(mdl.getName());
        dob.setText(mdl.getDob());
        weight.setText(String.valueOf(mdl.getWeight()));
        height.setText(String.valueOf(mdl.getHeight()));
        String val=mdl.getGender();

        if(val.equals("Male"))
        {
            male.setChecked(true);
        }
        else
        {
            female.setChecked(true);
        }
        mail.setText(mdl.getMail());
        pass.setText(mdl.getPass());
    }


}