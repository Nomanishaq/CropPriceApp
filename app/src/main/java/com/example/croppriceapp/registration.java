package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    final  String _API = "https://crop-price-app.000webhostapp.com/register.php";
    String i="0";
    String userGender;
    String[] roleList = {"Select","Buyer","Seller"};
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
                    first_name.setError("First Name Requireds");
                }
//                userRegister(first_name.getText().toString(),last_name.getText().toString(),email.getText().toString(),
//                        password.getText().toString(),age.getText().toString(),phone.getText().toString(),i,userGender);
            }
        });
    }
        public  void  userRegister(final String first_name, final String last_name, final String email, final String password, final String age, final String phone, final String role, final String gender){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, _API,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("ok")){
                                Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
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
                    map.put("first_name",first_name);
                    map.put("last_name",last_name);
                    map.put("email",email);
                    map.put("pass",password);
                    map.put("age",age);
                    map.put("gender",gender);
                    map.put("phone",phone);
                    map.put("user_type",role);

                    return  map;
                }
            };
            RequestQueue referenceQueue = Volley.newRequestQueue(getApplicationContext());
            referenceQueue.add(stringRequest);

        }


}