package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    MultipleChoiceQuestion CurrentQuestion;

    TextView QuestionContent;
    GridView gridView;
    ImageAnswerAdapter ImageAdapter;
    TextAnswerAdapter TextAdapter;
    Button BtnHint;
    Button BtnCheck;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.multiplechoice_question);

        initComponent();

        Intent intent = getIntent();
        CurrentQuestion = (MultipleChoiceQuestion) intent.getSerializableExtra("CurrentQuestion");


        display();


        //pressAnswer();
    }

    private void display() {
        if (CurrentQuestion.isImageChoice == true){
            ImageAdapter = new ImageAnswerAdapter(MultipleChoiceQuestionActivity.this,
                    R.layout.image_answer, CurrentQuestion.AnswerImage, CurrentQuestion.Answer);
            gridView.setAdapter(ImageAdapter);
        }
        else {
            TextAdapter = new TextAnswerAdapter(MultipleChoiceQuestionActivity.this, R.layout.text_answer, CurrentQuestion.Answer);
            gridView.setAdapter(TextAdapter);
        }

        QuestionContent.setText(CurrentQuestion.getQname());
    }

    private void initComponent() {
        BtnCheck = findViewById(R.id.checkBtn);
        BtnHint = findViewById(R.id.hintBtn);
        QuestionContent = findViewById(R.id.QuestionName);
        gridView = findViewById(R.id.QuestionGridView);
    }

}
