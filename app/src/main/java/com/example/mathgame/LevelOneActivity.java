package com.example.mathgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LevelOneActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView scoreTextView;
    private Button trueButton;
    private Button falseButton;
    private Button nextLvlButton;
    private TextView highestScoreTextView;
    private QuestionTrueFalse currentQuestion = new QuestionTrueFalse();
    //private Score currentScore = new Score();
    public static final String HIGHEST_SCORE = "highest score";
    private static final String MESSAGE_ID = HIGHEST_SCORE;


    //todo score in a separate class with addScore and decreaseScore methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        scoreTextView = findViewById(R.id.score_text_view);
        questionTextView = findViewById(R.id.question_textview);
        highestScoreTextView = findViewById(R.id.highest_score_text_view);
        nextLvlButton = findViewById(R.id.next_level_button);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        nextLvlButton.setVisibility(View.INVISIBLE);
        nextLvlButton.setOnClickListener(this);

        showNextQuestion();
        updateScore();

        MyMathGame appState = ((MyMathGame)getApplicationContext());
        Log.d("Lvl1 OnCreate", "current score = " + appState.getCurrentScore());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case  R.id.next_level_button:
                openLevelTwo();
                break;
        }
    }

    private void openLevelTwo() {
        Bundle extras = new Bundle();
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        extras.putInt("currentScore", appState.getCurrentScore());
        Intent openLvlTwo = new Intent(this, LevelTwoActivity.class);
        openLvlTwo.putExtras(extras);
        startActivity(openLvlTwo);
    }


    private void checkAnswer(boolean userInput) {
        boolean answerIsTrue = currentQuestion.isAnswerTrue();
        int toastMessageId;
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        if (userInput == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            //currentScore.addScore(2);
            appState.addTwoToCurrentScore();
            Log.d("Lvl1 OnCreate", "current score = " + appState.getCurrentScore());
        } else {
            toastMessageId = R.string.wrong_answer;
            if (appState.getCurrentScore() > 0)
            {
                //currentScore.lowerTheScore(1);
                appState.substructOneFromCurrentScore();
            }
        }
        updateScore();
        showNextQuestion();
        Toast.makeText(LevelOneActivity.this, toastMessageId,
                Toast.LENGTH_LONG)
                .show();
    }


    private void updateScore() {
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        if (appState.getCurrentScore() >= 10)
        {
            nextLvlButton.setVisibility(View.VISIBLE);
        }

        updateHighestScore();

        scoreTextView.setText("Score: " + appState.getCurrentScore());
    }


    private void updateHighestScore()
    {
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        int highestScoreCounter = getSharedData.getInt(HIGHEST_SCORE,  0);
        highestScoreTextView.setText("Highest score: "  + highestScoreCounter);

        if(highestScoreCounter < appState.getCurrentScore())
        {
            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(HIGHEST_SCORE, appState.getCurrentScore());
            editor.apply(); //saving to disk
        }
    }


    private void showNextQuestion() {
        currentQuestion = currentQuestion.generateArithmeticQuestion();
        questionTextView.setText(currentQuestion.getQuestion());
    }
}
