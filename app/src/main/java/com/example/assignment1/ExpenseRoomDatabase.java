package com.example.assignment1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Expense.class}, version = 2, exportSchema = false)
public abstract class ExpenseRoomDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract ExpenseDao expenseDao();

    private static volatile ExpenseRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExpenseRoomDatabase getRoomDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpenseRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ExpenseRoomDatabase.class,
                                    "expense_database"
                            )
//                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() -> {
//                CategoryDao categoryDao = INSTANCE.categoryDao();
//                ExpenseDao expenseDao = INSTANCE.expenseDao();
//
//                expenseDao.deleteAll();
//                categoryDao.deleteAll();
//
//                long foodId = categoryDao.insert(new Category("Food"));
//                long transportId = categoryDao.insert(new Category("Transport"));
//                long studyId = categoryDao.insert(new Category("Study"));
//
//                expenseDao.insert(new Expense("أكل", 25.0, "2026-05-10", (int) foodId));
//                expenseDao.insert(new Expense("سيارة", 10.0, "2026-05-10", (int) transportId));
//                expenseDao.insert(new Expense("دفتر", 6.5, "2026-05-10", (int) studyId));
//            });
//        }
//    };
}
