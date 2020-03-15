package com.example.selfdevelopmentapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.selfdevelopmentapplication.R;

public class TypedTextActivity extends AppCompatActivity {

    EditText editTextTepedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typed_text);

        String text = getIntent().getStringExtra("text");

        editTextTepedText = findViewById(R.id.typed_text_et);
        editTextTepedText.setText(text);
        editTextTepedText.setSelection(text.length());
    }

    public void sendText(View view) {
        String text = String.valueOf(editTextTepedText.getText());
        AddTaskActivity.isReturned = true;
        AddTaskActivity.fullDescriptionText = text;
        finish();
    }
}
