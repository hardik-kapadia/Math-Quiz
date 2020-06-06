package com.example.s.braintest;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.*;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.s.braintest.GetData.lim;
import static com.example.s.braintest.GetData.noq;
import static com.example.s.braintest.MainActivity.scores;

public class Test extends AppCompatActivity {

    RelativeLayout game;
    RelativeLayout over;
    TextView question;
    TextView questionNo;
    TextView tscore;
    TextView time;
    TextView finalScore;
    Button opt0;
    Button opt1;
    Button opt2;
    Button opt3;
    Button gameexit;
    ArrayList<Double> options;
    DecimalFormat df;
    int score;
    int fscore;

    char sign;
    int signId;
    int locationOfAnswer;
    int q1;


    public void startGame() {
        game.setVisibility(View.VISIBLE);
        score = 0;
        generateQ(0);
    }

    public void generateQ(int q) {
        q1 = q;
        if (q1 < noq) {
            options = new ArrayList<>();
            double answer;

            df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            StringBuilder str = new StringBuilder();

            signId = (int) (Math.random() * 4);
            switch (signId) {
                case 0:
                    sign = '+';

                    break;
                case 1:
                    sign = '-';
                    break;
                case 2:
                    sign = '*';

                    break;
                case 3:
                    sign = '/';

                    break;
                default:
                    sign = '+';
            }

            if(signId<2){
                double a,b;
                a = Math.random() * lim;
                b = Math.random() * lim;
                if(signId == 0){
                    answer = a+b;
                }
                else{
                    if(b>a){
                        double t = a;
                        a=b;
                        b=t;
                    }
                    answer = a-b;
                }

                question.setText(String.valueOf(df.format(a) + sign + df.format(b)));
            }
            else{
                double a,b;
                a = Math.round(Math.random()*lim);
                b = Math.round(Math.random()*lim);
                if(signId == 2){
                    answer = (a*b);
                }
                else{
                    if(a == 0 || b==0){
                        generateQ(q1);
                    }
                    if(b>a){
                        double t = a;
                        a=b;
                        b=t;
                    }
                    answer = (a/b);
                    if(df.format(answer).equals("0")){
                        generateQ(q1);
                    }
                }
                str.append(a);
                str.append(sign);
                str.append(b);
                Log.i("str",str.toString());
                question.setText(str);
            }

            String qnos = q1 + "/" + noq;
            questionNo.setText(qnos);
            String scorS = score + "/" + q1;
            tscore.setText(scorS);
            locationOfAnswer = (int) (Math.random() * 4);

            for (int i = 0; i < 4; i++) {
                if (i == locationOfAnswer) {

                    options.add(answer);

                } else {
                    double temp = Math.random() * (answer * 2);
                    options.add(temp);

                }
            }

            opt0.setText(String.valueOf(df.format(options.get(0))));
            opt1.setText(String.valueOf(df.format(options.get(1))));
            opt2.setText(String.valueOf(df.format(options.get(2))));
            opt3.setText(String.valueOf(df.format(options.get(3))));

            final CountDownTimer ct = new CountDownTimer(15000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time.setText(String.valueOf((int) (millisUntilFinished / 1000)));
                }

                @Override
                public void onFinish() {
                    generateQ(++q1);
                }
            }.start();
            opt0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // selected = 0;
                    ct.cancel();
                    scoreCheck(0);
                    generateQ(++q1);
                }
            });
            opt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // selected = 1;
                    ct.cancel();
                    scoreCheck(1);
                    generateQ(++q1);
                }
            });
            opt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // selected = 2;
                    ct.cancel();
                    scoreCheck(2);
                    generateQ(++q1);
                }
            });
            opt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // selected = 3;
                    ct.cancel();
                    scoreCheck(3);
                    generateQ(++q1);
                }
            });

        } else {
            Log.i("Info", "GameEnded");
            endGame();
        }
    }

    public void scoreCheck(int sel) {
        if (sel == locationOfAnswer) {
            score++;
        }
    }

    public void gameExit(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void endGame() {
        over.setVisibility(View.VISIBLE);
        game.setVisibility(View.INVISIBLE);
        fscore = score;
        finalScore.setText(String.valueOf("SCORE: " + fscore));
        scores.add(String.valueOf(fscore) + "/" + String.valueOf(noq));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        game = (RelativeLayout) findViewById(R.id.game);
        over = (RelativeLayout) findViewById(R.id.ender);
        over.setVisibility(View.INVISIBLE);
        game.setVisibility(View.INVISIBLE);
        gameexit = (Button) findViewById(R.id.exiter);
        //gameexit.setVisibility(View.INVISIBLE);
        question = (TextView) findViewById(R.id.question);
        questionNo = (TextView) findViewById(R.id.qno);
        tscore = (TextView) findViewById(R.id.score);
        finalScore = (TextView) findViewById(R.id.fScore);
        //finalScore.setVisibility(View.INVISIBLE);
        time = (TextView) findViewById(R.id.Timer);
        opt0 = (Button) findViewById(R.id.option0);
        opt1 = (Button) findViewById(R.id.option1);
        opt2 = (Button) findViewById(R.id.option2);
        opt3 = (Button) findViewById(R.id.option3);

        final TextView starter = (TextView) findViewById(R.id.starter);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                starter.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                starter.setText(String.valueOf("Begin"));
                starter.setVisibility(View.INVISIBLE);
                startGame();
            }
        }.start();
    }
}
