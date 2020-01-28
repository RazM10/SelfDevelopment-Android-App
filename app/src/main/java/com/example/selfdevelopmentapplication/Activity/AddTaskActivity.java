package com.example.selfdevelopmentapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selfdevelopmentapplication.GlobalItems.StringArrays;
import com.example.selfdevelopmentapplication.Helper.SpinnerHelper;
import com.example.selfdevelopmentapplication.R;
import com.example.selfdevelopmentapplication.Model.Task;
import com.example.selfdevelopmentapplication.DB.TaskDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    Spinner sp_category, sp_character, sp_situation, sp_priority, sp_complete;
    TextView tv_categoryLabel, tv_dateLabel, tv_date;
    EditText et_date, et_output, et_priority, et_duration, et_complete, et_description, et_solution;
    Button btn_show, btn_save;

    SpinnerHelper spinnerHelper = new SpinnerHelper();
    String TAG = "ListenerPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

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

        spinnerHelper.setSpinnerAdapter(this, StringArrays.category, sp_category);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.character, sp_character);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.situation, sp_situation);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.priority, sp_priority);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.complete, sp_complete);
    }

    private void listener() {
        spinnerHelper.onItemSelectedListener(sp_category, 1);
        spinnerHelper.onItemSelectedListener(sp_character, 2);
        spinnerHelper.onItemSelectedListener(sp_situation, 3);
        spinnerHelper.onItemSelectedListener(sp_priority, 4);
        spinnerHelper.onItemSelectedListener(sp_complete, 5);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTaskActivity.this, "Position is: " + spinnerHelper.getPositionCategory()
                                + " " + spinnerHelper.getPositionSituation() + " " + spinnerHelper.getPositionCharacter() + " " +
                                spinnerHelper.getPositionPriority() + " " + spinnerHelper.getPositionComplete()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveData(View view) {
        Task task = getFieldsValues();
        boolean result = TaskDB.addData(task);
        Toast.makeText(this, "Save: " + result, Toast.LENGTH_SHORT).show();
        finish();
    }

    public Task getFieldsValues() {
        Task task = new Task();
        task.setCategory(spinnerHelper.getPositionCategory());
        task.setDate(tv_date.getText().toString());
        task.setSituation(spinnerHelper.getPositionSituation());
        task.setCharacter(spinnerHelper.getPositionCharacter());
        task.setOutput(et_output.getText().toString());
        task.setPriority(spinnerHelper.getPositionPriority());
        task.setDuration(et_duration.getText().toString());
        task.setComplete(spinnerHelper.getPositionComplete());
        task.setDescription(et_description.getText().toString());
        task.setSolution(et_solution.getText().toString());

        return task;
    }
}

