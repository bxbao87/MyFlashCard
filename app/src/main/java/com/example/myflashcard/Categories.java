package com.example.myflashcard;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {

    private String Name; // user can interact with this
    private ArrayList<String> ListQuestions; // system interact only
    private int id; // system interact only


    public Categories(String name) {
        Name = name;
        ListQuestions = null;
    }


    public Categories(int categoryID, String name) {
        id = categoryID;
        Name = name;
    }

    public Categories(String name, ArrayList<String> quest) {
        Name = name;
        ListQuestions = quest;
    }

        Categories(String name, ArrayList<String> listQuestions, int id) {
        this.Name = name;
        this.ListQuestions = listQuestions;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public Categories(int categoryID, String name, ArrayList<String> listQuestions) {
        id = categoryID;
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
