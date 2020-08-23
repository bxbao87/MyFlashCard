package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class QuestionChoiceActivity extends AppCompatActivity {
    private Categories CategoryItem;
    private GridView gridView;
    private QuestionChoiceAdapter adapter;
    private TextView Slogan;

    LoadWriteData fileController = new LoadWriteData(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        getDataFromIntent();
        initGridView();
        createAdapter();
        pressQuestionItem();
        display();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        CategoryItem = (Categories) intent.getSerializableExtra("CategoryItem");
    }

    private void createAdapter(){
        adapter = new QuestionChoiceAdapter(CategoryItem.getListQuestions(), QuestionChoiceActivity.this, R.layout.one_quesion);
        gridView.setAdapter(adapter);
    }

    private void display() {
        Slogan.setText("    Oh easy " + CategoryItem.getName() + ", \n          Let's get them all !" );
    }

    private void pressQuestionItem() {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question CurrentQuestion = fileController.LoadPlayQuestion(CategoryItem.getListQuestions().get(i));

                Intent intent;
                if (CurrentQuestion.getType() == 0) {
                    //Multiple question question
                    if (CurrentQuestion.getIsImageQuestion() == true) {
                        intent = new Intent(QuestionChoiceActivity.this, Image_MultipleChoiceQuestionActivity.class);
                    }
                    else {
                        intent = new Intent(QuestionChoiceActivity.this, MultipleChoiceQuestionActivity.class);
                    }
                }
                else {
                    //Single answer question
                    if (CurrentQuestion.getIsImageQuestion() == true) {
                        intent = new Intent(QuestionChoiceActivity.this, Image_SingleAnswerQuestionActivity.class);
                    }
                    else {
                        intent = new Intent(QuestionChoiceActivity.this, SingleAnswerQuestionActivity.class);
                    }
                }

                intent.putExtra("CurrentQuestion", CategoryItem.getListQuestions().get(i));
                startActivity(intent);
            }
        });
    }

    private void initGridView() {
        Slogan = (TextView) findViewById(R.id.slogan);
        gridView = findViewById(R.id.QuestionGridView);
    }
}
