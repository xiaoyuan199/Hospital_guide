package com.zone.hospital.model.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zone.hospital.R;
import com.zone.hospital.model.bean.Keshi;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

    public class HospitalAdapter extends ArrayAdapter<Keshi>{

    private int resourceid;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        Keshi  keshi=getItem(position);

        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        }else{
            view=convertView;
        }

        TextView textView= (TextView) view.findViewById(R.id.text_item);
        textView.setText(keshi.getName()+"当前人数为："+keshi.getNumber()+"人");


        return view;
    }

    public HospitalAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Keshi> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }
}
