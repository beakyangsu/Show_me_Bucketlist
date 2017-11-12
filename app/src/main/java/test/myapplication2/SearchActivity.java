package test.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yangsu on 2017-11-12.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Button searchButton = (Button)findViewById(R.id.searchButton);
        final EditText editText = findViewById(R.id.searchInput);

        searchButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                String searchInput = editText.getText().toString();
                System.out.println("search = "+ searchInput);
                if(searchInput != null){
                    SearchApi searchApi = new SearchApi();
                    String apiUrl = searchApi.searchBlog(searchInput);
                    System.out.println("url = " +apiUrl );
                    if(apiUrl != null) {
                        Intent intent = new Intent(SearchActivity.this, WebViewActivity.class);
                        intent.putExtra("searchInput",searchInput);
                        intent.putExtra("apiUrl", apiUrl);
                        startActivity(intent);
                    }

                }
            }
        });


    }
}
