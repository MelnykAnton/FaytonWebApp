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
import android.webkit.WebChromeClient;
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
        mWebStngs.setJavaScriptEnabled(true);
        mWebStngs.setSupportZoom(true);
        mWebStngs.setUseWideViewPort(true);
        mWebView.setWebViewClient(new MyWebViewClient());
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

    private class MyWebViewClient extends WebViewClient {

    }

    /*private class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            String message = "SSL Certificate error.";
            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    message = "The certificate authority is not trusted.";
                    break;
                case SslError.SSL_EXPIRED:
                    message = "The certificate has expired.";
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "The certificate Hostname mismatch.";
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "The certificate is not yet valid.";
                    break;
            }

            message += "\"SSL Certificate Error\" Do you want to continue anyway?.. YES";

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
            handler.proceed();
        }
    }*/
}
