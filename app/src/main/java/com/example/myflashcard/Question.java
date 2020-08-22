package com.example.myflashcard;

import android.graphics.Bitmap;

import java.util.ArrayList;
//
//enum questionType {
//    MULTIPLE_CHOICE,
//    SINGLE_ANSWER;
//}

public abstract class Question {
    String qname;
    String hint;
    String key;                 //correct answer to check with
    Bitmap questionImage;
    boolean isImageQuestion;

    public Question(String qname, String hint, String key, Bitmap questionImage, boolean isImageQuestion) {
        this.qname = qname;
        this.hint = hint;
        this.key = key;
        this.questionImage = questionImage;
        this.isImageQuestion = isImageQuestion;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Bitmap getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(Bitmap questionImage) {
        this.questionImage = questionImage;
    }

    public boolean getIsImageQuestion() {
        return isImageQuestion;
    }

    public void setImageQuestion(boolean imageQuestion) {
        isImageQuestion = imageQuestion;
    }

    public abstract boolean checkAnswer(String answer);

}