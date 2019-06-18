package com.example.mathgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class LevelTwoActivity extends AppCompatActivity {

    private QuestionRadioButtonFourAnswers currentQuestion = new QuestionRadioButtonFourAnswers();
    private TextView questionTextView;
    private TextView currentScoreTextView;
    private Button submitButton;
    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;
    private RadioButton radioButtonThree;
    private RadioButton radioButtonFour;
    private TextView highestScoreTextView;
    public static final String HIGHEST_SCORE = "highest score";
    private static final String MESSAGE_ID = HIGHEST_SCORE;

//    private int currentScore = 0;

    //todo maintain highest score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        currentScoreTextView = findViewById(R.id.score_text_view);
        questionTextView = findViewById(R.id.question_textview);
        submitButton = findViewById(R.id.answer_button);
        radioButtonOne = findViewById(R.id.choice_1_radio_button);
        radioButtonTwo = findViewById(R.id.choice_2_radio_button);
        radioButtonThree = findViewById(R.id.choice_3_radio_button);
        radioButtonFour = findViewById(R.id.choice_4_radio_button);
        highestScoreTextView = findViewById(R.id.highest_score_text_view);

        currentQuestion = currentQuestion.generateArithmeticQuestion();
        updateQuestion();

        //todo get currenrscore bundle

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        int scoreFromLvlOne = extras.getInt("currentScore");
//        currentScore = scoreFromLvlOne;

        MyMathGame appState = ((MyMathGame)getApplicationContext());


        //currentScore = intent.getIntExtra("currentScore",0);

        Log.d("LevelTwo score", "" + appState.getCurrentScore());

        updateScore();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrect();
            }
        });
    }


    private void updateScore() {
        MyMathGame appState = ((MyMathGame)getApplicationContext());
        currentScoreTextView.setText("Score: " + appState.getCurrentScore());
        updateHighestScore();
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


    private void isCorrect() {

        MyMathGame appState = ((MyMathGame)getApplicationContext());
        int corrAns = currentQuestion.getCorrectAnswer();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);

        int selectedId = rg.getCheckedRadioButtonId();

        if (selectedId == -1)
        {
            Toast.makeText(LevelTwoActivity.this, "Choose something", Toast.LENGTH_SHORT).show();
        }
        else {
            RadioButton radioButton = (RadioButton)rg.findViewById(selectedId);
            String selectedRadioButtonValue = radioButton.getText().toString();

            if(corrAns==Integer.valueOf(selectedRadioButtonValue))
            {
                Toast.makeText(LevelTwoActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                appState.addTwoToCurrentScore();
            }
            else
            {
                Toast.makeText(LevelTwoActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                if(appState.getCurrentScore() >= 2)
                {
                    appState.substructOneFromCurrentScore();
                }
            }
            updateScore();
            currentQuestion = currentQuestion.generateArithmeticQuestion();
            rg.clearCheck();
            updateQuestion();
        }
    }


    private void updateQuestion() {
        String question = currentQuestion.getQuestionText();
        questionTextView.setText(question);
        String msg = "question generated";
        Log.d("LevelTwo", msg);

        int[] answersArray = currentQuestion.getAnswerChoices();
        int corrAnsID = currentQuestion.getCorrectAnswer();

        Log.d("LevelTwo answers elems", "" + Arrays.toString(answersArray));
        Log.d("LevelTwo correctAnsID", "" + corrAnsID);

        String choice0 = String.valueOf(currentQuestion.getAnswerChoices()[0]);
        String choice1 = String.valueOf(currentQuestion.getAnswerChoices()[1]);
        String choice2 = String.valueOf(currentQuestion.getAnswerChoices()[2]);
        String choice3 = String.valueOf(currentQuestion.getAnswerChoices()[3]);

        radioButtonOne.setText(choice0);
        radioButtonTwo.setText(choice1);
        radioButtonThree.setText(choice2);
        radioButtonFour.setText(choice3);
    }
}
