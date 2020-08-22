package com.example.myflashcard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button BtnExplore;
    private Button BtnCustom;
    static final int GET_CUSTOM = 135;
    private LoadWriteData fileController = new LoadWriteData(this);
    
    private ArrayList<Categories> CategoryList = new ArrayList<>();
    
    private String _newQuestion;
    private String _newHint;
    private String _newAnswer;
    private String _category;
    private Bitmap _newImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadData(); // always load data first MrX to make sure no leaking
        initButton();
        PressExplore();
        PressCustom();
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
    private void PressCustom()
    {
        BtnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                startActivityForResult(intent, GET_CUSTOM);
            }
        });
    }
    private void initButton() {
        BtnExplore = (Button) findViewById(R.id.exploreBtn);
        BtnCustom = (Button) findViewById(R.id.customBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_CUSTOM)
        {
            if(resultCode == RESULT_OK)
            {
                _newQuestion = data.getStringExtra("Quest");
                _newHint = data.getStringExtra("Hint");
                _newAnswer = data.getStringExtra("Answer");
                _category = data.getStringExtra("Category");
                _newImage = data.getParcelableExtra("Image");
            }
        }
    }
}