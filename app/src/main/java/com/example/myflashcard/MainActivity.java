package com.example.myflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        LoadData(); // always load data first MrX to make sure no leaking
        initButton();
        PressExplore();
    }


    private void LoadData() {
        CategoryList = fileController.loadSystemCategories();
    }

    private void initDataDum() {
        CategoryList = new ArrayList<>();
        CategoryList.add(new Categories("Denmark"));
        CategoryList.add(new Categories("Germany"));
        CategoryList.add(new Categories("England"));
    }

    private void PressExplore() {
        BtnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                //Put some thing if necessary

                intent.putExtra("array1", CategoryList);

                startActivity(intent);
                //startActivityForResult(intent, 1);
            }
        });

    }

    private void initButton() {
        BtnExplore = (Button) findViewById(R.id.exploreBtn);
        BtnCustom = (Button) findViewById(R.id.customBtn);
    }
}