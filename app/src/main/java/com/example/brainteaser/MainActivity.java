package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ConstraintLayout gameLayout;
    Button playAgainButton;
    TextView timeTextView,quesTextView,scoreTextView,resultTextView;
    Button button0,button1,button2,button3;
    ArrayList<Integer> option =new ArrayList<Integer>();
    int location,score=0,total=0;
    CountDownTimer count;

    public void playGame(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){

        String tag = view.getTag().toString();
        if(tag.equals(Integer.toString(location))) {
            resultTextView.setText("Correct!");
            score++;
        }
        else
            resultTextView.setText("Wrong!");
        resultTextView.setVisibility(View.VISIBLE);
        total++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(total));
        newQuestion();
    }

    public void newQuestion() {
        playAgainButton.setVisibility(View.INVISIBLE);
        //resultTextView.setVisibility(View.INVISIBLE);
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        quesTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        location = rand.nextInt(4);

        option.clear();
        for(int i=0;i<4;i++) {
            if (i != location) {
                int wrong = rand.nextInt(41);
                while(wrong==(a+b)) {
                    wrong = rand.nextInt(41);
                }
                option.add(wrong);
            }
            else {
                option.add(a+b);
            }
        }

        button0.setText(Integer.toString(option.get(0)));
        button1.setText(Integer.toString(option.get(1)));
        button2.setText(Integer.toString(option.get(2)));
        button3.setText(Integer.toString(option.get(3)));
    }


    public void playAgain(View view) {
        timeTextView.setText("30s");
        scoreTextView.setText("0/0");
        score=0;
        total=0;
        resultTextView.setVisibility(View.INVISIBLE);

         newQuestion();
         setTime();
    }

    public void setTime() {

        count = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Done!");
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        gameLayout=findViewById(R.id.gameLayout);
        playAgainButton=findViewById(R.id.playAgainButton);
        timeTextView=findViewById(R.id.timeTextView);
        quesTextView=findViewById(R.id.quesTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        resultTextView=findViewById(R.id.resultTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgain(findViewById(R.id.button0));
    }
}
