package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class finalIntermediate extends AppCompatActivity implements View.OnClickListener {

    public String LogInID;

    public Button Reset;
    public Button Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_intermediate);
        Continue = (Button) findViewById(R.id.Continue);
        Reset = (Button) findViewById(R.id.reset);

        Continue.setOnClickListener(this);
        Reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if( view == Continue){
            // No change
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            User user = new User(this);
            user.setDaysPassed(-200);
            finish();
            startActivity(intent);

        }
        if( view  == Reset){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            User user = new User(this);
            user.setHowLiberal(50);
            user.setType(2);

            finish();
            startActivity(intent);

        }
    }
}