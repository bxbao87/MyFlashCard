package com.example.myflashcard;

import java.util.ArrayList;

public class MultiChoice_Quest extends Question {
                                        // default all multiple_choice question have only 4 answer stored in Answer
    ArrayList<String> Answer;           //Answers are text answer if isImageChoice if false
    boolean isImageChoice;              //Answers are text and name of image answers when isImageChoice is true
    boolean haveImageContent;           //The last element of Answer is name of content image

    public MultiChoice_Quest(questionType type, String name, String hint,  String key, ArrayList<String> answer, boolean isImageChoice, boolean haveImageContent) {
        super(type, name, hint, key);
        Answer = answer;
        this.isImageChoice = isImageChoice;
        this.haveImageContent = haveImageContent;
    }
}
