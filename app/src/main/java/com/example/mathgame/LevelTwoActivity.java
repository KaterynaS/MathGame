package com.example.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private int currentScore = 0;

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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int scoreFromLvlOne = extras.getInt("currentScore");
        currentScore = scoreFromLvlOne;

        //currentScore = intent.getIntExtra("currentScore",0);

        Log.d("LevelTwo score", "" + currentScore);

        updateScore();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo check radiobuttons state
                //if correct answer chosen - add to score, show next  question
                //else
                isCorrect();
            }
        });
    }


    private void updateScore() {

        //get data from shared pref
//        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
//        int highestScoreCounter = getSharedData.getInt("highest score",  0);
//        highestScoreTextView.setText("Highest score: " + highestScoreCounter);
//
//        //set data to pref
//        if(highestScoreCounter < currentScore)
//        {
//            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("highest score", currentScore);
//            editor.apply(); //saving to disk
//        }


        if(currentScore >= 40)
        {
            Toast.makeText(LevelTwoActivity.this,
                    "Мила, ты здорово считаешь!\nТеперь время читать!",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            currentScoreTextView.setText("Score: " + currentScore);
        }
    }


    private void isCorrect() {

        boolean isCorrect = false;

        int corrAns = currentQuestion.getCorrectAnswer();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);

        int selectedId = rg.getCheckedRadioButtonId();

        if (selectedId == -1)
        {
            isCorrect = false;
            Toast.makeText(LevelTwoActivity.this, "Choose something", Toast.LENGTH_SHORT).show();
        }
        else {
            RadioButton radioButton = (RadioButton)rg.findViewById(selectedId);
            String selectedRadioButtonValue = radioButton.getText().toString();

            if(corrAns==Integer.valueOf(selectedRadioButtonValue))
            {
                isCorrect=true;
                Toast.makeText(LevelTwoActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                currentScore = currentScore + 3;
                updateScore();
                currentQuestion = currentQuestion.generateArithmeticQuestion();
                rg.clearCheck();
                updateQuestion();
            }
            else
            {
                isCorrect = false;
                Toast.makeText(LevelTwoActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                if(currentScore >= 2)
                {
                    currentScore = currentScore - 2;
                }
                updateScore();
                currentQuestion = currentQuestion.generateArithmeticQuestion();
                rg.clearCheck();
                updateQuestion();
            }
        }
    }


    private void updateQuestion() {
        String question = currentQuestion.getQuestionText();
        questionTextView.setText(question);
        String msg = "question generated";
        Log.d("LevelTwo", msg);

        int[] answersArray = currentQuestion.getAnswerChoices();
        int corrAnsID = currentQuestion.getCorrectAnswer();

        Log.d("LevelTwo answers elems", "" + answersArray[0] + answersArray[1] + answersArray[2] + answersArray[3]);
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
