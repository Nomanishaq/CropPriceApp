package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_auction extends AppCompatActivity {
    final  String  API = "https://crop-price-app.000webhostapp.com/add_auction.php";

    EditText auction_name,product_name,description,end_date,price;
    Button add_btn;
        final String DATE_REG ="([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))";
        String getUserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auction);
        auction_name = findViewById(R.id.auction_name);
        product_name = findViewById(R.id.product_name);
        description = findViewById(R.id.auction_description);
        end_date = findViewById(R.id.end_date);
        price = findViewById(R.id.price);
        add_btn = findViewById(R.id.add_btn);

        SharedPreferences g = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        getUserID = g.getString("userID","");



        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(end_date.getText().toString().matches(DATE_REG)){
                    addAuction(auction_name.getText().toString(),product_name.getText().toString(), description.getText().toString(),end_date.getText().toString(),price.getText().toString());
                }
                else{
                    end_date.setError("Date Must be In YYYY/MM/DD Format");
                }

            }
        });

    }

    public void addAuction(final String name, final String product, final String description, final String end_date, final String price){
        StringRequest rq= new StringRequest(Request.Method.POST, API,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")){
                    auction_name.setText("");
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
                map.put("auction_name",name);
                map.put("auction_product",product);
                map.put("auction_descripion",description);
                map.put("auction_end_date",end_date);
                map.put("auction_price",price);
                map.put("getUser",getUserID);
                return  map;
            }
        };
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(rq);
        }


}