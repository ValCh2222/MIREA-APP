package com.mirea.chubuka_v_a.mireaapp_android.ui.History;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}
