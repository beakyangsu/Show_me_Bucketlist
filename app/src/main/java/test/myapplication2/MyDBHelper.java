package test.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

/**
 * Created by yangsu on 2017-10-31.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBList.db";
    private static final String DB_TABLE = "DBList";

    MyDBHelper(Context context){
        super(context, DB_NAME, null, 2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, latitude TEXT, longitude TEXT, url TEXT);");

    }

    boolean DBInsert(SQLiteDatabase db, String name, String address, String latitude, String longitude, String url){

        ContentValues value = new ContentValues();

        value.put("name", name);
        value.put("address", address);
        value.put("latitude", latitude);
        value.put("longitude", longitude);
        value.put("url", url);


        return db.insert(DB_TABLE, null, value) > 0;
    }

    boolean DBDelete(SQLiteDatabase db, int id ){
        String _id = String.valueOf(id);
        return 1 == db.delete(DB_TABLE, "_id=?", new String[]{_id});
    }
    void DBUpgrade(SQLiteDatabase db, int id, String key, String data){

        ContentValues value = new ContentValues();
        String _id = String.valueOf(id);

        value.put(key, data);
        db.execSQL("UPDATE " + DB_TABLE + " SET " + key + " = " + data + " WHERE _id = " + _id + ";");
    }

    Cursor DBSelect(SQLiteDatabase db, int id){
        String _id = String.valueOf(id);
        return db.query(DB_TABLE, null, "_id=?", new String[]{_id}, null, null, null);
    }


    ArrayList DBgetAllData(SQLiteDatabase db){

        Cursor c = db.query(DB_TABLE, null, null, null, null, null, null);

       // ArrayList<String> list = new ArrayList<>();
        ArrayList<Item> list = new ArrayList<>();
        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c. getString(1);
            String address = c. getString(2);
            String latitude = c.getString(3);
            String longitude = c.getString(4);
            String url = c.getString(5);
            Item item = new Item(id, name, address, latitude, longitude, url);
            list.add(item);

        }
        c.close();
        return list;
    }

    void DBShow(SQLiteDatabase db){

        Cursor c = db.query(DB_TABLE, null, null, null, null, null, null);

        ArrayList<String> list = new ArrayList<>();

        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c. getString(1);
            String address = c. getString(2);
            String latitude = c.getString(3);
            String longitude = c.getString(4);
            String url = c.getString(5);
            list.add(id + "|" + name + "|" + address + "|" + latitude + "|" + longitude +  "|" + url+ "\n");
        }
        System.out.println(list);
        c.close();
    }

    void DBShow(SQLiteDatabase db, Cursor c){

        ArrayList<String> list = new ArrayList<>();

        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c. getString(1);
            String address = c. getString(2);
            String latitude = c.getString(3);
            String longitude = c.getString(4);
            String url = c.getString(5);
            list.add(id + "|" + name + "|" + address + "|" + latitude + "|" + longitude +  "|" + url+ "\n");
        }
        System.out.println(list);
        c.close();
    }

}
