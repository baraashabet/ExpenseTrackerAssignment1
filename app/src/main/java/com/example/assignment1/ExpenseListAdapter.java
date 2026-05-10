package com.example.assignment1;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ExpenseListAdapter extends ListAdapter<ExpenseWithCategory, ExpenseViewHolder> {

    public ExpenseListAdapter(@NonNull DiffUtil.ItemCallback<ExpenseWithCategory> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ExpenseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        ExpenseWithCategory current = getItem(position);
        holder.bind(current.title, current.categoryName, current.amount, current.date);
    }

    static class ExpenseDiff extends DiffUtil.ItemCallback<ExpenseWithCategory> {

        @Override
        public boolean areItemsTheSame(@NonNull ExpenseWithCategory oldItem, @NonNull ExpenseWithCategory newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ExpenseWithCategory oldItem, @NonNull ExpenseWithCategory newItem) {
            return oldItem.title.equals(newItem.title)
                    && oldItem.categoryName.equals(newItem.categoryName)
                    && oldItem.amount == newItem.amount
                    && oldItem.date.equals(newItem.date);
        }
    }
}
