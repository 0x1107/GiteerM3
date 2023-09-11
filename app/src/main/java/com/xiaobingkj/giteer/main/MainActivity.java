package com.xiaobingkj.giteer.main;

import com.tencent.mmkv.MMKV;
import com.xiaobingkj.giteer.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.login.LoginActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MMKV kv = MMKV.defaultMMKV();
        //如果没有Token进入登录页面
        if (kv.getString("token", "").length() == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}