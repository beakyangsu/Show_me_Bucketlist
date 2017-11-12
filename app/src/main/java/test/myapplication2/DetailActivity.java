package test.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsu on 2017-11-01.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent receive = getIntent();
        Item item =  receive.getParcelableExtra("data");
        setLayout(item);

    }

    protected void setLayout(Item item){
        if(item != null){
            TextView name = (TextView) findViewById(R.id.name);
            TextView address = (TextView) findViewById(R.id.address);

            WebView mWebView = (WebView)findViewById(R.id.webview);
            String url = item.getUrl();
            if(mWebView != null && url != null) {
                mWebView.setWebViewClient(new WebViewClient());
                mWebView.clearCache(true);
                mWebView.clearHistory();
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.loadUrl(url);
            }

            String itemName = item.getName();
            if(name != null && itemName != null)
                name.setText("name : " + itemName);

            String itemAddress = item.getAddress();
            if(address != null)
                address.setText("address : " + itemAddress);
        }
    }
}
