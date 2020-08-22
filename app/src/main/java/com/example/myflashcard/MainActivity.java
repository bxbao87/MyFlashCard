package com.example.myflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private Button BtnExplore;
    private Button BtnCustom;
    private ArrayList<Categories> CategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> nameImage = new ArrayList<>();
//        nameImage.add("Rat");
//        nameImage.add("Cat");
//        nameImage.add("Dog");
//        nameImage.add("Pig");
//        nameImage.add("Rat.png");
//        nameImage.add("Cat.png");
//        nameImage.add("Dog.png");
//        nameImage.add("Pig.png");
//
//        String key = "Cat";
//
//        Question x = new MultipleChoiceQuestion(MULTIPLE_CHOICE, "Who is Doraemon ?", "Doraemon hates mouse a lot !", key, nameImage, true, false);
//
//        ArrayList<Question> arrayQuest = new ArrayList<>();
//        arrayQuest.add(x);
//
//        Categories y = new Categories("Japan", arrayQuest);

        initButton();

        PressExplore();

        initDataDum();
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