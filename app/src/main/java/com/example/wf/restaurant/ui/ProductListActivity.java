package com.example.wf.restaurant.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wf.restaurant.R;
import com.example.wf.restaurant.ui.view.refresh.SwipeRefresh;
import com.example.wf.restaurant.ui.view.refresh.SwipeRefreshLayout;

import org.w3c.dom.Text;

public class ProductListActivity extends BaseActitity {

    private SwipeRefreshLayout mswipeRefreshLayout;
    private RecyclerView mrecyclerView;
    private TextView tv_mCount;
    private Button btn_mpay_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        setUpToolBar();
        setTitle("订餐");
        initView();
        initEvent();
    }

    private void initEvent() {
        mswipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadmore();
            }
        });
        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas();
            }
        });
        btn_mpay_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    private void loadDatas() {

    }

    private void loadmore() {

    }

    private void initView() {
        mswipeRefreshLayout = findViewById(R.id.swipRefreshLayout);
        mrecyclerView = findViewById(R.id.id_recyclerView);
        tv_mCount = findViewById(R.id.tv_count);
        btn_mpay_price = findViewById(R.id.btn_price_pay);

        //代表上拉下拉都支持
        mswipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        mswipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.YELLOW);
    }
}
