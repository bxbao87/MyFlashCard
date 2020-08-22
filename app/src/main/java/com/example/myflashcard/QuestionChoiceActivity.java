package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    }

    private void initGridView() {
        gridView = findViewById(R.id.QuestionGridView);
    }
}
