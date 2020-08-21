package com.example.myflashcard;

import java.util.ArrayList;

public class Categories {
    String Name;
    ArrayList<String> ListQuestions;

    public Categories(String name, ArrayList<String> quest) {
        Name = name;
        ListQuestions = quest;
    }
}
