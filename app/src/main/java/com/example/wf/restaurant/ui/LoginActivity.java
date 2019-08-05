package com.example.wf.restaurant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wf.restaurant.R;
import com.example.wf.restaurant.bean.User;
import com.example.wf.restaurant.biz.UserBiz;
import com.example.wf.restaurant.net.CommenCallBack;
import com.example.wf.restaurant.utils.UserHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

public class LoginActivity extends BaseActitity {

    private UserBiz userBiz = new UserBiz();

    private EditText username;
    private EditText password;
    private Button btn_login;
    private TextView tv_register;

    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";

    public static void launch(Context context, String username, String password) {
        Intent intent = new Intent(context , LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_USERNAME , username);
        intent.putExtra(KEY_PASSWORD , password);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        cookieJar.getCookieStore().removeAll();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        initIntent(getIntent());
    }

    private void initView() {
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
    }

    private void initEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pwd = password.getText().toString();

                if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this , "账号或者密码不能为空" , Toast.LENGTH_SHORT).show();
                    return;
                }

                startLoadingProgress();

                userBiz.login(uname, pwd, new CommenCallBack<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        Toast.makeText(LoginActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(User response) {
                        stopLoadingProgress();
                        Toast.makeText(LoginActivity.this , "登录成功" , Toast.LENGTH_SHORT).show();
                       //保存用户信息
                        UserHolder.getmInstance().setUser(response);
                        toOrderActivity();
                    }
                });

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }

    private void toOrderActivity() {
        Intent intent = new Intent(this , OrderActivity.class);
        startActivity(intent);
    }

    private void toRegisterActivity() {
        Intent intent = new Intent(this , RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        if(intent==null){
            return;
        }
        String uname = intent.getStringExtra(KEY_USERNAME);
        String pwd = intent.getStringExtra(KEY_PASSWORD);

        if(TextUtils.isEmpty(uname) | TextUtils.isEmpty(pwd)){
            return;
        }

        username.setText(uname);
        password.setText(pwd);
    }
}
