package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

public class PreviusAuction_B extends AppCompatActivity {

    RecyclerView buyerRec;
    List<auctioFetchModel> list;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previus_auction__b);

        buyerRec = findViewById(R.id.br);

        buyerRec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        Intent i = getIntent();
        String data = i.getStringExtra("category");
        int status;
        if(data.equals("current")){
            status = 0;
        }
        else {
            status = 1;
        }
        SharedPreferences sp = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        String id = sp.getString("userID","");

        String url = "https://crop-price-app.000webhostapp.com/buyer_auctions.php?status="+status+"&getDateCompare="+data;

        Log.e( "API URL",url);
            getBuyerAuctions(url);
    }


    public void getBuyerAuctions(String urlData){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("no record found")) {

                        } else {
                            try {
                                JSONObject jo = new JSONObject(response);
                                JSONArray ja = jo.getJSONArray("auctions");
                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject o = ja.getJSONObject(i);
                                    auctioFetchModel aModel = new auctioFetchModel(
                                            o.getString("id"),
                                            o.getString("aution_name"),
                                            o.getString("user_id"),
                                            o.getString("product_name"),
                                            o.getString("product_description"),
                                            o.getString("price"),
                                            o.getString("starting_date_time"),
                                            o.getString("end_date_time")
                                    );
                                    list.add(aModel);
                                }
                                adapter = new auctionFetchAdapter(list, getApplicationContext());
                                buyerRec.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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


//    public void getAuctions(String url) {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response.trim().equals("no record found")) {
//
//                        } else {
//                            try {
//                                JSONObject jo = new JSONObject(response);
//                                JSONArray ja = jo.getJSONArray("auctions");
//                                for (int i = 0; i < ja.length(); i++) {
//                                    JSONObject o = ja.getJSONObject(i);
//                                    auctioFetchModel aModel = new auctioFetchModel(
//                                            o.getString("id"),
//                                            o.getString("aution_name"),
//                                            o.getString("product_name"),
//                                            o.getString("product_description"),
//                                            o.getString("price"),
//                                            o.getString("starting_date_time"),
//                                            o.getString("end_date_time")
//                                    );
//                                    list.add(aModel);
//                                }
//                                adapter = new auctionFetchAdapter(list, getApplicationContext());
//                                recyclerView.setAdapter(adapter);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue rq = Volley.newRequestQueue(this);
//        rq.add(stringRequest);
//
//    }
    }