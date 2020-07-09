package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile_view extends AppCompatActivity {

    TextView first_name,last_name,email_address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        first_name = findViewById(R.id.first_name_val);
        last_name = findViewById(R.id.last_name_val);
        email_address = findViewById(R.id.email_val);
        phone  = findViewById(R.id.phone_val);


        SharedPreferences sp = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        String id = sp.getString("userID","");
        String email = sp.getString("userEmail","");
        String URL = "https://crop-price-app.000webhostapp.com/fetch_profile.php?userID="+id+"&email="+email;

        fetchProfile(URL);


    }

    public void fetchProfile(String url)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            JSONArray jan= jo.getJSONArray("details");
                            for(int i=0;i<jan.length();i++){
                                JSONObject j = jan.getJSONObject(i);
                            first_name.setText(j.getString("first_name"));
                            last_name.setText(j.getString("last_name"));
                            email_address.setText(j.getString("email"));
                            phone.setText(j.getString("phone"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);
    }
}
