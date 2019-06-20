package com.example.mathgame;

import android.arch.lifecycle.ViewModelProviders;
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
    public static final String HIGHEST_SCORE = "highest score";
    private static final String MESSAGE_ID = HIGHEST_SCORE;
    LevelOneViewModel levelOneViewModel;

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


        levelOneViewModel = ViewModelProviders.of(this).get(LevelOneViewModel.class);
        updateQuestionTextView(levelOneViewModel.getCurrentQuestion());
        Log.d("Lvl1 OnCreate", "current question = " + levelOneViewModel.getCurrentQuestion().getQuestionText());

        updateScore();

        MyMathGame appState = ((MyMathGame)getApplicationContext());


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
        boolean answerIsTrue = levelOneViewModel.currentQuestion.isAnswerTrue();
        int toastMessageId;
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        if (userInput == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            appState.addTwoToCurrentScore();
        } else {
            toastMessageId = R.string.wrong_answer;
            if (appState.getCurrentScore() > 0)
            {
                appState.subtractOneFromCurrentScore();
            }
        }
        updateScore();
        updateQuestionTextView(levelOneViewModel.getNewQuestion());
        Toast.makeText(LevelOneActivity.this, toastMessageId,
                Toast.LENGTH_LONG)
                .show();
    }


    private void updateScore() {
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        if (appState.getCurrentScore() >= 16)
        {
            nextLvlButton.setVisibility(View.VISIBLE);
            trueButton.setVisibility(View.INVISIBLE);
            falseButton.setVisibility(View.INVISIBLE);
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


    private void updateQuestionTextView(QuestionTrueFalse q)
    {
        String s = q.getQuestionText();
        questionTextView.setText(s);
    }
}
