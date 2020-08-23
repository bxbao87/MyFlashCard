package com.example.myflashcard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Image_SingleAnswerQuestionActivity extends AppCompatActivity {
    SingleAnswerQuestion CurrentQuestion;
    ImageView QuestionImage;
    TextView QuestionContent;
    EditText AnswerOfUser;
    Button BtnHint;
    Button BtnCheck;
    Button BtnCloseHint;
    TextView HintContent;
    Dialog PopUpHint;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.image_singleanswer_question);

        initComponent();

        initComponent();

        Intent intent = getIntent();
        CurrentQuestion = (SingleAnswerQuestion) intent.getSerializableExtra("CurrentQuestion");

        display();

        pressHint();
    }

    private void pressHint() {
        BtnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint();
            }
        });
    }

    private void showHint() {
        PopUpHint.setContentView(R.layout.popup_hint);
        BtnCloseHint = PopUpHint.findViewById(R.id.doneBtn);
        HintContent = PopUpHint.findViewById(R.id.hintContent);

        HintContent.setText(CurrentQuestion.getHint());

        BtnCloseHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpHint.dismiss();
            }
        });

        PopUpHint.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        PopUpHint.show();
    }

    private void display() {
        QuestionContent.setText(CurrentQuestion.getQname());
        QuestionImage.setImageBitmap(CurrentQuestion.getQuestionImage());
    }

    private void initComponent() {
        BtnCheck = findViewById(R.id.checkBtn);
        QuestionImage = findViewById(R.id.ImageOfQuestion);
        QuestionContent = findViewById(R.id.QuestionName);
        AnswerOfUser = findViewById(R.id.AnswerEditText);
        BtnHint = findViewById(R.id.hintBtn);
    }
}
