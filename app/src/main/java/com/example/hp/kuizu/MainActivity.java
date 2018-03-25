package com.example.hp.kuizu;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    GridLayout gridLayout;
    int c=0;
    int d=0,e=0,f=0;
    int totalScore=0;
    int ans=0;
    String s;
    String ch[]={"+","-","*","/"};
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;

    public void playAgain(View view)
    {
        sumTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();
        new CountDownTimer(20100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
                if(score>=5)
                    playAgainButton.setText("NEXT LEVEL");
                else
                    playAgainButton.setText("PLAY AGAIN");
                sumTextView.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                if(score>=5&&totalScore<4) {
                    c++;
                    totalScore++;
                }
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: "+ Integer.toString(score) + "/" +Integer.toString(numberOfQuestions));
            }
        }.start();

    }
    public void generateQuestion()
    {
        Random random = new Random();
        int a = random.nextInt(510);
        int b = random.nextInt(510);
        if(totalScore>=4)
        {
            d=random.nextInt(4);
            e=random.nextInt(4);
            f=random.nextInt(510);
            if(d>=e) {
                if (d == 0)
                    ans = a + b;
                else if (d == 1)
                    ans = a - b;
                else if (d == 2)
                    ans = a * b;
                else
                    ans = a / b;
                if (e == 0)
                    ans += f;
                else if (e == 1)
                    ans -= f;
                else if (e == 2)
                    ans *= f;
                else if (e == 3)
                    ans /= f;
            }
            else
            {
                if (e == 0)
                    ans = b + f;
                else if (e == 1)
                    ans = b - f;
                else if (e == 2)
                    ans = b * f;
                else
                    ans = b / f;
                if (d == 0)
                    ans += a;
                else if (d == 1)
                    ans =a- ans;
                else if (d == 2)
                    ans =a* ans;
                else if (d == 3)
                    ans = a/ans;
            }
            s= Integer.toString(a) + ch[d] + Integer.toString(b) + ch[e]+Integer.toString(f);
            sumTextView.setText(s);
        }
        else
            sumTextView.setText(Integer.toString(a) + ch[c] + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        int incorrectAnswer;
        if(totalScore<4) {
            for (int i = 0; i < 4; i++) {

                if (i == locationOfCorrectAnswer) {
                    if (c == 0)
                        answers.add(a + b);
                    else if (c == 1)
                        answers.add(a - b);
                    else if (c == 2)
                        answers.add(a * b);
                    else if (c == 3)
                        answers.add(a / b);
                } else {
                    incorrectAnswer = random.nextInt(510);
                    while (incorrectAnswer == a + b) {
                        incorrectAnswer = random.nextInt(510);

                    }
                    answers.add(incorrectAnswer);
                }
            }
        }
        else
        {
            for (int i = 0; i < 4; i++) {

                if (i == locationOfCorrectAnswer) {
                    answers.add(ans);
                }
                else {
                    incorrectAnswer = random.nextInt(41);
                    while (incorrectAnswer == a + b) {
                        incorrectAnswer = random.nextInt(41);
                    }
                    answers.add(incorrectAnswer);
                }
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" +Integer.toString(numberOfQuestions));
        generateQuestion();
    }
    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
