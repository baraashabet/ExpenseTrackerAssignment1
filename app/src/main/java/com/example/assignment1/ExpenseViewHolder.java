package com.example.assignment1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;
    private final TextView textViewDetails;
    private final TextView textViewAmount;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewDetails = itemView.findViewById(R.id.textViewDetails);
        textViewAmount = itemView.findViewById(R.id.textViewAmount);
    }

    @SuppressLint("SetTextI18n")
    public void bind(String title, String category, double amount, String date) {
        textViewTitle.setText(title);
        textViewDetails.setText(category + " • " + date);
        textViewAmount.setText(String.format(Locale.US, "%.2f", amount));
    }

    static ExpenseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ExpenseViewHolder(view);
    }
}
