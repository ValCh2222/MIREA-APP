package com.mirea.chubuka_v_a.mireaapp_android;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mirea.chubuka_v_a.mireaapp_android.ui.History.History;
import com.mirea.chubuka_v_a.mireaapp_android.ui.History.HistoryDao;


@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}

