package com.example.selfdevelopmentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb_addNew;
    RecyclerView rv_task;

    AdapterTask adapterTask;
    ArrayList<Task> taskArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskDB.init(this);

        fb_addNew=findViewById(R.id.fb_addNew);
        rv_task=findViewById(R.id.rv_task);

        taskArrayList=new ArrayList<>();
        rv_task.setLayoutManager(new LinearLayoutManager(this));
        adapterTask = new AdapterTask(taskArrayList, this);
        rv_task.setAdapter(adapterTask);

        adapterTask.replaceArrayList(TaskDB.getAllData());


        fb_addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddTaskActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterTask.replaceArrayList(TaskDB.getAllData());
    }
}
