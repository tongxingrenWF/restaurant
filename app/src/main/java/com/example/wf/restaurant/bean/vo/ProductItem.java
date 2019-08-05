package com.example.wf.restaurant.bean.vo;

import com.example.wf.restaurant.bean.Product;
import com.example.wf.restaurant.bean.Restaurant;

public class ProductItem extends Product {
    private int count;

    public ProductItem(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.label = product.getLabel();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.icon = product.getIcon();
        this.restaurant = product.getRestaurant();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
