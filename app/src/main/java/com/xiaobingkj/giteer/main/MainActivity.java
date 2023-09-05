package com.xiaobingkj.giteer.main;

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

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}