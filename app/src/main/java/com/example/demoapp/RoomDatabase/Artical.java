package com.example.demoapp.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "artical")
public class Artical {

    @ColumnInfo (name = "userId")
    private int userId;
    @ColumnInfo (name = "id")
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo (name = "title")
    private String title;
    @ColumnInfo (name = "body")
    private String body;
    @ColumnInfo (name = "fav")
    private String fav;

    public Artical(int userId, int id, String title, String body, String fav) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.fav = fav;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getFav() {
        return fav;
    }
}
