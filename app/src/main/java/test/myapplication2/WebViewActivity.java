package test.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by yangsu on 2017-11-12.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private Item item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent recieve = getIntent();
        final String searchInput = recieve.getStringExtra("searchInput");
        final String apiUrl = recieve.getStringExtra("apiUrl");


        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(apiUrl);

        TextView name = (TextView)findViewById(R.id.name);
        TextView address = (TextView)findViewById(R.id.address);

        name.setText("name : "+ searchInput);
        address.setText("address : " + searchInput);

        item = new Item(-1, searchInput, searchInput, null, null, apiUrl);
        //TODO : put right data value

        Button okButton = (Button)findViewById(R.id.okButton);
        okButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        });

    }

}
