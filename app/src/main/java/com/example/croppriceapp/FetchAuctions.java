package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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

public class FetchAuctions extends AppCompatActivity {

    RecyclerView recyclerView;
    List<auctioFetchModel> list;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_auctions);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
        String id = sp.getString("userID","");
        String URL = "https://crop-price-app.000webhostapp.com/seller_auctions.php?userID="+id+"&status=0";

        getAuctions(URL);
    }

    public void getAuctions(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("no record found")){

                        }
                        else{
                            try {
                                JSONObject jo = new JSONObject(response);
                                JSONArray ja = jo.getJSONArray("auctions");
                                for(int i = 0;i<ja.length();i++){
                                    JSONObject o = ja.getJSONObject(i);
                                    auctioFetchModel aModel = new auctioFetchModel(
                                            o.getString("id"),
                                            o.getString("aution_name"),
                                            o.getString("product_name"),
                                            o.getString("product_description"),
                                            o.getString("price"),
                                            o.getString("starting_date_time"),
                                            o.getString("end_date_time")
                                    );
                                    list.add(aModel);
                                }
                                adapter = new auctionFetchAdapter(list,getApplicationContext());
                                recyclerView.setAdapter(adapter);
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


}