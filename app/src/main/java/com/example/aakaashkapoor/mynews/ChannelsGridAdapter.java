package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by aakaashkapoor on 8/28/17.
 */

public class ChannelsGridAdapter extends BaseAdapter {

    private final int [] images;
    private final String [] values;
    Context context;
    View view;
    LayoutInflater layoutInflater;


    public ChannelsGridAdapter(Context context, String[] values ,int[] images) {
        this.images = images;
        this.values = values;
        this.context = context;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {

            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_channel, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            imageView.setImageResource(images[position]);
        }

        else{
            view = (View) convertView;
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            imageView.setImageResource(images[position]);
        }

        return view;
    }
}
