package com.example.myflashcard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleAnswerQuestionActivity extends AppCompatActivity {
    LoadWriteData fileController = new LoadWriteData(this);

    SingleAnswerQuestion CurrentQuestion;
    TextView QuestionContent;
    EditText AnswerOfUser;
    Button BtnHint;
    Button BtnCheck;
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
        setContentView(R.layout.singleanswer_question);

        getDataFromIntent();
        initComponent();

        display();
        pressHint();
        pressCheck();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        String filename = intent.getStringExtra("CurrentQuestion");
        CurrentQuestion= (SingleAnswerQuestion) fileController.LoadPlayQuestion(filename);

    }

    private void pressCheck() {
        BtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AnswerOfUser.getText().toString().compareTo(CurrentQuestion.getKey()) == 0){
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
        QuestionContent.setText(CurrentQuestion.getQname());
    }

    private void initComponent() {
        BtnCheck = findViewById(R.id.checkBtn);
        QuestionContent = findViewById(R.id.QuestionName);
        AnswerOfUser = findViewById(R.id.AnswerEditText);
        BtnHint = findViewById(R.id.hintBtn);
    }
}
