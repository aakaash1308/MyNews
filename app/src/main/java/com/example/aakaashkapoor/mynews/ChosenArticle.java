package com.example.aakaashkapoor.mynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChosenArticle extends AppCompatActivity {

    long timeElapsed;
    public int entryNumber = 0;
    public String sourceName = "";
    public String destinationHeadline = "";
    public String destinationAuthor = "";
    public String destinationBody = "";
    public String destinationUrl= "";
    public String articlePosition= "";
    public String articleTS= "";
    public String sourcePosition="";
    public String sourceTimestamp = "";
    public String sourceTimespent;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_article);



        timeElapsed = System.currentTimeMillis();
        Intent intent = getIntent();

        sourceTimespent = intent.getStringExtra("STS");
        destinationHeadline = intent.getStringExtra("headline");
        sourceName = intent.getStringExtra("sourceName");
        destinationBody = intent.getStringExtra("body");
        destinationAuthor = intent.getStringExtra("author");
        destinationUrl =intent.getStringExtra("url");
        articlePosition = intent.getStringExtra("articlePosition");
        articleTS = intent.getStringExtra("articleTimestamp");
        sourceTimestamp = intent.getStringExtra("sourceTimestamp");
        sourcePosition = intent.getStringExtra("SP");

        TextView textView = (TextView) findViewById(R.id.headline);
        textView.setText(destinationHeadline);

        textView = (TextView) findViewById(R.id.author);
        textView.setText(destinationAuthor);

        textView = (TextView) findViewById(R.id.body);

        textView.setText(destinationBody);
        textView.setMovementMethod(new ScrollingMovementMethod());

        ImageView articleImageView = (ImageView)findViewById(R.id.imageView);
        Picasso.with(this).load(destinationUrl).into(articleImageView);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        long currentTIme = System.currentTimeMillis();
        intent.putExtra("time" , (currentTIme - timeElapsed)/1000);
        // add data to Intent
        setResult(Activity.RESULT_OK, intent);
        makeEntry(sourceName, (currentTIme - timeElapsed) ,sourceTimespent);
        super.onBackPressed();

    }

    public void makeEntry(final String sourceName, final long timeSpent, final String ST){
        User user = new User(this);
        final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX").format(cDate);

        DatabaseReference mdatabase = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());
        //gets the entry number
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                entryNumber = (int) snapshot.getChildrenCount() + 1;
                databaseReference.child(String.valueOf(entryNumber)).child("Article Headline").setValue(destinationHeadline);
                databaseReference.child(String.valueOf(entryNumber)).child("Article Position").setValue(articlePosition);
                databaseReference.child(String.valueOf(entryNumber)).child("Article Timestamp").setValue(articleTS);
                databaseReference.child(String.valueOf(entryNumber)).child("Article Timespent").setValue(timeSpent);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Name").setValue(sourceName);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Position").setValue(sourcePosition);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Timestamp").setValue(sourceTimestamp);
                databaseReference.child(String.valueOf(entryNumber)).child("Source Timespent").setValue(ST);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
