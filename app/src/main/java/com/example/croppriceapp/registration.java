package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class registration extends AppCompatActivity {

    Spinner role;
    Spinner gender;
    String[] roleList = {"Seller","Buyer"};
    String[] genderList = {"Male","Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        role = findViewById(R.id.role);
        gender = findViewById(R.id.gender);

        ArrayAdapter roleAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,roleList);
        role.setAdapter(roleAdapter);
        ArrayAdapter genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,genderList);
        gender.setAdapter(genderAdapter);
    }
}