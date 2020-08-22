package com.example.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<Categories> CategoryList;
    GridView gridView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        Intent intent = getIntent();
        CategoryList = (ArrayList<Categories>) intent.getSerializableExtra("array1");

        Toast.makeText(this, "second act", Toast.LENGTH_SHORT).show();

        gridView = (GridView) findViewById(R.id.CategoriesListMain);
        CategoryAdapter adapter = new CategoryAdapter(CategoryList, CategoryActivity.this, R.layout.one_category);
        gridView.setAdapter(adapter);
    }
}
