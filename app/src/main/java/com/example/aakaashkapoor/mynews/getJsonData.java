package com.example.aakaashkapoor.mynews;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by aakaashkapoor on 9/5/17.
 */

public class getJsonData extends AsyncTask<Void,Void,Void> {
    String jsonData = "";
    JSONObject jsonObject;

    String URLs [] = {
            "https://api.newsapi.aylien.com/api/v1/stories?categories.taxonomy=iptc-subjectcode&categories.confident=true&categories.id%5B%5D=11000000&source.name%5B%5D=BBC&cluster=false&cluster.algorithm=lingo&sort_by=published_at&sort_direction=desc&cursor=*&per_page=10"
            ,"a"
    };

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Log.i("I am currently here", " yes");
            URL url = new URL(URLs[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-AYLIEN-NewsAPI-Application-ID","15c136e0" );
            httpURLConnection.addRequestProperty("X-AYLIEN-NewsAPI-Application-Key", "15c7da7bb8d73f21f0af9bf5ef6d2539");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            jsonObject = new JSONObject(responseStrBuilder.toString());
            //Log.i("news", jsonObject.toString());

//            String line = "";
//
//            while(line != null){
//                line = bufferedReader.readLine();
//                jsonData = jsonData + line;
//            }
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

        //Log.i("news", jsonObject.toString());
//        MainActivity.title.setText(jsonData);
//        //MainActivity.articleNames.add();
//
        JSONArray jsonArray = null, mediaArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("stories");

        } catch (JSONException e) {
            e.printStackTrace();
        }
//
//
        int i = 0;
        while(i < jsonArray.length() && i < 6)
        {
            try {
                JSONObject array = new JSONObject(jsonArray.get(i).toString());
                ArticleChoiceActivity.articleNames.add(array.get("title").toString());
                ArticleChoiceActivity.articleBody.add(array.get("body").toString());

                JSONObject author = (JSONObject) array.get("author");
                ArticleChoiceActivity.articleAuthor.add(author.get("name").toString());

                JSONArray media = (JSONArray) array.get("media");
                JSONObject medias = (JSONObject) media.get(0);

                ArticleChoiceActivity.articleImages.add((String) medias.get("url"));

//                Log.i("article names: ", array.get("title").toString());
//                Log.i("news", (String) medias.get("url"));
          } catch (JSONException e) {
                e.printStackTrace();
            }
//
            i++;
        }
        Log.i("article names: ", "GOT ALL 6 BABY!!!");
    }
}
