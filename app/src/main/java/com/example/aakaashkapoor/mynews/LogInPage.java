package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

public class LogInPage extends AppCompatActivity implements View.OnClickListener{

    public Button Login;
    public EditText LogInId;
    public DatabaseReference databaseReference;
    public int toCheck = -1;

    final List<Entry> list = Collections.EMPTY_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        LogInId = (EditText) findViewById(R.id.userID);
        Login = (Button) findViewById(R.id.LogIn);

        databaseReference =  FirebaseDatabase.getInstance().getReference().child("mynews-b08ca");


        Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == Login) {

            if(checkExists() == 1) {
                Intent intent = new Intent(getApplicationContext(), intermediate.class);
                String LoginID = this.LogInId.getText().toString().trim();
                Log.i("TEST", LoginID);

                User user = new User(this);
                user.setUsername(LoginID);
                intent.putExtra(LoginID, LoginID);
                finish();
                startActivity(intent);
            }else{
                Toast.makeText(this, "Invalid Log In ID", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private int checkExists(){

        DatabaseReference users = FirebaseDatabase.getInstance().getReference();
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(String.valueOf(LogInId.getText())).exists()) {
                    // run some code
                    toCheck = 1;
                }else{
                    toCheck = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return toCheck;
    }
}
