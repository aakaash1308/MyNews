package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogInPage extends AppCompatActivity implements View.OnClickListener{

    public Button Login;
    public EditText LogInId;
    public DatabaseReference databaseReference;
    public int toCheck = -1;

    public List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        LogInId = (EditText) findViewById(R.id.userID);
        Login = (Button) findViewById(R.id.LogIn);
        list = new ArrayList<String>();

        databaseReference =  FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference();


        Login.setOnClickListener(this);
        checkExists();

    }

    @Override
    public void onClick(View v) {
        if(v == Login) {


            if(list.contains( String.valueOf(LogInId.getText()))){

                Intent intent = new Intent(getApplicationContext(), intermediate.class);
                String LoginID = this.LogInId.getText().toString().trim();

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

    public  void checkExists(){

        DatabaseReference users  =  FirebaseDatabase.getInstance("https://echo-chamber-7e6d4.firebaseio.com/").getReference();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                for (DataSnapshot child: snapshot.getChildren()) {
                    if(!child.getKey().equalsIgnoreCase("News"))
                        list.add(child.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
