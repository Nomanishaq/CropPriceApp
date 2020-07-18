package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class Bid_view extends AppCompatActivity {

    EditText price_bid;
    Button btn_bid_add;
    final String ADD_BID_URL = "https://crop-price-app.000webhostapp.com/bid_now.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_view);

        Intent i = getIntent();
        final String bid_to = i.getStringExtra("bid_to");
        SharedPreferences p = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        final String bid_by = p.getString("userID","");
        final String product_id = i.getStringExtra("product");
        price_bid = findViewById(R.id.edtPrice);
        btn_bid_add = findViewById(R.id.btnFinalBid);

    btn_bid_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bidNow(bid_by,bid_to,product_id,price_bid.getText().toString());
        }
    });
    }

    public void bidNow(final String by, final String to, final String prod, final String price){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ADD_BID_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("ok")){
                            Intent i = new Intent(Bid_view.this,BuyerDashboard.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Bid_view.this, response, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("bid_by",by);
                map.put("bid_to",to);
                map.put("p_id",prod);
                map.put("price",price);
                return map;

            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);

    }
}