package com.example.myflashcard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Image_MultipleChoiceQuestionActivity extends AppCompatActivity {

    MultipleChoiceQuestion CurrentQuestion;
    ImageView QuestionImage;
    TextView QuestionContent;
    GridView gridView;
    ImageAnswerAdapter ImageAdapter;
    TextAnswerAdapter TextAdapter;
    Button BtnHint;
    Button BtnCloseHint;
    TextView HintContent;
    Dialog PopUpHint;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.image_multiplechoice_quesion);

        initComponent();

        Intent intent = getIntent();
        CurrentQuestion = (MultipleChoiceQuestion) intent.getSerializableExtra("CurrentQuestion");


        display();


        //pressAnswer();

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
        if (CurrentQuestion.isImageChoice == true){
            ImageAdapter = new ImageAnswerAdapter(Image_MultipleChoiceQuestionActivity.this,
                    R.layout.image_answer, CurrentQuestion.AnswerImage, CurrentQuestion.Answer);
            gridView.setAdapter(ImageAdapter);
        }
        else {
            TextAdapter = new TextAnswerAdapter(Image_MultipleChoiceQuestionActivity.this, R.layout.text_answer, CurrentQuestion.Answer);
            gridView.setAdapter(TextAdapter);
        }

        QuestionContent.setText(CurrentQuestion.getQname());
        QuestionImage.setImageBitmap(CurrentQuestion.getQuestionImage());
    }

    private void initComponent() {
        BtnHint = findViewById(R.id.hintBtn);
        QuestionImage = findViewById(R.id.ImageOfQuestion);
        QuestionContent = findViewById(R.id.QuestionName);
        gridView = findViewById(R.id.QuestionGridView);
    }
}
