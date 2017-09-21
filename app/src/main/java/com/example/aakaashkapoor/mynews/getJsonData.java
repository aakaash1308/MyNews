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

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://newsapi.org/v1/articles?source=the-new-york-times&sortBy=top&apiKey=eaef4430cc804415b2d2cba50782913b");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            jsonObject = new JSONObject(responseStrBuilder.toString());

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
        
        MainActivity.title.setText(jsonData);
        //MainActivity.articleNames.add();
        
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("articles");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        int i = 0;
        while(i < jsonArray.length())
        {
            try {
                JSONObject array = new JSONObject(jsonArray.get(i).toString());
                //Log.i("json", String.valueOf(array));
                //MainActivity.title.append(array.get("title").toString());
                //MainActivity.title.append("\n\n");


                ArticleChoiceActivity.articleImages.add(array.get("urlToImage").toString());
                ArticleChoiceActivity.articleNames.add(array.get("title").toString());
                ArticleChoiceActivity.articleURL.add(array.get("url").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            i++;
        }
    }
}
