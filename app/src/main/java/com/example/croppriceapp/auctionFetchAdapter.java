package com.example.croppriceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class auctionFetchAdapter extends RecyclerView.Adapter<auctionFetchAdapter.ViewHolder>{

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
        auctioFetchModel md = auctionList.get(position);

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            a_name = itemView.findViewById(R.id.auc_name);
            a_product = itemView.findViewById(R.id.auc_product);
            a_description = itemView.findViewById(R.id.auc_description);
            a_price = itemView.findViewById(R.id.auc_price);
        }
    }
}
