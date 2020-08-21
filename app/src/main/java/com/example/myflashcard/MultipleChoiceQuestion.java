package com.example.myflashcard;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {
                                        // default all multiple_choice question have only 4 answer stored in Answer
    ArrayList<String> Answer;           //Answers are text answer if isImageChoice if false
    ArrayList<Bitmap> AnswerImage;
    boolean isImageChoice;              //Answers are text and name of image answers when isImageChoice is true
   // boolean haveImageContent;           //The last element of Answer is name of content image


    public MultipleChoiceQuestion(String qname, String hint, String key, Bitmap questionImage, boolean isImageQuestion, ArrayList<String> answer, ArrayList<Bitmap> answerImage, boolean isImageChoice) {
        super(qname, hint, key, questionImage, isImageQuestion);
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

    public boolean isImageChoice() {
        return isImageChoice;
    }

    public void setImageChoice(boolean imageChoice) {
        isImageChoice = imageChoice;
    }
}
