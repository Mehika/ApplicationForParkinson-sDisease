package com.example.mehikamanocha.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class Instructions extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // this is required to have a back button on the page
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startActivity(View view) {
        Intent intent = new Intent(this, FingerTapping.class);
        startActivity(intent);
    }
}
