package com.example.wf.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wf.restaurant.R;
import com.example.wf.restaurant.bean.Order;
import com.example.wf.restaurant.config.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> mDatas;
    private Context context;
    private LayoutInflater layoutInflater;

    public OrderAdapter(List<Order> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = layoutInflater.inflate(R.layout.item_order_list , viewGroup ,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Order order = mDatas.get(i);

        //放图片
        Picasso.with(context)
                .load(Config.baseUrl+order.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(myViewHolder.imageView);

        if(order.getPs().size()>0){
            myViewHolder.tv_lable.setText(order.getPs().get(0)
                    .product.getName()+"等"+order.getCount()+"件商品");
        }else {
            myViewHolder.tv_lable.setText("无消费");
        }

        //放文字
        myViewHolder.tv_name.setText(order.getRestaurant().getName());
        myViewHolder.tv_price.setText("共消费："+order.getPrice()+"元");


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView tv_name;
        public TextView tv_lable;
        public TextView tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
            imageView = itemView.findViewById(R.id.id_iv_img);
            tv_name = itemView.findViewById(R.id.res_name);
            tv_lable = itemView.findViewById(R.id.res_label);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
