package com.example.myflashcard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadWriteData {
    private Context context;
    private String userCategoryFilename ="userCategory.json";

    public LoadWriteData(Context context)
    {
        this.context = context;
    }

    public ArrayList<Categories> loadUserCategories(){
        return loadCategories(getUserFileContent(userCategoryFilename));
    }
    public ArrayList<Categories> loadSystemCategories(){
        return loadCategories(getSystemFileContent(R.raw.category));
    }

    public Question loadUserQuestion(String filename){
        return loadQuestion(getUserFileContent(filename));
    }
    public Question loadSystemQuestion(String filename){
        int resource = context.getResources().getIdentifier(
                filename,"raw",context.getPackageName());
        return loadQuestion(getSystemFileContent(resource));
    }

    public void createQuestion(Categories categories, Question question, String imagePath){
        try {
            String filename=addMoreQuestion2Category(categories);
            writeQuestionFile(filename,writeQuestionJson(question, imagePath).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String filename) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while(line!=null){
                builder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    private String getUserFileContent(String filename){
        String content="";
        try {
            content = readFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
    private String getSystemFileContent(int resource){
        Scanner scan = new Scanner(
                context.getResources().openRawResource(resource));
        String content = ""; // read entire file
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            content += line;
        }
        scan.close();
        return content;
    }

    private ArrayList<Categories> loadCategories(String content){
        ArrayList<Categories> categoriesArrayList = new ArrayList<>();
        try {
            categoriesArrayList=parseCategories(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoriesArrayList;
    }
    private ArrayList<Categories> parseCategories(String s) throws JSONException {
        ArrayList<Categories> categoriesArrayList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(s);
        for(int i=0; i<jsonArray.length();++i){
            JSONObject object = jsonArray.getJSONObject(i);
            int id = object.getInt("id");
            String name = object.getString("name");
            JSONArray arrayFile = object.getJSONArray("filename");
            ArrayList<String> listFileName = new ArrayList<>();
            for(int j=0;j<arrayFile.length();++j){
                listFileName.add(arrayFile.getString(j));
            }
            Categories categories = new Categories(name,listFileName,id);
            categoriesArrayList.add(categories);
        }

        return categoriesArrayList;
    }

    public Question loadQuestion(String content) {
        Question question = null;
        try {
            question = parseQuestion(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return question;
    }
    private Question parseQuestion(String s) throws JSONException {

        JSONObject jsonObject = new JSONObject(s);
        int type = jsonObject.getInt("type");
        String question = jsonObject.getString("question");
        boolean isImageQuestion = jsonObject.getBoolean("isImageQuestion");
        String questionImagePath = jsonObject.getString("questionImagePath");
        Bitmap questionImage=null;
        if(questionImagePath.contains(".json"))
            questionImage = BitmapFactory.decodeFile(questionImagePath);
        else {
            int resource = context.getResources().getIdentifier(
                    questionImagePath,"raw",context.getPackageName());
            questionImage = BitmapFactory.decodeResource(context.getResources(),resource);
        }
        String hint = jsonObject.getString("hint");
        String key = jsonObject.getString("key");

        Question q;
        if(type==0){
            JSONArray answer = jsonObject.getJSONArray("answer");
            ArrayList<String> listAnswers = new ArrayList<>();
            for(int i=0; i<answer.length();++i){
                listAnswers.add(answer.getString(i));
            }

            boolean isImageChoice = jsonObject.getBoolean("isImageChoice");
            JSONArray answerPath = jsonObject.getJSONArray("answerImagePath");
            ArrayList<Bitmap> answerImages = new ArrayList<>();
            for(int i=0;i<answerPath.length();++i){
                answerImages.add(BitmapFactory.decodeFile(answerPath.getString(i)));
            }

            q = new MultipleChoiceQuestion(question,hint,key,questionImage,isImageQuestion,
                    listAnswers,answerImages,isImageChoice);
        }
        else {
            q = new SingleAnswerQuestion(question,hint,key,questionImage,isImageQuestion,"");
        }
        return q;
    }

    private JSONObject writeQuestionJson(Question question, String imagePath) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",1);
        jsonObject.put("question",question.getQname());
        jsonObject.put("isImageQuestion",question.getIsImageQuestion());
        jsonObject.put("questionImagePath",imagePath);
        jsonObject.put("hint",question.getHint());
        jsonObject.put("key",question.getKey());
        return jsonObject;
    }
    private JSONArray writeCategoryJson(ArrayList<Categories> categoriesArrayList) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<categoriesArrayList.size();++i){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",categoriesArrayList.get(i).getId());
            jsonObject.put("name",categoriesArrayList.get(i).getName());
            //jsonObject.put("filename",categoriesArrayList.get(i).getListQuestions());

            JSONArray arrayFilename = new JSONArray();
            int size = categoriesArrayList.get(i).getListQuestions().size();
            for(int j=0;j<size;++j){
                arrayFilename.put(categoriesArrayList.get(i).getListQuestions().get(j));
            }
            jsonObject.put("filename",arrayFilename);

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
    private void writeQuestionFile(String filename, String content){
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeCategoryFile(String content){
        try (FileOutputStream fos = context.openFileOutput(userCategoryFilename, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String addMoreQuestion2Category(Categories categories){
        String filename="";
        ArrayList<Categories> categoriesArrayList = loadCategories(
                getUserFileContent(userCategoryFilename));

        int id = categories.getId();
        if(id>=categoriesArrayList.size()){
            filename=categories.getName().toLowerCase()+"0.json";
            ArrayList<String> listFile = new ArrayList<>();
            listFile.add(filename);
            categories.setListQuestions(listFile);
            categoriesArrayList.add(categories);
        }
        else {
            Categories category = categoriesArrayList.get(id);
            ArrayList<String> listFile = category.getListQuestions();
            filename = category.getName() + listFile.size() + ".json";
            listFile.add(filename);
        }
        try {
            writeCategoryFile(writeCategoryJson(categoriesArrayList).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return filename;
    }

}
