package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.text.TextUtils;
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
    public String[] Channels; // will store the saved articles

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

        Channels = getChannelsFromMemory(context);

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

    //
    // function that will populate the Channels array
    //
    public String[] getChannelsFromMemory(Context mContext)
    {
        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        String allChannels = prefs.getString("allChannels", "0 c,1 l,2 c,3 l,4 c,5 l,6 c,7 l,8 c,9 l,10 c"); // username will be set to the second argument if it doesn't exist

        Channels = TextUtils.split(allChannels, ","); // splits the channels into an array



        return Channels;
    }

    //
    // function that will say if its a new day
    //
    public boolean checkNewDay()
    {
        return false;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int currentImage = 0;
        String currentName = "";

        if(checkNewDay()) // creates a random channels array if its a new day
        {
            if (isLiberal()) {
                currentImage = liberal_images.getResourceId(display.get(position), -1);
                currentName = liberal_names.getString(display.get(position));
                currentName = currentName + "1";
                //1 is for liberal
            } else {

                currentImage = conservative_images.getResourceId(display.get(position), -1);
                currentName = conservative_names.getString(display.get(position));
                currentName = currentName + "0";
            }
        }
        else // creates an array based on array that was saved
        {
            String[] channel = Channels[position].split(" ");
            Log.i("getView", Channels[position]);
            int channelNumber = Integer.parseInt(channel[0]); // gets which channel

            if (channel[1].equals("l")) {
                currentImage = liberal_images.getResourceId(channelNumber, -1);
                currentName = liberal_names.getString(channelNumber);
                currentName = currentName + "1";
                //1 is for libera
            }
            else {
                currentImage = conservative_images.getResourceId(channelNumber, -1);
                currentName = conservative_names.getString(channelNumber);
                currentName = currentName + "0";
            }
        }

        if (convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_channel, null);
        } else {
            view = (View) convertView;
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(currentImage);
        imageView.setTag(currentName);
        return view;
    }

}
