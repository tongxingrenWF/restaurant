package com.example.wf.restaurant.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ProductListAdapter extends RecyclerView.Adapter <ProductListAdapter.MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

      class MyViewHolder extends RecyclerView.ViewHolder{



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
