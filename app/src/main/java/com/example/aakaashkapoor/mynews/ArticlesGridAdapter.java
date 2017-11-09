package com.example.aakaashkapoor.mynews;

/**
 * Created by yashbhartia on 9/18/17.
 * ArticlesGridAdapter
 */

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.aakaashkapoor.mynews.R.id.imageView;

public class ArticlesGridAdapter extends BaseAdapter {

    private final Context mContext;
    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();

    // 1
    public ArticlesGridAdapter(Context context, ArrayList<String> articleNames, ArrayList<String> articleImages) {
        this.mContext = context;
        this.articleNames = articleNames;
        this.articleImages = articleImages;
    }

    // 2
    @Override
    public int getCount() {
        return articleNames.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }



    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.article_names, null);
        }

        final TextView nameTextView = (TextView)convertView.findViewById(R.id.article_headline);
        nameTextView.setText(articleNames.get(position));
        //-- nameTextView.setText(articleNames.get(position));

        final ImageView articleImageView = (ImageView)convertView.findViewById(R.id.article_cover_art);
        String url = articleImages.get(position);
        Log.i("URRRRLRLLRLRLLRLRLRLL", articleNames.get(position));
        Log.i("URRRRLRLLRLRLLRLRLRLL", articleImages.get(position));

        articleImageView.setImageResource(R.drawable.cnn);
        Picasso.with(this.mContext).load(articleImages.get(position)).into(articleImageView);

        return convertView;
    }



}