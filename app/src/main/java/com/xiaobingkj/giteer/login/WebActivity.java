package com.xiaobingkj.giteer.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {
    private ActivityWebBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String ClientID = "6c2acdc7e2348399036735675b029171c5fafd7ffcbe682e5cd697e1d250e6e1";
        String ClientSecret = "a6ce86609de6851d0158b8499d4ba7cee4cfa839914403ca4fcb27cfd8094d50";
        String RedirectUri = "giteer://oauth-callback";

        String url = "https://gitee.com/oauth/authorize?client_id=" + ClientID + "&redirect_uri=" + RedirectUri + "&response_type=code";
        Log.i("WebActivity", "请求授权URL:" + url);

        WebView webView = binding.webview;

        WebSettings settings = webView.getSettings(); //和系统webview一样
        settings.setUseWideViewPort(true); //自适应屏幕

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        });

        webView.loadUrl(url);
    }
}