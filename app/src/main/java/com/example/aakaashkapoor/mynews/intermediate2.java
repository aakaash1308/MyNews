package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermediate2 extends AppCompatActivity implements View.OnClickListener{

    public String LogInID;

    public Button Reset;
    public Button Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate2);
        Continue = (Button) findViewById(R.id.Continue);

        Continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), finalIntermediate.class);
        finish();
        startActivity(intent);
    }
}