package com.example.selfdevelopmentapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdevelopmentapplication.Adapter.AdapterTask;
import com.example.selfdevelopmentapplication.R;
import com.example.selfdevelopmentapplication.Model.Task;
import com.example.selfdevelopmentapplication.DB.TaskDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb_addNew;
    RecyclerView rv_task;

    AdapterTask adapterTask;
    ArrayList<Task> taskArrayList;

    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskDB.init(this);
        context=MainActivity.this;

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
                startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            }
        });
    }

    public void deleteSelectedTask(int id){
        boolean result=TaskDB.deleteRow(id);
        Toast.makeText(context, "Delete: "+result, Toast.LENGTH_SHORT).show();
        onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapterTask.replaceArrayList(TaskDB.getAllData());
    }
}
