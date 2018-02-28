package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermediate extends AppCompatActivity implements View.OnClickListener{

    public Button Continue;
    long timeStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timeStarted = System.currentTimeMillis();
        setContentView(R.layout.activity_intermediate);
        Continue = (Button) findViewById(R.id.Continue);

        Continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        long timePassed =  System.currentTimeMillis() - timeStarted;
        String temp = String.valueOf(timePassed);
        Intent intent = new Intent(getApplicationContext(), intermediate2.class);
        intent.putExtra("TimeScreen1",timePassed);
        finish();
        startActivity(intent);
    }
}
