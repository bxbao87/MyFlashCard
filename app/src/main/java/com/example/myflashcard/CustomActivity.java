package com.example.myflashcard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class CustomActivity extends AppCompatActivity {
    private Button create, addImage, scan, scan1, scan2;
    private String currentPhotoPath;
    private Bitmap imageAdd;
    private String _question;
    private String _answer;
    private String _hint;
    private String _category;
    private EditText editQuestion, editHint, editAnswer;
    static final int GALLERY_REQUEST_CODE = 12;
    static final int GET_QUESTION = 3;
    static final int GET_HINT = 115;
    static final int GET_ANSWER = 7;
    private ImageView imageView;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        init();
        setScan();
        createNew();
    }
    private boolean isValidate(EditText t)
    {
        if(t.getText().length() < 4)
        {
            t.setError("Invalid Input");
            return false;
        }
        return true;
    }
    private void createNew()
    {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidate(editQuestion) && isValidate(editAnswer))
                {
                    //back to previous activity
                    Intent intent = new Intent();
                    intent.putExtra("Quest", _question);
                    intent.putExtra("Image", imageAdd);
                    intent.putExtra("Hint", _hint);
                    intent.putExtra("Answer", _answer);
                    intent.putExtra("Category", _category);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    private void init() {
        imageView = findViewById(R.id.display_image);

        editQuestion = findViewById(R.id.new_question);
        editHint = findViewById(R.id.new_hint);
        editAnswer = findViewById(R.id.new_answer);
        spinner = findViewById(R.id.category_chosen);
        create = findViewById(R.id.Create);

        scan = findViewById(R.id.scan_question);
        scan1 = findViewById(R.id.scan_hint);
        scan2 = findViewById(R.id.scan_answer);


        addImage = findViewById(R.id.add_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageIntent();
            }
        });
    }
    private void setScan()
    {
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(CustomActivity.this, ScanActivity.class);
                x.putExtra("quest",GET_QUESTION);
                startActivityForResult(x, GET_QUESTION);
            }
        });
        scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(CustomActivity.this, ScanActivity.class);
                x.putExtra("hint",GET_HINT);
                startActivityForResult(x, GET_HINT);
            }
        });
        scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(CustomActivity.this, ScanActivity.class);
                x.putExtra("answer",GET_ANSWER);
                startActivityForResult(x, GET_ANSWER);
            }
        });
    }
    private void getImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pick Image"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri image = data.getData();
            try {
                imageAdd = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(imageAdd);
        }
        if(requestCode == GET_QUESTION)
        {
            if(resultCode == RESULT_OK)
            {
                if(data.getStringExtra("quest") != null ){
                    _question = data.getStringExtra("quest");
                    editQuestion.setText(_question);
                }
            }
        }
        if(requestCode == GET_HINT)
        {
            if(resultCode == RESULT_OK)
            {
                if(data.getStringExtra("hint") != null) {
                    _hint = data.getStringExtra("hint");
                    editHint.setText(_hint);
                }
            }
        }
        if(requestCode == GET_ANSWER)
        {
            if(resultCode == RESULT_OK)
            {
                if(data.getStringExtra("answer") != null) {
                    _answer = data.getStringExtra("answer");
                    editAnswer.setText(_answer);
                }
            }
        }
    }
    private void getCategory()
    {
        _category = String.valueOf(spinner.getSelectedItem());
    }
}
