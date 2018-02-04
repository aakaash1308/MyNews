package com.example.aakaashkapoor.mynews;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

public class ChosenArticle extends AppCompatActivity {

    long timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_article);

        timeElapsed = (System.currentTimeMillis());
        Intent intent = getIntent();
        String sourceName = "";
        String destinationHeadline = "";
        String destinationAuthor = "";
        String destinationBody = "";
        String destinationUrl= "";
        destinationHeadline = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.headline));
        sourceName = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.sourceName));
        destinationBody = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.body));
        destinationAuthor = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.author));
        destinationUrl = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.url));

        TextView textView = (TextView) findViewById(R.id.headline);
        textView.setText(destinationHeadline);

        textView = (TextView) findViewById(R.id.author);
        textView.setText(destinationAuthor);

        textView = (TextView) findViewById(R.id.body);
        textView.setText(destinationBody);
        textView.setMovementMethod(new ScrollingMovementMethod());

        ImageView articleImageView = (ImageView)findViewById(R.id.imageView);
        Picasso.with(this).load(destinationUrl).into(articleImageView);
        //WebView webview = new WebView(this);
        //setContentView(webview);
        //webview.loadUrl(destination);
        //makeEntry(sourceName);
    }
    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        long currentTIme = System.currentTimeMillis();
        data.putExtra("time" , (currentTIme - timeElapsed)/1000);
        // add data to Intent
        setResult(Activity.RESULT_OK, data);
        super.onBackPressed();

    }
    public String getThisData()
    {
        return "hell0";
    }

}
