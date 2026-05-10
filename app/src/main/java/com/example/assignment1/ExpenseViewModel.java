package com.example.assignment1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private final ExpenseRepository repository;
    private final LiveData<List<ExpenseWithCategory>> allExpenses;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();
    }

    LiveData<List<ExpenseWithCategory>> getAllExpenses() {
        return allExpenses;
    }

    public void insert(String title, double amount, String date, String categoryName) {
        repository.insert(title, amount, date, categoryName);
    }
}
