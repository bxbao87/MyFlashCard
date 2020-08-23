package com.example.myflashcard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MultipleChoiceQuestionActivity extends AppCompatActivity {
    LoadWriteData fileController = new LoadWriteData(this);

    MultipleChoiceQuestion CurrentQuestion;

    TextView QuestionContent;
    GridView gridView;
    ImageAnswerAdapter ImageAdapter;
    TextAnswerAdapter TextAdapter;
    Button BtnHint;
    Button BtnCloseHint;
    TextView HintContent;
    Dialog PopUpHint;

    Button BtnCloseCorrect;
    Dialog PopUpCorrect;
    Button BtnCloseWrong;
    Dialog PopUpWrong;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice_question);

        getDataFromIntent();
        initComponent();

        display();
        pressAnswer();
        pressHint();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        String filename = intent.getStringExtra("CurrentQuestion");
        CurrentQuestion= (MultipleChoiceQuestion) fileController.LoadPlayQuestion(filename);
    }

    private void pressAnswer() {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (CurrentQuestion.getAnswer().get(i).toString().compareTo(CurrentQuestion.getKey()) == 0){
                    showCorrect();
                }
                else {
                    showWrong();
                }
            }
        });
    }

    private void showWrong() {
        PopUpWrong = new Dialog(this);
        PopUpWrong.setContentView(R.layout.popup_wronganswer);
        BtnCloseWrong = PopUpWrong.findViewById(R.id.doneBtn);

        BtnCloseWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpWrong.dismiss();
            }
        });

        PopUpWrong.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        PopUpWrong.show();
    }

    private void showCorrect() {
        PopUpCorrect = new Dialog(this);
        PopUpCorrect.setContentView(R.layout.popup_correctanswer);
        BtnCloseCorrect = PopUpCorrect.findViewById(R.id.doneBtn);

        BtnCloseCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpCorrect.dismiss();
                finish();
            }
        });

        PopUpCorrect.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        PopUpCorrect.show();
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
        PopUpHint = new Dialog(this);
        PopUpHint.setContentView(R.layout.popup_hint);
        BtnCloseHint = PopUpHint.findViewById(R.id.doneBtn);
        HintContent = PopUpHint.findViewById(R.id.hintContent);

        if (CurrentQuestion.getHint().length() > 0) {
            HintContent.setText(CurrentQuestion.getHint());
        }
        else {
            HintContent.setText("Not that hard, think another way !");
        }

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
        if (CurrentQuestion.getIsImageChoice() == true){
            ImageAdapter = new ImageAnswerAdapter(MultipleChoiceQuestionActivity.this,
                    R.layout.image_answer, CurrentQuestion.getAnswerImage(), CurrentQuestion.getAnswer());
            gridView.setAdapter(ImageAdapter);
        }
        else {
            TextAdapter = new TextAnswerAdapter(MultipleChoiceQuestionActivity.this, R.layout.text_answer, CurrentQuestion.getAnswer());
            gridView.setAdapter(TextAdapter);
        }

        QuestionContent.setText(CurrentQuestion.getQname());
    }

    private void initComponent() {
        BtnHint = findViewById(R.id.hintBtn);
        QuestionContent = findViewById(R.id.QuestionName);
        gridView = findViewById(R.id.AnswerGridView);
    }

}
