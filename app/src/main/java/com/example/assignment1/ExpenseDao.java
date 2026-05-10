package com.example.assignment1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("DELETE FROM expense_table")
    void deleteAll();

    @Query("SELECT expense_table.id, expense_table.title, expense_table.amount, expense_table.date, " +
            "expense_table.categoryId, category_table.name AS categoryName " +
            "FROM expense_table INNER JOIN category_table ON expense_table.categoryId = category_table.id " +
            "ORDER BY expense_table.id DESC")
    LiveData<List<ExpenseWithCategory>> getAllExpensesWithCategory();
}
