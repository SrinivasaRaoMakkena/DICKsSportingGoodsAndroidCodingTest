package com.example.dsgandroidapp.module.website;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.dsgandroidapp.R;
import com.example.dsgandroidapp.base.ParentActivity;

import butterknife.Bind;


public class WebsiteActivity extends ParentActivity {
    WebView browser;
    ProgressDialog progDailog;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        browser = (WebView) findViewById(R.id.website);
        progDailog = ProgressDialog.show(WebsiteActivity.this, "Loading", "Please wait...", true);
        progDailog.setCancelable(true);

        Intent i = getIntent();
        System.out.println("url " + i.getStringExtra("url"));
        browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        configureWebView(browser);

        browser.loadUrl(i.getStringExtra("url"));
        showBackArrow();
        setTitle("DSG's Website");


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_website;
    }

    private void configureWebView(WebView webview) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new InnerWebViewClient());
        webview.setInitialScale(100);

    }

    private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progDailog.show();
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, final String url) {
            progDailog.dismiss();
        }

    }
}
