package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArticleChoiceActivity extends AppCompatActivity {


    //GridView newsArticles;

    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();
    public static ArrayList<String> articleURL = new ArrayList<String>();

    public static String messanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_choice);


        //Intent intent = getIntent();
        //String message = intent.getStringExtra(getTitle().toString());
        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(message);

        getJsonData jsonData = new getJsonData();
        jsonData.execute();


        GridView articleView = (GridView)findViewById(R.id.articleView);
        GridView newsArticles = (GridView) findViewById(R.id.articleView);
        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages);//, articleNames);
        newsArticles.setAdapter(articleAdapter);

        articleView.setAdapter(articleAdapter);
        articleView.setVisibility(View.VISIBLE);

        newsArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                //Log.i("String", articleURL.get(position));
                Intent intent = new Intent(getApplicationContext(), ChosenArticle.class);
                messanger =  articleURL.get(position);//editText.getText().toString();
                intent.putExtra( messanger, messanger);
                startActivity(intent);
                //startActivity(intent);
            }});


        Log.i("String", "--------------HERE----------");

    }
}
