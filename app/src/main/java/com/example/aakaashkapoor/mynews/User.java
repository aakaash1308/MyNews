package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by aakaashkapoor on 8/28/17.
 * This class holds the main username for the user
 * It consists of two parts:
 * 1) That is going to create a user in the database if its not created
 * 2) That will just get the username if the user is already there
 */

public class User{

    String username; // stores the one time username to differentiate the user from the others
    private int firstTime = 0; // checks if this is the first time the app is opened

    public User(String username) {
        this.username = username;
    }

    public User(Context mContext) {

        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        String username = prefs.getString("username1", "this is the first time."); // username will be set to the second argument if it doesn't exist

        if(username.equals("this is the first time.")) // check for the first time and create a random username
        {
            username = Long.toHexString(Double.doubleToLongBits(Math.random()));
            firstTime = 1; // for telling the app that this is the first time

            // saving in the phone
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username1", username);
            editor.commit();
        }

        this.username = username; // setting the correct username
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkFirstTime() {
        return (firstTime == 1);
    }
}
