package com.example.selfdevelopmentapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.selfdevelopmentapplication.DB.TaskDB;
import com.example.selfdevelopmentapplication.GlobalItems.StringArrays;
import com.example.selfdevelopmentapplication.Model.Task;
import com.example.selfdevelopmentapplication.R;

public class DetailsActivity extends AppCompatActivity {

    TextView tv_category,tv_description,tv_Solution,tv_output,tv_duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_category=findViewById(R.id.tv_category);
        tv_description=findViewById(R.id.tv_description);
        tv_Solution=findViewById(R.id.tv_Solution);
        tv_output=findViewById(R.id.tv_output);
        tv_duration=findViewById(R.id.tv_duration);

        int id = getIntent().getIntExtra("id", 0);
        Task task= TaskDB.searchInColumn(0, String.valueOf(id),1);

        tv_category.setText(StringArrays.category[task.getCategory()]);
        tv_description.setText(task.getDescription());
        tv_Solution.setText(task.getSolution());
        tv_output.setText(task.getOutput());
        tv_duration.setText(task.getDuration());
    }
}
