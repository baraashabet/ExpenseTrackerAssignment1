package com.example.assignment1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {

    private final CategoryDao categoryDao;
    private final ExpenseDao expenseDao;
    private final LiveData<List<ExpenseWithCategory>> allExpenses;

    ExpenseRepository(Application application) {
        ExpenseRoomDatabase db = ExpenseRoomDatabase.getRoomDatabase(application);
        categoryDao = db.categoryDao();
        expenseDao = db.expenseDao();
        allExpenses = expenseDao.getAllExpensesWithCategory();
    }

    LiveData<List<ExpenseWithCategory>> getAllExpenses() {
        return allExpenses;
    }

    void insert(String title, double amount, String date, String categoryName) {
        ExpenseRoomDatabase.databaseWriteExecutor.execute(() -> {
            String cleanCategoryName = categoryName.trim();
            long categoryId = categoryDao.insert(new Category(cleanCategoryName));

            if (categoryId == -1) {
                categoryId = categoryDao.getCategoryIdByName(cleanCategoryName);
            }

            Expense expense = new Expense(title.trim(), amount, date, (int) categoryId);
            expenseDao.insert(expense);
        });
    }
}
