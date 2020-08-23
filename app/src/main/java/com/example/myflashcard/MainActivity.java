package com.example.myflashcard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button BtnExplore;
    private Button BtnCustom;

    private LoadWriteData fileController = new LoadWriteData(this);
    
    private ArrayList<Categories> CategoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadData();
        initButton();
        PressExplore();
        PressCustom();
    }

    private void LoadData() {
        CategoryList = fileController.loadAllCategories();
    }

    private void PressExplore() {
        BtnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                LoadData();
                intent.putExtra("CategoryList", CategoryList);
                startActivity(intent);
            }
        });

    }

    private void PressCustom()
    {
        BtnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButton() {
        BtnExplore = (Button) findViewById(R.id.exploreBtn);
        BtnCustom = (Button) findViewById(R.id.customBtn);
    }
}