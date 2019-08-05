package com.example.wf.restaurant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wf.restaurant.R;

public class SplashActivity extends AppCompatActivity {
    private Button btn_toLogin;
    private Handler handler = new Handler();
    private Runnable mRunnableToLogin  = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
            initEvents();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        handler.postDelayed(mRunnableToLogin , 3*1000);
    }

    private void initView() {
         btn_toLogin = findViewById(R.id.btn_skip);
    }
    private void initEvents() {
        btn_toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(mRunnableToLogin);
                toLoginActivity();
            }
        });
    }
    private void toLoginActivity(){
        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mRunnableToLogin);
    }
}
