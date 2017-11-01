package test.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
        int position = receive.getIntExtra("position", 0);
      // Item item =  receive.getParcelableExtra("data");
        //ArrayList<Item> list =  receive.getParcelableExtra("data");
        //Item item = list.get(position);
        //setLayout(item);

    }

    protected void setLayout(Item item){
        if(item != null){
            TextView name = (TextView) findViewById(R.id.name);
            TextView address = (TextView) findViewById(R.id.address);

            if(name != null)
                name.setText(item.getName());

            if(address != null)
                address.setText(item.getAddress());
        }
    }
}
