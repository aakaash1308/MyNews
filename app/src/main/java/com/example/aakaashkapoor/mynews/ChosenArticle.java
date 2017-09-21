package com.example.aakaashkapoor.mynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class ChosenArticle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_article);

        Intent intent = getIntent();
        String destination = "";
        destination = intent.getStringExtra(String.valueOf(ArticleChoiceActivity.messanger));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(destination);

        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl(destination);
    }
}
