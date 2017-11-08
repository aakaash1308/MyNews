package com.example.aakaashkapoor.mynews;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_article);

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
        makeEntry(sourceName);
    }

    public void makeEntry(String sourceName)//Integer sourceNumber, Integer type)
    {
        User user = new User(this);
        //Log.i("username", user.getUsername());
        Date currentTime = Calendar.getInstance().getTime();
        //Log.i("username", currentTime.toString());
        //Log.i("username", sourceName);
        Entry entry = new Entry(user.getUsername(), currentTime.toString(), 8,0);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.push().setValue(entry);

    }
}
