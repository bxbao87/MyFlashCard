package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Image_MultipleChoiceQuestionActivity extends AppCompatActivity {

    Question CurrenQuestion;
    ImageView QuestionImage;
    TextView QuestionContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.image_multiplechoice_quesion);

        Intent intent = getIntent();
        CurrenQuestion = (Question) intent.getSerializableExtra("CurrentQuestion");
    }
}
