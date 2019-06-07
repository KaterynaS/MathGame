package com.example.mathgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextview;
    private TextView scoreTextView;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private Question currentQuestion = new Question();
    int questionCounter = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.next_button);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        scoreTextView = findViewById(R.id.score_text_view);
        questionTextview = findViewById(R.id.question_textview);

        nextButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        currentQuestion = currentQuestion.generateArithmeticQuestion();
        updateQuestion();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.next_button:
                currentQuestion = currentQuestion.generateArithmeticQuestion();
                updateQuestion();
                questionCounter++;
                break;
            case R.id.true_button:
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.false_button:
                checkAnswer(false);
                updateQuestion();
                break;
        }
    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = currentQuestion.isAnswerTrue();
        int toastMessageId = 0;
        if (userChooseCorrect == answerIsTrue) {

            toastMessageId = R.string.correct_answer;
            score++;
            updateScore();
            currentQuestion = currentQuestion.generateArithmeticQuestion();
            updateQuestion();
            //move to next question or disable true/false buttons
        } else {
            toastMessageId = R.string.wrong_answer;
            currentQuestion = currentQuestion.generateArithmeticQuestion();
            updateQuestion();
        }
        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_LONG)
                .show();
    }

    private void updateScore() {
        String scoreText = "Score: " + String.valueOf(score);
        scoreTextView.setText(scoreText);
    }

    private void updateQuestion() {

        if(score <= 5)
        {
            String question = currentQuestion.getQuestion();
            questionTextview.setText(question);
        }
        else
        {
            String iloveMila = "I love Mila";
            questionTextview.setText(iloveMila);
        }

    }
}
