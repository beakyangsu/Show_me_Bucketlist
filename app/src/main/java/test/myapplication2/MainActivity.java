package test.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper db = new MyDBHelper(this);
        SQLiteDatabase mdb = db.getWritableDatabase();
        //create table
        //TestDB(mdb, db);
        //test DB helper

        final ArrayList<Item> list = db.DBgetAllData(mdb);
        ListView listView = (ListView)findViewById(R.id.list);
        ListAdapter listAdapter = new ListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("data",list.get(position));

                startActivity(intent);
            }
        });

    }

    public void TestDB(SQLiteDatabase mdb, MyDBHelper db){

        db.DBInsert(mdb,"2", "3", "4", "5");
        db.DBInsert(mdb,"9", "8", "7", "6");

        System.out.println("INSERT");
        db.DBShow(mdb);


       db.DBDelete(mdb,10);

        System.out.println("DELETE");
        db.DBShow(mdb);


        db.DBInsert(mdb,"2", "3", "4", "5");

        System.out.println("INSERT");
        db.DBShow(mdb);


        db.DBUpgrade(mdb,2, "name", "10");

        System.out.println("UPGRADE");
        db.DBShow(mdb);


        Cursor c = db.DBSelect(mdb, 2);
        System.out.println("SELECT");
        db.DBShow(mdb, c);

    }

}
