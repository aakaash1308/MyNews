package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public static String sourceName = "" , headline, body, author,url, articleTimestamp, sourceTimestamp, sourcePosition;
    public static long sourceTimespent;
    public static String kind;
    public  int  articlePosition;
    Context mcontext;

    public int entryNumber = 0;
    public int SourceHasBeenClicked = 0;
    public int inBackground = 0;

    public static GridView newsArticles;
    public static final int OPEN_NEW_ACTIVITY = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_choice);
        Date cDate = new Date();
        sourceTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX").format(cDate);

        User user = new User(this);

        sourceTimespent = System.currentTimeMillis();
        newsArticles = (GridView) findViewById(R.id.articleView);

        mcontext = this;
        Intent intent = getIntent();
        sourceName = intent.getStringExtra(String.valueOf(MainActivity.sourceName));
        sourcePosition = intent.getStringExtra("sourcePosition");
        kind = intent.getStringExtra(MainActivity.kind);

        getJsonData jsonData = new getJsonData(mcontext, sourceName, sourcePosition);
        jsonData.execute(sourceName);

        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages, sourceName, sourcePosition, articleAuthor);//, articleNames);
        newsArticles.setAdapter(articleAdapter);

        newsArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                SourceHasBeenClicked = 1;
                if(articleNames.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ChosenArticle.class);

                    headline = articleNames.get(position);//editText.getText().toString();
                    body = articleBody.get(position);//editText.getText().toString();
                    author = articleAuthor.get(position);//editText.getText().toString();
                    articlePosition = position;
                    Date cDate = new Date();
                    articleTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX").format(cDate);
                    if(articleImages.size() > position)
                        url = articleImages.get(position);

                    if (kind.equals("1")) {
                        makeMoreLiberal();
                    }
                    else {
                        makeMoreConservative();
                    }

                    intent.putExtra("sourceName", fixName(sourceName));
                    intent.putExtra("headline", headline);
                    intent.putExtra("author", author);
                    intent.putExtra("url", url);
                    intent.putExtra("body", body);
                    final long currentTIme = System.currentTimeMillis();

                    intent.putExtra("STS", Long.toString(currentTIme - sourceTimespent));
                    intent.putExtra("articleTimestamp", articleTimestamp);
                    intent.putExtra("SP",String.valueOf(sourcePosition));
                    intent.putExtra("articlePosition", String.valueOf(articlePosition));
                    intent.putExtra("sourceTimestamp", sourceTimestamp);

                    inBackground = -1;
                    startActivityForResult(intent, OPEN_NEW_ACTIVITY);

                }
            }});
    }

    public String createTime(long time)
    {
        long min = time/60;
        long seconds = time%60;
        String toMake = "";
        if(min < 10)
            toMake = toMake + "0" + min;
        else
            toMake = toMake + min;

        toMake += ":";
        if(seconds < 10)
            toMake = toMake + "0" + seconds;
        else
            toMake = toMake + seconds;
        return toMake;
    }



    public void makeMoreLiberal(){
        User user = new User(mcontext);
        user.setMoreLiberal();
        //Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

    public void makeMoreConservative(){
        User user = new User(mcontext);
        user.setMoreConservative();
        //0-
        // Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

    @Override
    public void onBackPressed() {
        if(SourceHasBeenClicked == 0){

            makeEntry(fixName(sourceName));

        }
        super.onBackPressed();
    }

    public String fixName(String sourceName){
        for( int i = 0; i < sourceName.length(); i++) {
            if (sourceName.charAt(i) == '%') {
                sourceName = sourceName.substring(0, i) + ' ' + sourceName.substring(i + 3);
            }
        }


        return sourceName;
    }

    public void makeEntry(final String sourceName)//Integer sourceNumber, Integer type)
    {
        User user = new User(this);
        final int bias = user.gethowLiberal();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());
        DatabaseReference mdatabase = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());

        final long currentTIme = System.currentTimeMillis();
        final Date resultdate = new Date(currentTIme - sourceTimespent);
        final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

//
        //gets the entry number
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                entryNumber = (int) snapshot.getChildrenCount() + 1;
                databaseReference.child(String.valueOf(entryNumber)).child("Source Name").setValue(sourceName);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Position").setValue(sourcePosition+1);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Timestamp").setValue(sourceTimestamp);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Timespent").setValue(currentTIme - sourceTimespent);
                databaseReference.child(String.valueOf(entryNumber)).child("User Bias").setValue(bias);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    protected void onStop() {
        super.onStop();

        inBackground += 1;

    }
    @Override
    protected void onResume() {
        super.onResume();
        //do something

        if(inBackground == 1) {
            inBackground = 0;
            Intent intent = new Intent(getApplicationContext(), LogInPage.class);

            finish();
            startActivity(intent);
        }
    }
}
