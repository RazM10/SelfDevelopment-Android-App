package com.example.selfdevelopmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    Spinner sp_category, sp_character, sp_situation, sp_priority, sp_complete;
    TextView tv_categoryLabel, tv_dateLabel, tv_date;
    EditText et_date, et_output, et_priority, et_duration, et_complete, et_description, et_solution;
    Button btn_show, btn_save;

    MainHelper mainHelper = new MainHelper();
    String TAG = "ListenerPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TaskDB.init(this);

        init();
        listener();
    }

    private void init() {
        sp_category = findViewById(R.id.sp_category);
        sp_character = findViewById(R.id.sp_character);
        sp_situation = findViewById(R.id.sp_situation);
        tv_categoryLabel = findViewById(R.id.tv_categoryLabel);
        btn_show = findViewById(R.id.btn_show);
        tv_dateLabel = findViewById(R.id.tv_dateLabel);
        tv_date = findViewById(R.id.tv_date);
        et_output = findViewById(R.id.et_output);
        sp_priority = findViewById(R.id.sp_priority);
        et_duration = findViewById(R.id.et_duration);
        sp_complete = findViewById(R.id.sp_complete);
        et_description = findViewById(R.id.et_description);
        et_solution = findViewById(R.id.et_solution);
        btn_save = findViewById(R.id.btn_save);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        tv_date.setText(formattedDate);

        mainHelper.setSpinnerAdapter(this, CategoryString.category, sp_category);
        mainHelper.setSpinnerAdapter(this, CategoryString.character, sp_character);
        mainHelper.setSpinnerAdapter(this, CategoryString.situation, sp_situation);
        mainHelper.setSpinnerAdapter(this, CategoryString.priority, sp_priority);
        mainHelper.setSpinnerAdapter(this, CategoryString.complete, sp_complete);
    }

    private void listener() {
        mainHelper.onItemSelectedListener(sp_category, 1);
        mainHelper.onItemSelectedListener(sp_character, 2);
        mainHelper.onItemSelectedListener(sp_situation, 3);
        mainHelper.onItemSelectedListener(sp_priority, 4);
        mainHelper.onItemSelectedListener(sp_complete, 5);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTaskActivity.this, "Position is: " + mainHelper.getPositionCategory()
                                + " " + mainHelper.getPositionSituation() + " " + mainHelper.getPositionCharacter() + " " +
                                mainHelper.getPositionPriority() + " " + mainHelper.getPositionComplete()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveData(View view) {
        Task task = getFieldsValues();
        boolean result = TaskDB.addData(task);
        Toast.makeText(this, "Save: " + result, Toast.LENGTH_SHORT).show();
    }

    public Task getFieldsValues() {
        Task task = new Task();
        task.setCategory(mainHelper.getPositionCategory());
        task.setDate(tv_date.getText().toString());
        task.setSituation(mainHelper.getPositionSituation());
        task.setCharacter(mainHelper.getPositionCharacter());
        task.setOutput(et_output.getText().toString());
        task.setPriority(mainHelper.getPositionPriority());
        task.setDuration(et_duration.getText().toString());
        task.setComplete(mainHelper.getPositionComplete());
        task.setDescription(et_description.getText().toString());
        task.setSolution(et_solution.getText().toString());

        return task;
    }
}

