package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuestionChoiceActivity extends AppCompatActivity {
    private Categories CategoryItem;
    private GridView gridView;
    private QuestionChoiceAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        initGridView();

        Intent intent = getIntent();
        CategoryItem = (Categories) intent.getSerializableExtra("CategoryItem");

        adapter = new QuestionChoiceAdapter(CategoryItem.getListQuestions(), QuestionChoiceActivity.this, R.layout.one_quesion);
        gridView.setAdapter(adapter);


        pressQuestionItem();
    }

    private void pressQuestionItem() {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question CurrentQuestion = new Question(1, "Who is Doraemon", "hint", "Cat", null, false);
                /*TODO
                    Load & store data: load Question i th from the current category
                                       Store content of Question ith to CurrentQuestion
                */

                Intent intent;

                if (CurrentQuestion.getType() == 0) {
                    //Multiple question question

                    if (CurrentQuestion.isImageQuestion == true) {
                        intent = new Intent(QuestionChoiceActivity.this, Image_MultipleChoiceQuestionActivity.class);
                    }
                    else {
                        intent = new Intent(QuestionChoiceActivity.this, MultipleChoiceQuestionActivity.class);
                    }
                }
                else {
                    //Single answer question

                    if (CurrentQuestion.isImageQuestion == true) {
                        intent = new Intent(QuestionChoiceActivity.this, Image_SingleAnswerQuestionActivity.class);
                    }
                    else {
                        intent = new Intent(QuestionChoiceActivity.this, SingleAnswerQuestionActivity.class);
                    }
                }

                intent.putExtra("CurrentQuestion", CurrentQuestion);

                startActivity(intent);

            }
        });
    }

    private void initGridView() {
        gridView = findViewById(R.id.QuestionGridView);
    }
}
