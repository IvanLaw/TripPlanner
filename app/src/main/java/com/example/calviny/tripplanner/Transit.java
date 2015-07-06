package com.example.calviny.tripplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Transit extends Activity {
 
	WebView mWebView = null;
	String data_val = "123";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transit);
		
		Intent intent = getIntent();
		String from = intent.getStringExtra("from");
		String to = intent.getStringExtra("to");
		
		mWebView = (WebView)findViewById(R.id.webview);
		mWebView.setWebViewClient(mWebViewClient);
		mWebView.getSettings().setJavaScriptEnabled(true);  
		mWebView.loadUrl("file:///android_asset/html/trainsit.html?from=" + from + "&to=" + to);
	}
	
	WebViewClient mWebViewClient = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};
}