package com.example.myflashcard;

public class SingleAns_Quest extends Question {
    String Key;                 //correct answer to check with
    String imageContent;        //name of image go with content of the question

    public SingleAns_Quest(int type, String name, String hint, String key, String imageContent) {
        super(type, name, hint);
        Key = key;
        this.imageContent = imageContent;
    }
}
