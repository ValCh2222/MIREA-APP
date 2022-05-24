package com.mirea.chubuka_v_a.mireaapp_android.ui.History;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String story;

    public long getId(){
        return this.id;
    }

    public String getStory(){
        return this.story;
    }
}