package com.example.wf.restaurant.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;

import com.example.wf.restaurant.R;

public class BaseActitity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
             getWindow().setStatusBarColor(0xff000000);
         }
         progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("加载中...");
    }
    protected void stopLoadingProgress() {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    protected void startLoadingProgress() {
        progressDialog.show();
    }

    protected void setUpToolBar(){
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        progressDialog = null;
    }

    protected void toLoginActivity() {

    }

}
