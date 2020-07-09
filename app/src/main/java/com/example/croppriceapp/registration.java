package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {

    Spinner role,gender;
    EditText first_name,last_name,email,password,age,phone;
    Button submit;
    final  String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final  String _API = "https://crop-price-app.000webhostapp.com/register.php";
    String i="0";
    String userGender;
    String[] roleList = {"Buyer","Seller"};
    String[] genderList = {"Male","Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        role = findViewById(R.id.role);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);


        ArrayAdapter roleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roleList);
        role.setAdapter(roleAdapter);
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i= String.valueOf(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter genderAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderList);
        gender.setAdapter(genderAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userGender = genderList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_name.getText().toString().equals("")){
                    first_name.setError("First Name required");
                }else if(last_name.getText().toString().equals("")){
                    last_name.setError("Last Name is required");
                }
                else if(email.getText().toString().equals("")){
                    email.setError("Invalid email");
                }else if(password.getText().toString().equals("")){
                    password.setError("Password is required");
                }
                else if(age.getText().toString().equals("")){
                    age.setError("age is required");
                }
                else  if(phone.getText().toString().equals("")){
                    phone.setError("phone is required");
                }
                else {
                    if (email.getText().toString().matches(email_pattern)){
                        userRegister(first_name.getText().toString(), last_name.getText().toString(), email.getText().toString(),
                                password.getText().toString(), age.getText().toString(), phone.getText().toString(), i, userGender);
                    }
                    else {
                        email.setError("Invalid email");
                    }}
                }

        });
    }
        public  void  userRegister(final String f_name, final String l_name, final String email_i, final String password_i, final String age_i, final String phone_i, final String role_i, final String gender_i){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, _API,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("ok")){
                               first_name.setText("");
                               last_name.setText("");
                               email.setText("");
                               password.setText("");
                               age.setText("");
                               phone.setText("");
                                Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(registration.this,login.class);
                                startActivity(i);
                            }
                            else if(response.equals("already")){
                                email.setError("Already email registered");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("first_name",f_name);
                    map.put("last_name",l_name);
                    map.put("email",email_i);
                    map.put("pass",password_i);
                    map.put("age",age_i);
                    map.put("gender",gender_i);
                    map.put("phone",phone_i);
                    map.put("user_type",role_i);

                    return  map;
                }
            };
            RequestQueue referenceQueue = Volley.newRequestQueue(getApplicationContext());
            referenceQueue.add(stringRequest);

        }


}