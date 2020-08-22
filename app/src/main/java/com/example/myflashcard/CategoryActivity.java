package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private ArrayList<Categories> CategoryList;
    private GridView gridView;
    private CategoryAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        initGridView();

        Intent intent = getIntent();
        CategoryList = (ArrayList<Categories>) intent.getSerializableExtra("CategoryList");

        Toast.makeText(this, "second act", Toast.LENGTH_SHORT).show();


        adapter = new CategoryAdapter(CategoryList, CategoryActivity.this, R.layout.one_category);
        gridView.setAdapter(adapter);


        pressCategoryItem();

    }

    private void pressCategoryItem() {
       gridView.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(CategoryActivity.this, QuestionChoiceActivity.class);
               intent.putExtra("CategoryItem", CategoryList.get(i));
               startActivity(intent);
           }
       });
    }

    private void initGridView() {
        gridView = (GridView) findViewById(R.id.CategoriesGridView);
    }
}
