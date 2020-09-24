package com.geektech.myrecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityDataPerson extends AppCompatActivity {


    public static int REQUEST_CODE = 100;
    public static String KEY = "key";

    private EditText etName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etGroup;
    private ImageView imageView;
    private Uri imageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_person);

        init();
    }

    private void init() {
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etGroup = findViewById(R.id.etGroup);
        etAge = findViewById(R.id.etAge);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Title title = (Title) intent.getSerializableExtra("changeResult");
            if (title != null) {
                etName.setText(title.name);
                etLastName.setText(title.lastName);
                etAge.setText(title.age);
                etGroup.setText(title.group);
                imageView.setImageURI(Uri.parse(title.imageView));
            }

        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose image"), 200);

    }


    public void sendResult(View view) {

        String titleName = etName.getText().toString();
        String titleLastName = etLastName.getText().toString();
        String titleAge = etAge.getText().toString();
        String titleGroup = etGroup.getText().toString();
        String image = imageDate.toString();

        Intent intent = new Intent();
        Title title = new Title();
        title.setName(titleName);
        title.setLastName(titleLastName);
        title.setAge(titleAge);
        title.setGroup(titleGroup);
        title.setImageView(image);
        intent.putExtra(KEY, title);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {

            if (data != null) {
                imageDate = data.getData();
                imageView.setImageURI(imageDate);
            }
        }
    }
}