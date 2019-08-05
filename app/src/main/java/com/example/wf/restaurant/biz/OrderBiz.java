package com.example.wf.restaurant.biz;

import com.example.wf.restaurant.bean.Order;
import com.example.wf.restaurant.bean.Product;
import com.example.wf.restaurant.config.Config;
import com.example.wf.restaurant.net.CommenCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.Map;

public class OrderBiz {
    public void listByPage(int currentPage , CommenCallBack<List<Order>> commenCallBack){
        OkHttpUtils.post()
                .url(Config.baseUrl + "order_find")
                .tag(this)
                .addParams("currentPage" , currentPage+"")
                .build()
                .execute(commenCallBack);
    }
    public void add(Order order ,CommenCallBack<String> commenCallBack){

        StringBuilder stringBuilder = new StringBuilder();
        Map<Product, Integer> productIntegerMap = order.productIntegerMap;
        for(Product p : productIntegerMap.keySet()){
            stringBuilder.append(p.getId() + "_" +productIntegerMap.get(p));
            stringBuilder.append("|");
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length()-1);

        OkHttpUtils.post()
                .url(Config.baseUrl + "order_add")
                .tag(this)
                .addParams("res_id" , order.getRestaurant().getId()+"")
                .addParams("product_str" , stringBuilder.toString())
                .addParams("count" , order.getCount()+"")
                .addParams("price" , order.getPrice()+"")
                .build()
                .execute(commenCallBack);
    }
}
