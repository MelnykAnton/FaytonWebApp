package com.melnykanton.faytonwebapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.myWebView);
        WebSettings mWebStngs = mWebView.getSettings();
        mWebStngs.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://fayton.me");
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
