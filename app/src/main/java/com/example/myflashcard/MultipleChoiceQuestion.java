package com.example.myflashcard;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question implements Serializable {
                                        // default all multiple_choice question have only 4 answer stored in Answer
    private ArrayList<String> Answer;           //Answers are text answer if isImageChoice if false
    private ArrayList<Bitmap> AnswerImage;
    private boolean isImageChoice;              //Answers are text and name of image answers when isImageChoice is true


    public MultipleChoiceQuestion(int type, String qname, String hint, String key, Bitmap questionImage,
                                  boolean isImageQuestion, ArrayList<String> answer,
                                  ArrayList<Bitmap> answerImage, boolean isImageChoice) {
        super(type, qname, hint, key, questionImage, isImageQuestion);
        Answer = answer;
        AnswerImage = answerImage;
        this.isImageChoice = isImageChoice;
    }

    public ArrayList<String> getAnswer() {
        return Answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        Answer = answer;
    }

    public ArrayList<Bitmap> getAnswerImage() {
        return AnswerImage;
    }

    public void setAnswerImage(ArrayList<Bitmap> answerImage) {
        AnswerImage = answerImage;
    }

    public boolean getIsImageChoice() {
        return isImageChoice;
    }

    public void setIsImageChoice(boolean imageChoice) {
        isImageChoice = imageChoice;
    }

}
