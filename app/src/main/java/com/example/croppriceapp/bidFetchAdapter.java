package com.example.croppriceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class bidFetchAdapter extends RecyclerView.Adapter<bidFetchAdapter.ViewHolder> {

    List<bidModel> model;
    Context ctx;

    public bidFetchAdapter(List<bidModel> model, Context ctx) {
        this.model = model;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public bidFetchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.show_bid_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull bidFetchAdapter.ViewHolder holder, int position) {
        bidModel md = model.get(position);

        holder.showAuction.setText(md.getAuction_name());
        holder.showProduct.setText(md.getProduct_name());
        holder.showDescription.setText(md.getProduct_auction());
        holder.showPrice.setText(md.getProduct_price());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView showAuction,showProduct,showDescription,showPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showAuction = itemView.findViewById(R.id.bidAuctionName);
            showProduct = itemView.findViewById(R.id.bidProductName);
            showDescription = itemView.findViewById(R.id.bidProductDescription);
            showPrice = itemView.findViewById(R.id.bidAuctionPrice);
        }
    }
}
