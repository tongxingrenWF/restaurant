package com.example.wf.restaurant;

import android.app.Application;

import com.example.wf.restaurant.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ResApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(this, "sp_res.pref");

        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
