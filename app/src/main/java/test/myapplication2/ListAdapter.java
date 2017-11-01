package test.myapplication2;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by yangsu.baek on 2017-11-01.
 */


public class ListAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> mData;
   // private LayoutInflater inflater;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        mData = objects;
    }

    public int getCount(){
        return mData.size();
    }

    public Item getItem(int position){
        return mData.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){

            View v = convertView;

            if(v == null){
                LayoutInflater inflater;
                inflater = LayoutInflater.from(getContext());
                v = inflater.inflate(R.layout.list_item, null);
            }

            Item item = getItem(position);

            if(item != null){
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView address = (TextView) v.findViewById(R.id.address);

                if(name != null)
                    name.setText(item.getName());

                if(address != null)
                    address.setText(item.getAddress());
            }
            return v;
    }
}
