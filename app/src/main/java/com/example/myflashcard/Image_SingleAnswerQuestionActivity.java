package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.image_singleanswer_question);

        initComponent();

        initComponent();

        Intent intent = getIntent();
        CurrentQuestion = (SingleAnswerQuestion) intent.getSerializableExtra("CurrentQuestion");

        display();
    }

    private void display() {
        QuestionContent.setText(CurrentQuestion.getQname());
        QuestionImage.setImageBitmap(CurrentQuestion.getQuestionImage());
    }

    private void initComponent() {
        QuestionImage = findViewById(R.id.ImageOfQuestion);
        QuestionContent = findViewById(R.id.QuestionName);
        AnswerOfUser = findViewById(R.id.AnswerEditText);
        BtnHint = findViewById(R.id.hintBtn);
    }
}
