package com.example.wf.restaurant.bean;

import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {

    public static class ProductVo implements Serializable {
        public Product product;
        public int count;
    }

    public Map<Product , Integer> productIntegerMap = new HashMap<>();

    private int id;
    private Date date;
    Restaurant restaurant;
    private  int count;
    private int price;
    private List<ProductVo> ps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ProductVo> getPs() {
        return ps;
    }

    public void setPs(List<ProductVo> ps) {
        this.ps = ps;
    }
}
