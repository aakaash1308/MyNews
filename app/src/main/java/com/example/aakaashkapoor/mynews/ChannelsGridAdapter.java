package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by aakaashkapoor on 8/28/17.
 */

public class ChannelsGridAdapter extends BaseAdapter {


    private final TypedArray conservative_images;
    private final TypedArray liberal_images;
    private final TypedArray conservative_names;
    private final TypedArray liberal_names;

    Context context;
    View view;
    LayoutInflater layoutInflater;
    List<Integer> display = new ArrayList<>();
    public ChannelsGridAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 11; i++) {
            display.add(i);
        }
        //shuffling the array for images
        Collections.shuffle(display);

        //Log.i("testetetetetetetet",display.toString());

        conservative_images = context.getResources().obtainTypedArray(R.array.conservative_images);
        liberal_images = context.getResources().obtainTypedArray(R.array.liberal_images);
        conservative_names = context.getResources().obtainTypedArray(R.array.conservative_names);
        liberal_names = context.getResources().obtainTypedArray(R.array.liberal_names);
        Log.i("asdas",liberal_names.toString());
    }

    @Override
    public int getCount() {
        return 11;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public boolean isLiberal(){

        Random r = new Random();
        int likelihood = r.nextInt(100) + 1;
        User user = new User(this.context);

        if(likelihood > user.gethowLiberal())
            return false;

        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int currentImage = 0;
        String currentName = "";
        if(isLiberal()){
            currentImage = liberal_images.getResourceId(display.get(position),-1);
            currentName = liberal_names.getString(display.get(position));
            currentName = currentName + "1";
            //1 is for liberal
        }
        else{

            currentImage = conservative_images.getResourceId(display.get(position),-1);
            currentName = conservative_names.getString(display.get(position));
            currentName = currentName + "0";
        }

        if(convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_channel, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            imageView.setImageResource(currentImage);
            imageView.setTag(currentName);
        }
        else{
            view = (View) convertView;
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            imageView.setImageResource(currentImage);
            imageView.setTag(currentName);
        }

        return view;
    }
}
