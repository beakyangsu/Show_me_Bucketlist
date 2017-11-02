package test.myapplication2;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by yangsu.baek on 2017-11-01.
 */


public class ListAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> mData;
    ArrayList<Boolean> positionArray;
   // private LayoutInflater inflater;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        mData = objects;

        positionArray = new ArrayList<Boolean>(mData.size());
        for(int i = 0; i< mData.size(); i++){
            positionArray.add(false);
        }
    }

    public int getCount(){
        return mData.size();
    }

    public Item getItem(int position){
        return mData.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent){

            View v = convertView;

            if(v == null){
                LayoutInflater inflater;
                inflater = LayoutInflater.from(getContext());
                v = inflater.inflate(R.layout.list_item, null);
            }


            final Item item = getItem(position);

            if(item != null){
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView address = (TextView) v.findViewById(R.id.address);
                CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkbox);
                checkBox.setOnCheckedChangeListener(null);

                if(!item.getShowCheck()) {
                    checkBox.setVisibility(View.GONE);
                }
                else {
                    checkBox.setVisibility(View.VISIBLE);
                }


                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            positionArray.set(position, true);
                            item.setIsCheck(true);
                        }else{
                            positionArray.set(position, false);
                            item.setIsCheck(false);
                        }
                    }
                });


                if(name != null)
                    name.setText(item.getName());

                if(address != null)
                    address.setText(item.getAddress());
                }

            return v;
    }
}
