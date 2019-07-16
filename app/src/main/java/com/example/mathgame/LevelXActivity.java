package com.example.mathgame;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LevelXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        choose layout depending on level
//        if (Level1) setContentView(R.layout.layout1);
//        else if (level2) setContentView(R.layout.layout2);


        setContentView(R.layout.question_area_layout);

        LayoutInflater inflate = null;
        LinearLayout buttonLayout = (LinearLayout) inflate.inflate(
                R.layout.true_false_answer_area_layout, null);


        ConstraintLayout layoutMain = new ConstraintLayout(this);
        setContentView(layoutMain);
        inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout buttonsArea = (LinearLayout) inflate.inflate(
                R.layout.true_false_answer_area_layout, null);

        RelativeLayout.LayoutParams relParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutMain.addView(buttonsArea, 100, 100);
        layoutMain.addView(buttonsArea, relParam);



    }
}
