package com.example.mathgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView scoreTextView;
    private Button trueButton;
    private Button falseButton;
    private Button nextLvlButton;
    private TextView highestScoreTextView;
    private QuestionTrueFalse currentQuestion = new QuestionTrueFalse();
    private Score currentScore = new Score();
    private static final String MESSAGE_ID = "highest score";


    //todo score in a separate class with addScore and decreaseScore methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        currentQuestion = currentQuestion.generateArithmeticQuestion();
        updateQuestion();
        updateScore();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.false_button:
                checkAnswer(false);
                updateQuestion();
                break;
            case  R.id.next_level_button:
                openLevelTwo();
                break;
        }
    }


    private void openLevelTwo() {
        Bundle extras = new Bundle();
        extras.putInt("currentScore", currentScore.getCurrentScore());
        Intent openLvlTwo = new Intent(this, LevelTwoActivity.class);
        openLvlTwo.putExtras(extras);
        startActivity(openLvlTwo);
    }


    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = currentQuestion.isAnswerTrue();
        int toastMessageId = 0;
        if (userChooseCorrect == answerIsTrue) {

            toastMessageId = R.string.correct_answer;
            currentScore.addScore(2);
            updateScore();
            currentQuestion = currentQuestion.generateArithmeticQuestion();
            updateQuestion();
            //move to next question or disable true/false buttons
        } else
            {
            toastMessageId = R.string.wrong_answer;

            if (currentScore.getCurrentScore() > 0)
            {
                currentScore.lowerTheScore(1);
            }
            updateScore();
            currentQuestion = currentQuestion.generateArithmeticQuestion();
            updateQuestion();
        }
        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_LONG)
                .show();
    }


    private void updateScore() {

        if (currentScore.getCurrentScore() >= 10)
        {
            nextLvlButton.setVisibility(View.VISIBLE);
        }

        //get data from shared pref
        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        int highestScoreCounter = getSharedData.getInt("highest score",  0);
        highestScoreTextView.setText("Highest score: " + highestScoreCounter);

        //set data to pref
        if(highestScoreCounter < currentScore.getCurrentScore())
        {
            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highest score", currentScore.getCurrentScore());
            editor.apply(); //saving to disk
        }
        scoreTextView.setText("Score: " + currentScore.getCurrentScore());
    }


    private void updateQuestion() {
        String question = currentQuestion.getQuestion();
        questionTextView.setText(question);
    }
}
