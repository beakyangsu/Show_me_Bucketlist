package test.myapplication2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import test.myapplication2.MyDBHelper;

public class MainActivity extends AppCompatActivity {

    public static String[] items = {"star A", "Star B"};
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper db = new MyDBHelper(this);
        SQLiteDatabase mdb = db.getWritableDatabase();
        //create table

        db.DBInsert(mdb,"2", "3", "4", "5");
        db.DBInsert(mdb,"9", "8", "7", "6");
       ArrayList list = db.DBShow(mdb);
        System.out.println("INSERT");
        System.out.println(list);

       db.DBDelete(mdb,10);

        System.out.println("DELETE");
        list = db.DBShow(mdb);
        System.out.println(list);

        db.DBInsert(mdb,"2", "3", "4", "5");
        list = db.DBShow(mdb);
        System.out.println("INSERT");
        System.out.println(list);

        db.DBUpgrade(mdb,11, "name", "10");
        list = db.DBShow(mdb);
        System.out.println("UPGRADE");
        System.out.println(list);

        /*Cursor c = db.DBSelect(mdb, 2);
        //int id = c.getInt(0);
        String name = c. getString(1);
        String address = c. getString(2);
        String latitude = c.getString(3);
        String longitude = c.getString(4);
        System.out.println("SELECT");
        System.out.println( "|" + name + "|" + address + "|" + latitude + "|" + longitude);
*/


       // list.setAdapter(new IconAdapter(this));
    }
}
