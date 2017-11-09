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

    public static ArrayList<String> articleAuthor= new ArrayList<String>();
    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleBody = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();
    public static ArrayList<String> articleURL = new ArrayList<String>();

    public static String sourceName = "" , headline, body, author,url ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_choice);


        Intent intent = getIntent();
        sourceName = intent.getStringExtra(String.valueOf(MainActivity.sourceName));
        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(message);

        getJsonData jsonData = new getJsonData();
        jsonData.execute(sourceName);

        GridView articleView = (GridView)findViewById(R.id.articleView);
        GridView newsArticles = (GridView) findViewById(R.id.articleView);
        //Log.i("number of articles here: ", String.valueOf(articleNames.size()));
        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages);//, articleNames);
        newsArticles.setAdapter(articleAdapter);

        articleView.setAdapter(articleAdapter);
        articleView.setVisibility(View.VISIBLE);

        newsArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                //Log.i("String", articleURL.get(position));

                Intent intent = new Intent(getApplicationContext(), ChosenArticle.class);
                intent.putExtra(sourceName,sourceName);
                headline =  articleNames.get(position);//editText.getText().toString();
                intent.putExtra( headline, headline);
                body =  articleBody.get(position);//editText.getText().toString();
                intent.putExtra( body, body);
                author =  articleAuthor.get(position);//editText.getText().toString();
                intent.putExtra( author, author);
                url =  articleImages.get(position);//editText.getText().toString();
                intent.putExtra(url,url);

                startActivity(intent);
            }});


        Log.i("String", "--------------HERE----------");

    }
}
