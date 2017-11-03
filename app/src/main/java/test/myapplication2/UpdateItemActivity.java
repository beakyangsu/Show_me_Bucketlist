package test.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yangsu.baek on 2017-11-03.
 */

public class UpdateItemActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        Intent receive = getIntent();
        Item item =  receive.getParcelableExtra("data");
    }

}
