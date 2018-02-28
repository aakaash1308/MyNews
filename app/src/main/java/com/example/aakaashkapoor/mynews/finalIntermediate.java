package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class finalIntermediate extends AppCompatActivity implements View.OnClickListener {
    public Button Reset;
    public Button Continue;

    long timeElapsed;
    long timeScreen1;
    long timeScreen2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_intermediate);
        timeElapsed = System.currentTimeMillis();

        Continue = (Button) findViewById(R.id.Continue);
        Reset = (Button) findViewById(R.id.reset);

        Intent intent = getIntent();
        timeScreen1 = intent.getLongExtra("TimeScreen1",0);
        timeScreen2 = intent.getLongExtra("TimeScreen2",0);

        Continue.setOnClickListener(this);
        Reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        long timePassed =  System.currentTimeMillis() - timeElapsed;

        if( view == Continue){
            // No change
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            User user = new User(this);
            user.setDaysPassed(-200);
            makeEntry(timeScreen1,timeScreen2,timePassed,"Continue");
            finish();
            startActivity(intent);

        }
        if( view  == Reset){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            User user = new User(this);
            user.setHowLiberal(50);
            user.setType(2);
            makeEntry(timeScreen1,timeScreen2,timePassed,"Reset");

            finish();
            startActivity(intent);

        }
    }


    public void makeEntry(final long ST1, final long ST2, final long ST3, final String choice){
        User user = new User(this);
        final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());
        Date cDate = new Date();
        final String fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX").format(cDate);

        DatabaseReference mdatabase = FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference().child(user.getUsername());
        //gets the entry number
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                databaseReference.child("Intervention").child("date").setValue(fDate);
                databaseReference.child("Intervention").child("screen one time").setValue(ST1);
                databaseReference.child("Intervention").child("screen two time").setValue(ST2);
                databaseReference.child("Intervention").child("screen three time").setValue(ST3);
                databaseReference.child("Intervention").child("choice").setValue(choice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}