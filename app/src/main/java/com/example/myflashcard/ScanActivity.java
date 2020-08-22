package com.example.myflashcard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.List;

public class ScanActivity extends AppCompatActivity {
    private Button _capture, _extract, _gallery, _confirm;
    private ImageView _imageView;
    private TextView _textView;
    static final int GALLERY_REQUEST_CODE_SCAN = 123;
    private Bitmap imageBitmap;
    private String _textReturn = "";
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scan_activity);

        initComponents();
        takePhoto();
        receiveIntent();
        _extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageBitmap != null)
                    detectText();
                else{
                    Toast.makeText(ScanActivity.this, "Please Add Image or Capture Text!"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
        _confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(type == 3) // 3 is request_question;
                    intent.putExtra("quest", _textReturn);
                else if(type == 115) //115 is request_hint
                    intent.putExtra("hint", _textReturn);
                else
                    intent.putExtra("answer", _textReturn);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }



    private void receiveIntent() {
        Intent intent = getIntent();
        type = intent.getIntExtra("quest", 0);
        if(type == 0)
        {
            type = intent.getIntExtra("answer", 0);
            if(type == 0)
                type = intent.getIntExtra("hint", 0);
        }
    }
    private void detectText() {
        TextRecognizer textRecognizer = TextRecognition.getClient();
        InputImage imageInput = InputImage.fromBitmap(imageBitmap,0);


        Task<Text> result =
                textRecognizer.process(imageInput)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                // Task completed successfully
                                // ...
                                displayText(visionText);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        Toast.makeText(ScanActivity.this, "Error " + e, Toast.LENGTH_SHORT).show();
                                        Log.d("Error ", e.getMessage());
                                    }
                                });

    }
    private void displayText(Text text)
    {
        List<Text.TextBlock> blockList = text.getTextBlocks();
        if(blockList.isEmpty())
        {
            Toast.makeText(ScanActivity.this, "Cannot Recognize Text", Toast.LENGTH_SHORT).show();
        }
        else{
            for(Text.TextBlock block : text.getTextBlocks())
            {
                String result = block.getText();

                result = result.replace("\n", " ").replace("\r", "");
                _textReturn = _textReturn + result;
            }
            _textView.setText(_textReturn);
        }
    }
    private void takePhoto() {
        _capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        _gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageFromGallery();
            }
        });
    }

    private void addImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pick Image"), GALLERY_REQUEST_CODE_SCAN);

    }

    private void initComponents() {
        _capture = findViewById(R.id.capture);
        _extract = findViewById(R.id.extract_text);
        _imageView = findViewById(R.id.image_scan);
        _textView = findViewById(R.id.display_extract_text);
        _textView.setMovementMethod(new ScrollingMovementMethod());
        _gallery = findViewById(R.id.from_gallery);
        _confirm = findViewById(R.id.confirm);

    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                _imageView.setImageBitmap(imageBitmap);
            }
        }
        if(requestCode == GALLERY_REQUEST_CODE_SCAN)
        {
            if(resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _imageView.setImageBitmap(imageBitmap);
            }
        }

    }
}
