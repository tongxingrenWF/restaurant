package com.example.wf.restaurant.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wf.restaurant.R;
import com.example.wf.restaurant.bean.Order;
import com.example.wf.restaurant.bean.User;
import com.example.wf.restaurant.biz.OrderBiz;
import com.example.wf.restaurant.net.CommenCallBack;
import com.example.wf.restaurant.ui.adapter.OrderAdapter;
import com.example.wf.restaurant.ui.view.CircleTransform;
import com.example.wf.restaurant.ui.view.refresh.SwipeRefresh;
import com.example.wf.restaurant.ui.view.refresh.SwipeRefreshLayout;
import com.example.wf.restaurant.utils.UserHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActitity {

    private Button mBtnorder;
    private TextView mTvusername;
    private OrderAdapter orderAdapter;
    private RecyclerView mecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageView mIcon;
    private List<Order> mDatas = new ArrayList<>();
    private OrderBiz orderBiz = new OrderBiz();
    private int mCurrentPage  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        initEvent();
        loadDatas();
    }

    private void initView() {
        mBtnorder = findViewById(R.id.btn_order);
        mTvusername = findViewById(R.id.tv_username);
        mecyclerView = findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = findViewById(R.id.swipRefreshLayout);
        mIcon = findViewById(R.id.img_icon);

        User user = UserHolder.getmInstance().getUser();
        if (user != null) {
            mTvusername.setText(user.getUsername());
        } else {
            toLoginActivity();
            return;
        }

        //代表上拉下拉都支持
        mSwipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.YELLOW);

        //recyclerView TODO
        orderAdapter = new OrderAdapter(mDatas, this );
        mecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mecyclerView.setAdapter(orderAdapter);

        Picasso.with(this)
                .load(R.drawable.icon)
                .placeholder(R.drawable.pictures_no)
                .transform(new CircleTransform())
                .into(mIcon);

    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas();
            }
        });
        mSwipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });
        mBtnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this , ProductListActivity.class);
                startActivityForResult(intent , 10001);
            }
        });
    }

    private void loadMore() {
        startLoadingProgress();
        orderBiz.listByPage(++mCurrentPage, new CommenCallBack<List<Order>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                mCurrentPage--;
            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLoadingProgress();
                if(response.size() == 0){
                    Toast.makeText(OrderActivity.this , "没订单了"  , Toast.LENGTH_SHORT).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                    return;
                }else {
                    Toast.makeText(OrderActivity.this , "订单更新成功" , Toast.LENGTH_SHORT).show();
                    mDatas.addAll(response);
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadDatas() {
        startLoadingProgress();
        orderBiz.listByPage(0 , new CommenCallBack<List<Order>>(){

            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLoadingProgress();
                mCurrentPage = 0;
                Toast.makeText(OrderActivity.this , "订单更新成功" , Toast.LENGTH_SHORT).show();
                mDatas.clear();
                mDatas.addAll(response);
                orderAdapter.notifyDataSetChanged();
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }


}
