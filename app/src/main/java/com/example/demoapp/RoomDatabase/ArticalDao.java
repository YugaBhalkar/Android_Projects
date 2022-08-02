package com.example.demoapp.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticalDao {

    @Query("Select * from artical")
   LiveData<List<Artical>> getArticals();

    @Query("Select * from artical WHERE fav=:fav")
    LiveData<List<Artical>> getFavArticals(String fav);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticals(Artical artical);

    @Delete
    void deleteArticals(Artical artical);

    @Query("UPDATE artical SET fav=:fav WHERE id=:id")
    void updateArticals(String fav, int id);
}
