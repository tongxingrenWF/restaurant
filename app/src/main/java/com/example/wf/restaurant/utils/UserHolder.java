package com.example.wf.restaurant.utils;

import android.text.TextUtils;

import com.example.wf.restaurant.bean.User;

public class UserHolder {
    private static UserHolder mInstance = new UserHolder();
    private User muser;

    private static final String KEY_USERNAME = "key_username";

    public static UserHolder getmInstance(){
        return mInstance;
    }

    public User getUser() {
        User u = muser;
        if(u == null){
            String username =(String) SPUtils.getInstance().get(KEY_USERNAME , "");
            if(!TextUtils.isEmpty(username)){
                u = new User();
                u.setUsername(username);
            }
        }
        muser = u;
        return muser;
    }

    public void setUser(User user) {
        this.muser = user;
        if(user != null){
            SPUtils.getInstance().put(KEY_USERNAME , muser.getUsername());
        }

    }
}
