package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchBids extends AppCompatActivity {

    RecyclerView fRv;
    List<bidModel> list;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_bids);

        fRv = findViewById(R.id.bidRv);
        fRv.setLayoutManager(new LinearLayoutManager(this));

    list = new ArrayList<>();

        SharedPreferences p = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        String getUserID = p.getString("userIDz","");
        String url = "https://crop-price-app.000webhostapp.com/fetchBids.php?getUser="+getUserID;
        Log.e("BID URL",url);

        getBids(url);

    }

    public void getBids(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            JSONArray ja = jo.getJSONArray("bids");
                            for(int i =0;i<ja.length();i++){
                                JSONObject o = ja.getJSONObject(i);
                                bidModel bm = new bidModel(o.getString("aution_name"),
                                        o.getString("product_name"),o.getString("product_description")
                                ,o.getString("price"));
                            list.add(bm);
                            }
                            adapter = new bidFetchAdapter(list,getApplicationContext());
                            fRv.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}