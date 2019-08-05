package com.example.wf.restaurant.biz;

import com.example.wf.restaurant.bean.Product;
import com.example.wf.restaurant.config.Config;
import com.example.wf.restaurant.net.CommenCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

public class ProductBiz {

    public void  listBypage(int currentPage , CommenCallBack<List<Product>> commenCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"product_find")
                .addParams("currentPage" , currentPage+"")
                .build()
                .execute(commenCallBack);
    }
}
