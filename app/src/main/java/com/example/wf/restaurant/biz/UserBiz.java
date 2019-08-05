package com.example.wf.restaurant.biz;

import com.example.wf.restaurant.bean.User;
import com.example.wf.restaurant.config.Config;
import com.example.wf.restaurant.net.CommenCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

public class UserBiz {

    public  void login(String username , String password , CommenCallBack<User> commenCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "user_login")
                .tag(this)
                .addParams("username" , username)
                .addParams("password" , password)
                .build()
                .execute(commenCallBack);
    }

    public void register(String username , String password , CommenCallBack<User> commenCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "user_register")
                .tag(this)
                .addParams("username" , username)
                .addParams("password" , password)
                .build()
                .execute(commenCallBack);
    }

}
