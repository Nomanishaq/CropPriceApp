package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SellerDashboard extends AppCompatActivity {
    ImageView Add_new_auction,activeAuction,previousAuction,totalSale,accountDetail,logOut;
     SharedPreferences mSharedPreferences;
     SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        logOut = findViewById(R.id.logout_s);

//       redirect to add new auction page
        Add_new_auction = findViewById(R.id.add_new_auction_s);
        Add_new_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(SellerDashboard.this,Add_auction.class);
                startActivity(add);
            }
        });

//      redirect to account Detail
        accountDetail = findViewById(R.id.view_account_s);
        accountDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(SellerDashboard.this,Profile_view.class);
                startActivity(profile);
            }
        });

//      redirect to Active Auction
        activeAuction = findViewById(R.id.active_auction_s);
        activeAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activeAuc = new Intent(SellerDashboard.this,FetchAuctions.class);
                startActivity(activeAuc);
            }
        });

//      redirect to Previous Auction
        previousAuction = findViewById(R.id.previous_auction_s);
        previousAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousAuct = new Intent(SellerDashboard.this,PreviusAuction.class);
                startActivity(previousAuct);
            }
        });




        //      redirect to Total sales
        totalSale = findViewById(R.id.totoal_seles_s);
        totalSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent totalS = new Intent(SellerDashboard.this,FetchBids.class);
                startActivity(totalS);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences sp = getSharedPreferences("loginAuth", Context.MODE_PRIVATE);
               sp.edit().clear().apply();


                Intent i =new Intent(getApplicationContext(),login.class);
                    startActivity(i);
            }
        });


    }
}