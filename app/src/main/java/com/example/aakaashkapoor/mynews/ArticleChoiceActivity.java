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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ArticleChoiceActivity extends AppCompatActivity {


    //GridView newsArticles;

    public static ArrayList<String> articleAuthor= new ArrayList<String>();
    public static ArrayList<String> articleNames = new ArrayList<String>();
    public static ArrayList<String> articleBody = new ArrayList<String>();
    public static ArrayList<String> articleImages = new ArrayList<String>();

    public static String sourceName = "" , headline, body, author,url ;
    public static String kind;
    public static int sourcePosition;
    Context mcontext;

    public static GridView newsArticles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_choice);

        newsArticles = (GridView) findViewById(R.id.articleView);

        mcontext = this;
        Intent intent = getIntent();
        sourceName = intent.getStringExtra(String.valueOf(MainActivity.sourceName));
        sourcePosition = intent.getIntExtra(String.valueOf(MainActivity.sourcePosition), 0);

        makeEntry(sourceName);
        kind = intent.getStringExtra(MainActivity.kind);
        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(message);


        getJsonData jsonData = new getJsonData(mcontext);
        jsonData.execute(sourceName);

        Log.i("newssssss", "herererererererer");
        Log.i("number of articles here", String.valueOf(articleNames.size()));
        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages);//, articleNames);
        newsArticles.setAdapter(articleAdapter);
        //articleView.setAdapter(articleAdapter);
        //articleView.setVisibility(View.VISIBLE);

        //articleNames.clear();
        //articleImages.clear();
        newsArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                if(articleNames.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ChosenArticle.class);
                    intent.putExtra(sourceName, sourceName);
                    headline = articleNames.get(position);//editText.getText().toString();
                    intent.putExtra(headline, headline);
                    body = articleBody.get(position);//editText.getText().toString();
                    intent.putExtra(body, body);
                    author = articleAuthor.get(position);//editText.getText().toString();
                    intent.putExtra(author, author);
                    if(articleImages.size() > position)
                        url = articleImages.get(position);//editText.getText().toString();
                    intent.putExtra(url, url);

                    if (kind.equals("1")) {
                        makeMoreLiberal();
                    } else {
                        makeMoreConservative();
                    }
                    makeArticleEntry(sourceName,headline,position);
                    startActivity(intent);

                }
            }});




    }
    public void makeEntry(String sourceName)//Integer sourceNumber, Integer type)
    {
        Log.i("Making Entry", "--------------HERE----------");
        User user = new User(this);
        Date currentTime = Calendar.getInstance().getTime();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(user.getUsername());
        Entry entry = new Entry(currentTime.toString(), sourceName,sourcePosition+1);
        databaseReference.child(String.valueOf(user.getEventNum())).setValue(entry);

    }

    public void makeArticleEntry(String sourceName,String articleName, int position)//Integer sourceNumber, Integer type)
    {
        Log.i("Making Entry", "--------------HERE----------");
        User user = new User(this);
        Date currentTime = Calendar.getInstance().getTime();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(user.getUsername());
        Entry entry = new Entry(currentTime.toString(), sourceName,articleName, position+1,"0");
        databaseReference.child(String.valueOf(user.getEventNum())).setValue(entry);
    }

    public void makeMoreLiberal(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()+1);
        //Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

    public void makeMoreConservative(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()-1);
        //0-
        // Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

}
