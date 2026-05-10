package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewExpenseActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.assignment1.TITLE";
    public static final String EXTRA_AMOUNT = "com.example.assignment1.AMOUNT";
    public static final String EXTRA_CATEGORY = "com.example.assignment1.CATEGORY";

    private EditText editTitle;
    private EditText editAmount;
    private EditText editCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        editTitle = findViewById(R.id.edit_title);
        editAmount = findViewById(R.id.edit_amount);
        editCategory = findViewById(R.id.edit_category);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (TextUtils.isEmpty(editTitle.getText())
                    || TextUtils.isEmpty(editAmount.getText())
                    || TextUtils.isEmpty(editCategory.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_TITLE, editTitle.getText().toString());
                replyIntent.putExtra(EXTRA_AMOUNT, editAmount.getText().toString());
                replyIntent.putExtra(EXTRA_CATEGORY, editCategory.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
