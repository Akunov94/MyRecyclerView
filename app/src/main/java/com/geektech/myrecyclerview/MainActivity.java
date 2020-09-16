package com.geektech.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public List<Title> list;
    public MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MainAdapter(list,this);
        recyclerView.setAdapter(adapter);


            list.add(new Title("Привет " ,"Welcome to Geektech courses,we are from group 24" ));
            list.add(new Title("Пока " ,"Welcome to Geektech courses,we are from group 24" ));
            list.add(new Title("Добрый вечер " ,"Welcome to Geektech courses,we are from group 24" ));

    }
}