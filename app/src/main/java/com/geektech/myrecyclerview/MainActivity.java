package com.geektech.myrecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.ItemClickListener {

    private static final String TAG = "tag";
    public RecyclerView recyclerView;
    public List<Title> list;
    public MainAdapter adapter;
    Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        for (int i = 0; i <10 ; i++) {
//            list.add(new Title("Kutman","Akunov",26,24));
//        }
        adapter = new MainAdapter(list, this);
        adapter.setOnclickListener(this);
       // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        btnOpen = findViewById(R.id.btnOpen);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityDataPerson.class);
                startActivityForResult(intent, ActivityDataPerson.REQUEST_CODE);
            }
        });

    }

    @Override
    public void onItemClick(Title title, int position) {
        Intent intent = new Intent(MainActivity.this, ActivityDataPerson.class);
        intent.putExtra("changeResult", title);
        startActivityForResult(intent, 101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ActivityDataPerson.REQUEST_CODE && resultCode == RESULT_OK) {

            Title title = (Title) data.getSerializableExtra(ActivityDataPerson.KEY);
            adapter.addApplication(title);
        } else {
           if (requestCode ==101  && resultCode == RESULT_OK) {
                Title title = (Title) data.getSerializableExtra(ActivityDataPerson.KEY);
                adapter.list.clear();
                adapter.addApplication(title);
            }

        }
    }

}