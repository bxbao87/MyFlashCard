package com.example.myflashcard;

import java.util.ArrayList;

public class Categories {
    String Name;
    ArrayList<Question> Quest;

    public Categories(String name, ArrayList<Question> quest) {
        Name = name;
        Quest = quest;
    }
}
