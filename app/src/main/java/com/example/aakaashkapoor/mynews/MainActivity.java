package com.example.aakaashkapoor.mynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // variable declaration
    GridView newsChannels;

    // Declaration of the images and thier names
    String[] channelNames = {
            "BBC",
            "CNBC",
            "Conservative Tribune",
            "CNN",
            "Fox News",
            "MSNBC",
            "New York Times"
    };

    int[] channelImages = {
            R.drawable.bbc,
            R.drawable.cnbc,
            R.drawable.ct,
            R.drawable.cnn,
            R.drawable.foxnews,
            R.drawable.msnbc,
            R.drawable.newyorktimes
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User(this); // for checking the user

        if(user.checkFirstTime())
            createUserInDatabase(user.username);

        // variable instantiation
        newsChannels = (GridView) findViewById(R.id.channelsView);
        ChannelsGridAdapter gridAdapter = new ChannelsGridAdapter(this, channelNames, channelImages);

        newsChannels.setAdapter(gridAdapter);

        newsChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View image,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), channelNames[position],
                        Toast.LENGTH_SHORT).show();
            }});

    }

    public void createUserInDatabase(String username) {
        User user = new User(username);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.push().setValue(user);
    }
}
