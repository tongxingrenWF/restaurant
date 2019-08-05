package com.example.wf.restaurant.net;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

public abstract class CommenCallBack<T> extends StringCallback {

    Type  mType;
    public CommenCallBack(){
        Class<? extends CommenCallBack> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();

        if(genericSuperclass instanceof Class){
            throw new RuntimeException("Miss Type Params");
        }
        ParameterizedType parameterizedType =(ParameterizedType) genericSuperclass;
        mType = parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {
        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.optInt("resultCode");
            if(resultCode == 1){//成功
                String data = resp.optString("data");
                Gson gson = new Gson();
                onSuccess((T)gson.fromJson(data , mType));
            }else {
                onError(new Exception(resp.optString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public abstract void onError(Exception e);
    public abstract void onSuccess(T response);

}
