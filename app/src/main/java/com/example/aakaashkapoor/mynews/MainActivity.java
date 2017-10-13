package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] channelNames = {"BBC",
            "CNBC",
            "Conservative Tribune",
            "CNN",
            "Fox News",
            "MSNBC",
            "New York Times",
            "BBC",
            "CNBC",
            "Conservative Tribune",
            "CNN",
            "Fox News"
    };

    int[] channelImages = {
            R.drawable.bbc,
            R.drawable.cnbc,
            R.drawable.ct,
            R.drawable.cnn,
            R.drawable.foxnews,
            R.drawable.msnbc,
            R.drawable.newyorktimes,
            R.drawable.bbc,
            R.drawable.cnbc,
            R.drawable.ct,
            R.drawable.cnn,
            R.drawable.foxnews
    };

    // variable declaration
    GridView newsChannels;
    GridView newsArticles;
    public static TextView title;
    //public static TextView articles;


    public static final String MainMessage = "Cardi B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.textView2) ;

        User user = new User(this); // for checking the user

        if(user.checkFirstTime())
            createUserInDatabase(user.username);


        // variable instantiation
        final GridView gridView = (GridView) findViewById(R.id.channelsView) ;
        newsChannels = (GridView) findViewById(R.id.channelsView);
        ChannelsGridAdapter gridAdapter = new ChannelsGridAdapter(this, channelNames, channelImages);
        newsChannels.setAdapter(gridAdapter);

//These ARE REDUNDANT BUT FOR SOME REASON THE CODE DOESNT RUN WITHOUT THEM
//        ArrayList<String> articleNames = new ArrayList<String>();
//        ArrayList<String> articleImages = new ArrayList<String>();
//        final GridView articleView = (GridView)findViewById(R.id.articleView);
//        newsArticles = (GridView) findViewById(R.id.articleView);
//        ArticlesGridAdapter articleAdapter = new ArticlesGridAdapter(this, articleNames, articleImages);//, articleNames);
//        newsArticles.setAdapter(articleAdapter);
//        articleView.setAdapter(articleAdapter);
//        articleView.setVisibility(View.INVISIBLE);
//TILL HERE

        newsChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                Toast.makeText(getApplicationContext(), channelNames[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ArticleChoiceActivity.class);

                startActivity(intent);
            }});

    }

    public void createUserInDatabase(String username) {
        User user = new User(username);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.push().setValue(user);
    }


}
