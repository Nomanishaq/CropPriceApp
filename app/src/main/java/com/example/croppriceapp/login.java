package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    TextView reg;
    EditText email,password;
    Button loginb;
    final String Login_API = "https://crop-price-app.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginb = findViewById(R.id.login_btn);

        reg = findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, registration.class);
                startActivity(i);

            }
        });
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(),password.getText().toString());
            }
        });

    }
        public void login(final String email, final String password){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Login_API,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("invalid")){
                                Toast.makeText(getApplicationContext(), "invalid Login Details", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.trim().equals("1")){
                             Intent i = new Intent(login.this,BuyerDashboard.class);
                             startActivity(i);
                            }
                            else if(response.trim().equals("2")){
                                Intent i = new Intent(login.this,SellerDashboard.class);
                                startActivity(i);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("userEmail",email);
                    map.put("userpassword",password);

                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

}