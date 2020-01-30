package com.example.selfdevelopmentapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    EditText et_date, et_output, et_duration, et_description, et_solution;
    Button btn_show, btn_save, btn_update;

    SpinnerHelper spinnerHelper = new SpinnerHelper();
    String TAG = "ListenerPosition";
    Task task;
    int id = -1;
    public static boolean isUpdate = false;

    //calender
    String formattedDate;
    int y, m, d;

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
        tv_dateLabel = findViewById(R.id.tv_dateLabel);
        tv_date = findViewById(R.id.tv_date);
        et_output = findViewById(R.id.et_output);
        sp_priority = findViewById(R.id.sp_priority);
        et_duration = findViewById(R.id.et_duration);
        sp_complete = findViewById(R.id.sp_complete);
        et_description = findViewById(R.id.et_description);
        et_solution = findViewById(R.id.et_solution);
        btn_save = findViewById(R.id.btn_save);
        btn_update = findViewById(R.id.btn_update);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c);
        tv_date.setText(formattedDate);
        y = Integer.parseInt(formattedDate.substring(0, 3));
        m = Integer.parseInt(formattedDate.substring(5, 6));
        d = Integer.parseInt(formattedDate.substring(8, 9));

        spinnerHelper.setSpinnerAdapter(this, StringArrays.category, sp_category);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.character, sp_character);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.situation, sp_situation);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.priority, sp_priority);
        spinnerHelper.setSpinnerAdapter(this, StringArrays.complete, sp_complete);

        //intent
        if (isUpdate == true) {
            isUpdate = false;
            btn_save.setVisibility(View.GONE);
            btn_update.setVisibility(View.VISIBLE);
            task = new Task();
            id = getIntent().getIntExtra("id", 0);
            task.setCategory(getIntent().getIntExtra("category", 0));
            task.setSituation(getIntent().getIntExtra("situation", 0));
            task.setCharacter(getIntent().getIntExtra("character", 0));
            task.setPriority(getIntent().getIntExtra("priority", 0));
            task.setComplete(getIntent().getIntExtra("complete", 0));
            task.setDate(getIntent().getStringExtra("date"));
            task.setOutput(getIntent().getStringExtra("output"));
            task.setDuration(getIntent().getStringExtra("duration"));
            task.setDescription(getIntent().getStringExtra("description"));
            task.setSolution(getIntent().getStringExtra("solution"));

            sp_category.setSelection(task.getCategory());
            sp_situation.setSelection(task.getSituation());
            sp_character.setSelection(task.getCharacter());
            sp_priority.setSelection(task.getPriority());
            sp_complete.setSelection(task.getComplete());

            tv_date.setText(task.getDate());
            et_output.setText(task.getOutput());
            et_duration.setText(task.getDuration());
            et_solution.setText(task.getSolution());
        }
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
                new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int mTemp = month + 1;
                        String mSave = String.valueOf((mTemp > 9) ? mTemp : "0" + mTemp);
                        tv_date.setText("" + year + "-" + mSave + "-" + dayOfMonth);
                    }
                }, 2020, 01, 01).show();

            }
        });

//        btn_show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AddTaskActivity.this, "Position is: " + spinnerHelper.getPositionCategory()
//                                + " " + spinnerHelper.getPositionSituation() + " " + spinnerHelper.getPositionCharacter() + " " +
//                                spinnerHelper.getPositionPriority() + " " + spinnerHelper.getPositionComplete()
//                        , Toast.LENGTH_SHORT).show();
//            }
//        });
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

    public void updateData(View view) {
        Task task = getFieldsValues();
        task.setId(id);
        boolean result = TaskDB.updateData(task);
        Toast.makeText(this, "Update: " + result, Toast.LENGTH_SHORT).show();
        finish();
    }
}

