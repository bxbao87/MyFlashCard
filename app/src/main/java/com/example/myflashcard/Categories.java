package com.example.myflashcard;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {
    int CategoryID;
    String Name;
    ArrayList<String> ListQuestions;

    public Categories(String name) {
        Name = name;
        ListQuestions = null;
    }

    public Categories(int categoryID, String name) {
        CategoryID = categoryID;
        Name = name;
    }

    public Categories(String name, ArrayList<String> quest) {
        Name = name;
        ListQuestions = quest;
    }

    public Categories(int categoryID, String name, ArrayList<String> listQuestions) {
        CategoryID = categoryID;
        Name = name;
        ListQuestions = listQuestions;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<String> getListQuestions() {
        return ListQuestions;
    }

    public void setListQuestions(ArrayList<String> listQuestions) {
        ListQuestions = listQuestions;
    }
}
