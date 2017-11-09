package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ArticleChoiceActivity extends AppCompatActivity {


    //GridView newsArticles;

    public static ArrayList<String> articleAuthor= new ArrayList<String>();
    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleBody = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();

    public static String sourceName = "" , headline, body, author,url ;
    public static String kind;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_choice);

        mcontext = this;
        Intent intent = getIntent();
        sourceName = intent.getStringExtra(String.valueOf(MainActivity.sourceName));
        kind = intent.getStringExtra(MainActivity.kind);
        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(message);

        getJsonData jsonData = new getJsonData();
        try {
            jsonData.execute(sourceName).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final GridView articleView = (GridView)findViewById(R.id.articleView);
        GridView newsArticles = (GridView) findViewById(R.id.articleView);
        //Log.i("number of articles here: ", String.valueOf(articleNames.size()));
        final ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages);//, articleNames);
        newsArticles.setAdapter(articleAdapter);
        articleNames.clear();
        articleImages.clear();
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

                if(kind.equals("1")){
                    makeMoreLiberal();
                }
                else{
                    makeMoreConservative();
                }

                startActivity(intent);
            }});


        Log.i("String", "--------------HERE----------");

    }

    public void makeMoreLiberal(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()+1);
        Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }
    public void makeMoreConservative(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()-1);
        Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

}
