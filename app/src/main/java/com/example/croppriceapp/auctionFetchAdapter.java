package com.example.croppriceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class auctionFetchAdapter extends RecyclerView.Adapter<auctionFetchAdapter.ViewHolder>{

    Button buyBid;
    List<auctioFetchModel> auctionList;
    Context ctx;

    public auctionFetchAdapter(List auctionList, Context ctx) {
        this.auctionList = auctionList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public auctionFetchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.card_auction,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull auctionFetchAdapter.ViewHolder holder, int position) {
        final auctioFetchModel md = auctionList.get(position);

        SharedPreferences p = ctx.getSharedPreferences("loginAuth",Context.MODE_PRIVATE);
        String vRole = p.getString("userRole","");
        if(vRole.equals("0"))

        {
        holder.btnBid.setVisibility(View.VISIBLE);

        }
        else if(vRole.equals("1")) {

        }

        holder.btnBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bid_view = new Intent(ctx,Bid_view.class);
                bid_view.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bid_view.putExtra("bid_to",md.auc_by);
                bid_view.putExtra("product",md.id);
                ctx.startActivity(bid_view);
                }
        });

        holder.a_name.setText(md.getAuction());
        holder.a_product.setText(md.getProduct());
        holder.a_description.setText(md.getDescription());
        holder.a_price.setText(md.getPrice());



    }

    @Override
    public int getItemCount() {
        return auctionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView a_name,a_product,a_description,a_price;
    Button btnBid;
    public ViewHolder(@NonNull View itemView) {
            super(itemView);
            a_name = itemView.findViewById(R.id.auc_name);
            a_product = itemView.findViewById(R.id.auc_product);
            a_description = itemView.findViewById(R.id.auc_description);
            a_price = itemView.findViewById(R.id.auc_price);
            btnBid = itemView.findViewById(R.id.auc_bid);
        }
    }
}
