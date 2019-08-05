package com.example.wf.restaurant.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wf.restaurant.R;
import com.example.wf.restaurant.bean.User;
import com.example.wf.restaurant.biz.UserBiz;
import com.example.wf.restaurant.net.CommenCallBack;

public class RegisterActivity extends BaseActitity {

    private EditText phone;
    private EditText password;
    private EditText repassword;
    private Button register;

    private UserBiz userBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpToolBar();
        initView();
        initEvent();
        setTitle("register");
    }

    private void initEvent() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = phone.getText().toString();
                String pwd = password.getText().toString();
                String repwd = repassword.getText().toString();

                if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(repwd)){
                    Toast.makeText(RegisterActivity.this , "都不能为空"  , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(repwd)){
                    Toast.makeText(RegisterActivity.this , "前后密码不一致"  , Toast.LENGTH_SHORT).show();
                    return;
                }
                startLoadingProgress();

                userBiz.register(uname, pwd, new CommenCallBack<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        Toast.makeText(RegisterActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(User response) {
                        stopLoadingProgress();
                        Toast.makeText(RegisterActivity.this , "注册成功："+response.getUsername()  , Toast.LENGTH_SHORT).show();
                        LoginActivity.launch(RegisterActivity.this , response.getUsername() , response.getPassword());
                        finish();
                    }
                });
            }
        });
    }

    private void initView() {
        phone = findViewById(R.id.phone_num);
        password = findViewById(R.id.edt_password);
        repassword = findViewById(R.id.edt_repassword);
        register = findViewById(R.id.register);
    }
}
