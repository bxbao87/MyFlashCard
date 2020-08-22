package com.example.myflashcard;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
//
//enum questionType {
//    MULTIPLE_CHOICE,
//    SINGLE_ANSWER;
//}

public class Question implements Serializable {
    int type;
    String qname;
    String hint;
    String key;                 //correct answer to check with
    Bitmap questionImage;
    boolean isImageQuestion;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Question(int type, String qname, String hint, String key, Bitmap questionImage, boolean isImageQuestion) {
        this.type = type;
        this.qname = qname;
        this.hint = hint;
        this.key = key;
        this.questionImage = questionImage;
        this.isImageQuestion = isImageQuestion;
    }

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

    public boolean isImageQuestion() {
        return isImageQuestion;
    }

    public void setImageQuestion(boolean imageQuestion) {
        isImageQuestion = imageQuestion;
    }
}