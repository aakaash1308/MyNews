package com.example.aakaashkapoor.mynews;

/**
 * Created by yashbhartia on 9/18/17.
 * ArticlesGridAdapter
 */

import android.content.Context;

import android.content.res.TypedArray;
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
    private final String sourceName;
    private final String sourcePosition;
    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();
    public static ArrayList<String> articleAuthors = new ArrayList<String>();


    private final TypedArray conservative_images;
    private final TypedArray liberal_images;
    private final TypedArray conservative_names;
    private final TypedArray liberal_names;



    // 1
    public ArticlesGridAdapter(Context context, ArrayList<String> articleNames, ArrayList<String> articleImages, String sourceName, String sourcePosition,ArrayList<String> articleAuthors ) {
        this.mContext = context;
        this.articleNames = articleNames;
        this.articleAuthors = articleAuthors;
        this.articleImages = articleImages;
        this.sourceName = sourceName;
        this.sourcePosition = sourcePosition;

        conservative_images = context.getResources().obtainTypedArray(R.array.conservative_images);
        liberal_images = context.getResources().obtainTypedArray(R.array.liberal_images);
        conservative_names = context.getResources().obtainTypedArray(R.array.conservative_names);
        liberal_names = context.getResources().obtainTypedArray(R.array.liberal_names);
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
        final TextView authorTextView = (TextView)convertView.findViewById(R.id.article_author);
        authorTextView.setText(articleAuthors.get(position));
        //-- nameTextView.setText(articleNames.get(position));

        final ImageView articleImageView = (ImageView)convertView.findViewById(R.id.article_cover_art);

        Log.i("TEST sourceName", sourceName );
        int index = Integer.parseInt(sourcePosition);

        for(int i = 0; i < 12; i++){
            Log.i("TEST sourceName", sourceName );
            if(liberal_names.getString(i).equals(sourceName)) {
                articleImageView.setImageResource(liberal_images.getResourceId(i, -1));
                break;
            }
            Log.i("TEST conservative", conservative_names.getString(i) );
            if(conservative_names.getString(i).equals(sourceName)) {
                articleImageView.setImageResource(conservative_images.getResourceId(i, -1));
                break;
            }
        }


        if(articleImages.size() > position) // checking for any errors
            Picasso.with(this.mContext).load(articleImages.get(position)).into(articleImageView);

        return convertView;
    }



}