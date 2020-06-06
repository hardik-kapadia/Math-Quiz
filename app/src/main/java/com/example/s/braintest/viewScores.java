package com.example.s.braintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static com.example.s.braintest.MainActivity.names;
import static com.example.s.braintest.MainActivity.rnos;
import static com.example.s.braintest.MainActivity.scores;

public class viewScores extends AppCompatActivity {
    ListView viewName;
    ListView viewId;
    ListView viewMarks;


    public void scoreExiter(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);
        viewName = (ListView) findViewById(R.id.nameList);
        viewId = (ListView) findViewById(R.id.idList);
        viewMarks = (ListView) findViewById(R.id.scoreList);
        final ArrayList<String> names1 = names;
        final ArrayList<String> rnos1 = rnos;
        final ArrayList<String> scores1 = scores;
        ArrayAdapter<String> aa1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names1);
        viewName.setAdapter(aa1);
        ArrayAdapter<String> aa2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rnos1);
        viewId.setAdapter(aa2);
        ArrayAdapter<String> aa3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores1);
        viewMarks.setAdapter(aa3);
    }
}
