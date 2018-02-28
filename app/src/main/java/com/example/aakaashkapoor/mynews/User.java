package com.example.aakaashkapoor.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aakaashkapoor on 8/28/17.
 * This class holds the main username for the user
 * It consists of two parts:
 * 1) That is going to create a user in the database if its not created
 * 2) That will just get the username if the user is already there
 */

public class User{

    String username; // stores the one time username to differentiate the user from the others
    int howLiberal;
    private int firstTime = 0; // checks if this is the first time the app is opened
    Context mContext;
    int eventNum;
    int type; // 0-BOM, 1-BWM, 2-NBX

    public User(String username) {
        this.username = username;
    }

    public User(Context mContext) {
        this.mContext = mContext;
        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        String username = prefs.getString("username1", "this is the first time."); // username will be set to the second argument if it doesn't exist
        this.howLiberal = prefs.getInt("howLiberal", 50); // username will be set to the second argument if it doesn't exist
        this.eventNum = prefs.getInt("eventNum", 0); // username will be set to the second argument if it doesn't exist

//        if(username.equals("this is the first time.")) // check for the first time and create a random username
//        {
//            SharedPreferences.Editor editor = prefs.edit();
//
//            username = Long.toHexString(Double.doubleToLongBits(Math.random()));
//            firstTime = 1; // for telling the app that this is the first time
//
//            // saving in the phone
//            editor.putString("username1", username);
//            editor.commit();
//        }

        this.username = username; // setting the correct username
    }

    public String getUsername() {
        return username;
    }
    public int getEventNum(){
        setEventNum();
        return eventNum - 1 ;
    }
    public void setUsername(String username) {

        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("username1", username);
        this.username = username;
        editor.commit();
    }
    public void setEventNum(){
        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("eventNum", eventNum + 1);
        this.eventNum = eventNum + 1;
        editor.commit();
    }


    public void saveDateToMemory(Context mContext) // function that will save the Date today
    {

        // gets the date in the appropriate format
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate =  String.valueOf(mdformat.format(calendar.getTime()));

        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();
        Log.i("Savs channels to memory", currentDate);
        editor.putString("date1", currentDate);
        editor.commit();
    }
    public boolean checkNewDay(Context mContext) // checks if this a new day for the user
    {
        // gets the date in the appropriate format
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate =  String.valueOf(mdformat.format(calendar.getTime()));

        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        String dateInMemory = prefs.getString("date1", "2018/02/05"); // username will be set to the second argument if it doesn't exist

        if(currentDate.equals(dateInMemory)) // to check if its a new day or not
            return false;

        return true;
    }
    public void setMoreLiberal() // sets the user to be more liberal based on the biasing
    {
        // for no biasing
        if( this.getType() == 2)
            return;
        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();

        if(checkNewDay(mContext) == false) //  a normal time in the day
        {
            int liberal = prefs.getInt("liberal", 6); // getting the current bias for the day

            if(liberal > 0) // updating the liberal
            {
                editor.putInt("liberal", liberal - 1);
                editor.commit();
            }

            setHowLiberal(this.howLiberal + liberal); // setting the new liberal count
        }

        else // a new day to start with
        {
            saveDateToMemory(mContext); // saving the new day

            editor.putInt("liberal", 5); // resetting the offset in being liberal
            setHowLiberal(this.howLiberal + 6); // adding 6 since the bias is now 6
        }
    }
    public void setMoreConservative() // sets the user to be more liberal based on the biasing
    {
        //for no biasing
        if( this.getType() == 2)
            return;
        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();

        if(checkNewDay(mContext) == false) //  a normal time in the day
        {
            int conservative = prefs.getInt("conservative", 6); // getting the current bias for the day

            if(conservative > 0) // updating the liberal
            {
                editor.putInt("conservative", conservative - 1);
                editor.commit();
            }

            setHowLiberal(this.howLiberal - conservative); // setting the new liberal count
        }

        else // a new day to start with
        {
            saveDateToMemory(mContext); // saving the new day

            editor.putInt("conservative", 5); // resetting the offset in being liberal
            setHowLiberal(this.howLiberal -5); // adding 6 since the bias is now 6
        }
    }
    public int getType() // gets the type of the user
    {
        if(this.username.substring(0,3).equals("BOM")) // setting the type
            this.type = 0;
        else if(this.username.substring(0,3).equals("BMW")) // setting the type
            this.type = 1;
        if(this.username.substring(0,3).equals("NBX")) // setting the type
            this.type = 2;
        return this.type;
    }


    public int gethowLiberal(){ return howLiberal;}
    public void setHowLiberal(int howLiberal) {

        SharedPreferences prefs = mContext.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE); // This gets the storage in the phone
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("howLiberal", howLiberal);
        //editor.putInt("howLiberal", 50);
        if(howLiberal < 5)
            howLiberal = 5;
        if(howLiberal > 95)
            howLiberal = 95;

        Log.i("howLiberal", String.valueOf(howLiberal));
        this.howLiberal = howLiberal;
        editor.commit();

    }

    public boolean checkFirstTime() {
        return (firstTime == 1);
    }
}
