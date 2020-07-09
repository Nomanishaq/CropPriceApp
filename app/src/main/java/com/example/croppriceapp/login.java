package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    TextView reg;
    EditText email,password;
    String uID, uRole;
    Button loginb;
    final  String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final String Login_API = "https://crop-price-app.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginb = findViewById(R.id.login_btn);

        SharedPreferences p = getSharedPreferences("loginAuth",Context.MODE_PRIVATE);
        String vRole = p.getString("userRole","");
        if(vRole.equals("0"))
        {

            Intent s = new Intent(login.this,BuyerDashboard.class);
            startActivity(s);

        }
        else if(vRole.equals("1")){
            Intent b= new Intent(login.this,SellerDashboard.class);
            startActivity(b);

        }


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
        if (email.getText().toString().matches(email_pattern)){
            if(password.getText().toString().equals("")){
                password.setError("Please fill Password");
            }

            else {
                login(email.getText().toString(),password.getText().toString());
            }
        }
        else {
            email.setError("invalid email");
        }
            }
        });

    }
        public void login(final String email_p, final String password_p){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Login_API,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("invalid")){
                                password.setText("");
                                Toast.makeText(getApplicationContext(), "invalid Login Details", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                try {
                                    JSONObject jo = new JSONObject(response);
                                    JSONArray ja = jo.getJSONArray("details");
                                    for(int i =0;i<ja.length();i++){
                                        JSONObject obj = ja.getJSONObject(i);
                                        uID = obj.getString("id");
                                        uRole = obj.getString("role");
                                        SharedPreferences sp = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor ed = sp.edit();
                                        ed.putString("userEmail",obj.getString("email"));
                                        ed.putString("userID",uID);
                                        ed.putString("userRole",uRole);
                                        ed.commit();
                                        if(uRole.equals("0"))
                                        {

                                Intent s = new Intent(login.this,BuyerDashboard.class);
                                startActivity(s);
                                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();

                                        }
                                        else if(uRole.equals("1")){
                                            Intent b= new Intent(login.this,SellerDashboard.class);
                                            startActivity(b);
                                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();

                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

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
                    Map<String, String> map = new HashMap<>();
                    map.put("userEmail",email_p);
                    map.put("userpassword",password_p);

                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

}