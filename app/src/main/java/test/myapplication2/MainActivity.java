package test.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Item> list;
    ListView listView;
    ListAdapter listAdapter;
    private static boolean deleteButtonState = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button register = (Button)findViewById(R.id.register);
        Button delete = (Button)findViewById(R.id.delete);

        final MyDBHelper db = new MyDBHelper(this);
        final SQLiteDatabase mdb = db.getWritableDatabase();

        //create table
        //TestDB(mdb, db);
        //test DB helper

        list = db.DBgetAllData(mdb);
        listView = (ListView)findViewById(R.id.list);
        listAdapter = new ListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(!deleteButtonState) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("data", list.get(position));
                    startActivity(intent);
                }
            }
        });

        delete.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View view) {

               // Toast.makeText (getApplicationContext(), new String("delete") , Toast.LENGTH_SHORT).show();

                if(!deleteButtonState){
                    deleteButtonState = true;
                    showCeckbox();
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText (getApplicationContext(), new String("delete  1") , Toast.LENGTH_SHORT).show();

                }
                else {
                    deleteButtonState = false;
                    deleteDB_and_list(db, mdb);
                    hideCheckbox();
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText (getApplicationContext(), new String("delete  0") , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void showCeckbox(){

        for(int i = 0; i < list.size(); i++){
            list.get(i).setShowCheck(true);
        }
    }

    public void hideCheckbox(){

        for(int i = 0; i< list.size(); i++){
            list.get(i).setShowCheck(false);
        }

    }
    public void deleteDB_and_list(MyDBHelper db, SQLiteDatabase mdb){

        int size = list.size();
        for (int i = 0; i < size ; )
        {
            Item item = list.get(i);
            if (item.getIsCheck())
            {
                //System.out.println("item posiition = " + i + ", item id = " + item.getDBId());
                db.DBDelete(mdb, item.getDBId());
                list.remove(i);
                size--;
            }
            else
                i++;
        }
    }

    public void onBackPressed(){
        if(deleteButtonState){
            deleteButtonState = false;
            hideCheckbox();
            listAdapter.notifyDataSetChanged();
        }
        else
            this.finish();
    }


    public void TestDB(SQLiteDatabase mdb, MyDBHelper db){

        db.DBInsert(mdb,"2", "3", "4", "5");
        db.DBInsert(mdb,"9", "8", "7", "6");

        System.out.println("INSERT");
        db.DBShow(mdb);


       db.DBDelete(mdb,11);

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
