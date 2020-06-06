package com.example.s.braintest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.s.braintest.MainActivity.names;
import static com.example.s.braintest.MainActivity.rnos;

public class GetData extends AppCompatActivity {

    static int noq;
    static int lim;
    Button easy; // = (Button) findViewById(R.id.diff1);
    Button med; // = (Button) findViewById(R.id.diff2);
    Button hard; // = (Button) findViewById(R.id.diff3);
    Button currently;

    public void go(View view) {
        TextView name = (TextView) findViewById(R.id.getName);
        TextView rno = (TextView) findViewById(R.id.getRno);
        String nameS = name.getText().toString();
        Log.i("Name rcvd:", nameS);
        String rnoS = rno.getText().toString();
        Log.i("Roll no. rcvd: ", rnoS);
        if (!rnos.contains(rnoS)) {
            rnos.add(rnoS);
            names.add(nameS);
            Log.i("Updated names:", names.toString());
            Log.i("Updated IDs: ", rnos.toString());

            Intent i = new Intent(this, Test.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "You can attempt only once!", Toast.LENGTH_LONG).show();
            name.setText(null);
            rno.setText(null);
        }
    }

    public void diff1(View view){
        noq = 10;
        currently.setAlpha(0.4f);
        easy.setAlpha(1f);
        currently = easy;
        lim = 21;
    }

    public void diff2(View view){
        noq = 20;
        med.setAlpha(1f);
        currently.setAlpha(0.4f);
        currently = med;
        lim =51;

    }

    public void diff3(View view){
        noq = 30;
        hard.setAlpha(1f);
        currently.setAlpha(0.4f);
        currently = hard;
        lim =101;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        easy = (Button) findViewById(R.id.diff1);
        med = (Button) findViewById(R.id.diff2);
        hard = (Button) findViewById(R.id.diff3);
        noq =20;
        currently = med;
        easy.setAlpha(0.4f);
        med.setAlpha(0.4f);
        hard.setAlpha(0.4f);
        currently.setAlpha(1f);
        lim = 51;
    }
}
