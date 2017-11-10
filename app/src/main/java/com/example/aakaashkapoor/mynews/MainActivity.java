package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // variable declaration
    GridView newsChannels;
    GridView newsArticles;
    public static TextView title;
    static String sourceName;
    static int sourcePosition;
    static String kind;
    Context mcontext;
    //public static TextView articles;


    public static final String MainMessage = "Cardi B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.textView2) ;

        User user = new User(this); // for checking the user
        mcontext = this;
        if(user.checkFirstTime())
            createUserInDatabase(user.username);


        // variable instantiation
        final GridView gridView = (GridView) findViewById(R.id.channelsView) ;
        newsChannels = (GridView) findViewById(R.id.channelsView);
        ChannelsGridAdapter gridAdapter = new ChannelsGridAdapter(this);
        newsChannels.setAdapter(gridAdapter);


        newsChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image, int position, long id) {

                ImageView myimage = (ImageView) image.findViewById(R.id.imageview);
                Intent intent = new Intent(getApplicationContext(), ArticleChoiceActivity.class);
                sourceName = myimage.getTag().toString();
                sourcePosition = position;
                kind = String.valueOf(sourceName.charAt(sourceName.length()-1));
                if(kind.equals("1")){
                    makeMoreLiberal();
                }
                else{
                    makeMoreConservative();
                }
                sourceName = sourceName.substring(0,sourceName.length()-1);
                Toast.makeText(getApplicationContext(),sourceName , Toast.LENGTH_SHORT).show();

                intent.putExtra(sourceName,sourceName);
                intent.putExtra(kind, kind);
                intent.putExtra(String.valueOf(sourcePosition), sourcePosition);
                //finish();
                startActivity(intent);
            }});

    }
    public void makeMoreLiberal(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()+5);
        Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }
    public void makeMoreConservative(){
        User user = new User(mcontext);
        user.setHowLiberal(user.gethowLiberal()-5);
        Log.i("howLiberal", String.valueOf(user.gethowLiberal()));
    }

    public void createUserInDatabase(String username) {
        User user = new User(username);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.push().setValue(user);
    }


}
