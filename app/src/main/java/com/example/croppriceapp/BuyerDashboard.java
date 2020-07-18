package com.example.croppriceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BuyerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ImageView activeAuction_b,previousAuction_b,accountDetail_b,logOut_b;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);

//      redirect to account Detail
        accountDetail_b = findViewById(R.id.account_b);
        accountDetail_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(BuyerDashboard.this,Profile_view.class);
                startActivity(profile);
            }
        });


//      redirect to active auction
        activeAuction_b = findViewById(R.id.active_Auction_b);
        activeAuction_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent active = new Intent(BuyerDashboard.this,PreviusAuction_B.class);
                active.putExtra("category","current");
                startActivity(active);
            }
        });


 //      redirect to previous auction
        previousAuction_b = findViewById(R.id.previous_auction_b);
        previousAuction_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previous = new Intent(BuyerDashboard.this,FetchBids.class);
                    previous.putExtra("category","previous");
                startActivity(previous);
            }
        });

        logOut_b = findViewById(R.id.logout_b);
        logOut_b.setOnClickListener(new View.OnClickListener() {
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