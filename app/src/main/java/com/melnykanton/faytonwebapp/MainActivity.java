package com.melnykanton.faytonwebapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.view.View.SCROLLBARS_INSIDE_OVERLAY;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WebViewERROR";

    private WebView mWebView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);

        mWebView = (WebView) findViewById(R.id.myWebView);
        WebSettings mWebStngs = mWebView.getSettings();
        mWebStngs.setJavaScriptEnabled(false);
        mWebStngs.setSupportZoom(true);
        mWebStngs.setLoadWithOverviewMode(true);
        mWebStngs.setUseWideViewPort(false);
        mWebStngs.setDomStorageEnabled(true);
        mWebView.setScrollBarStyle(SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("https://www.fayton.me");
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_about:
                Toast.makeText(MainActivity.this, R.string.site_visit, Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_out:
                super.onBackPressed();
                Toast.makeText(this, R.string.bye, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
