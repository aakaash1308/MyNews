package com.example.aakaashkapoor.mynews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.aakaashkapoor.mynews.ArticlesGridAdapter.articleImages;
import static com.example.aakaashkapoor.mynews.ArticlesGridAdapter.articleNames;

/**
 * Created by aakaashkapoor on 9/5/17.
 */

public class getJsonData extends AsyncTask<String,Void,Void> {
    String jsonData = "";
    JSONObject jsonObject;
    ProgressDialog pd;
    Context mContext;
    String sourceName;
    String sourcePosition;
    // getting the context
    public getJsonData (Context context, String sourceName, String sourcePosition){
        mContext = context;
        this.sourceName = sourceName;
        this.sourcePosition = sourcePosition;
    }

    @Override
    protected Void doInBackground(String... voids) {
        try {


            Log.i("I am currently here", voids[0]);

            URL url = new URL("https://api.newsapi.aylien.com/api/v1/stories?categories.taxonomy=iptc-subjectcode&categories.confident=true&categories.id%5B%5D=11000000&source.name%5B%5D="+voids[0]+"&cluster=false&cluster.algorithm=lingo&sort_by=published_at&sort_direction=desc&cursor=*&per_page=10"
            );
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-AYLIEN-NewsAPI-Application-ID","65d8679d" );
            httpURLConnection.addRequestProperty("X-AYLIEN-NewsAPI-Application-Key", "5e2739af5aa4d8d25e3b16ad75092fe8");
            InputStream inputStream = httpURLConnection.getInputStream();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            jsonObject = new JSONObject(responseStrBuilder.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        JSONArray jsonArray = null, mediaArray = null;
        try {
            //Log.i("news", jsonObject.toString());
            jsonArray = jsonObject.getJSONArray("stories");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        int i = 0;
        ArrayList<String> articleAuthor= new ArrayList<String>();
        ArrayList<String> articleNames = new ArrayList<String>();
        ArrayList<String> articleBody = new ArrayList<String>();
        ArrayList<String> articleImages = new ArrayList<String>();
        while(i < jsonArray.length() && i < 6)
        {
            try {
                JSONObject array = new JSONObject(jsonArray.get(i).toString());
                articleNames.add(array.get("title").toString());
                articleBody.add(array.get("body").toString());

                JSONObject author = (JSONObject) array.get("author");
                articleAuthor.add(author.get("name").toString());

                JSONArray media = (JSONArray) array.get("media");
                JSONObject medias = (JSONObject) media.get(0);

                articleImages.add((String) medias.get("url"));

          } catch (JSONException e) {
                e.printStackTrace();
            }
//
            i++;
        }

        ArticleChoiceActivity.articleBody = articleBody;
        ArticleChoiceActivity.articleNames = articleNames;
        ArticleChoiceActivity.articleAuthor = articleAuthor;
        ArticleChoiceActivity.articleImages = articleImages;

        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(mContext, articleNames, articleImages, sourceName, sourcePosition, articleAuthor);//, articleNames);
        ArticleChoiceActivity.newsArticles.setAdapter(articleAdapter);


        //Log.i("article names: ", ArticleChoiceActivity.articleNames.get(0));




    }
}
