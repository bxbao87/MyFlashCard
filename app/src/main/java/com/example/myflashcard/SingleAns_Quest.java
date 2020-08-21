package com.example.myflashcard;

public class SingleAns_Quest extends Question {
    String imageContent;        //name of image go with content of the question

    public SingleAns_Quest(questionType type, String name, String hint, String key, String imageContent) {
        super(type, name, hint, key);
        this.imageContent = imageContent;
    }
}
