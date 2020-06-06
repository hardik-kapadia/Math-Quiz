package com.example.s.braintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String> rnos = new ArrayList<>();
    static ArrayList<String> scores = new ArrayList<>();

    public void start(View view) {
        Intent i = new Intent(this, GetData.class);
        startActivity(i);
    }

    public void score(View view) {
        Intent i = new Intent(this, viewScores.class);
        startActivity(i);
    }

    public void exit(View view) {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
