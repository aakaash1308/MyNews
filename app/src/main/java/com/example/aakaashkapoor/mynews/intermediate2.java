package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermediate2 extends AppCompatActivity implements View.OnClickListener{

    public Button Continue;
    long timeStarted;
    long timeScreen1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeStarted = System.currentTimeMillis();
        Intent intent = getIntent();
        timeScreen1 = intent.getLongExtra("TimeScreen1",0);
        setContentView(R.layout.activity_intermediate2);
        Continue = (Button) findViewById(R.id.Continue);
        Continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        long timePassed =  System.currentTimeMillis() - timeStarted;
        String temp = String.valueOf(timePassed);
        Intent intent = new Intent(getApplicationContext(), finalIntermediate.class);
        intent.putExtra("TimeScreen1",timeScreen1);
        intent.putExtra("TimeScreen2",timePassed);
        finish();
        startActivity(intent);
    }
}
