package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExpenseViewModel expenseViewModel;
    public static final int NEW_EXPENSE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ExpenseListAdapter adapter = new ExpenseListAdapter(new ExpenseListAdapter.ExpenseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        expenseViewModel.getAllExpenses().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fb);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewExpenseActivity.class);
            startActivityForResult(intent, NEW_EXPENSE_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_EXPENSE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(NewExpenseActivity.EXTRA_TITLE);
            String amountText = data.getStringExtra(NewExpenseActivity.EXTRA_AMOUNT);
            String category = data.getStringExtra(NewExpenseActivity.EXTRA_CATEGORY);
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());

            double amount = Double.parseDouble(amountText);
            expenseViewModel.insert(title, amount, date, category);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}
