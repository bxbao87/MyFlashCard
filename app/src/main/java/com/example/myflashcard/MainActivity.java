package com.example.myflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.myflashcard.questionType.MULTIPLE_CHOICE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> nameImage = new ArrayList<>();
        nameImage.add("Rat");
        nameImage.add("Cat");
        nameImage.add("Dog");
        nameImage.add("Pig");
        nameImage.add("Rat.png");
        nameImage.add("Cat.png");
        nameImage.add("Dog.png");
        nameImage.add("Pig.png");

        String key = "Cat";

        Question x = new MultiChoice_Quest(MULTIPLE_CHOICE, "Who is Doraemon ?", "Doraemon hates mouse a lot !", key, nameImage, true, false);

        ArrayList<Question> arrayQuest = new ArrayList<>();
        arrayQuest.add(x);

        Categories y = new Categories("Japan", arrayQuest);

    }
}