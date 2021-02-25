package xyz.ramil.catfact.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import xyz.ramil.catfact.data.model.CatFactModel;
@Dao
interface  CatFactDao {
    @Query("SELECT * FROM CatFactModel")
    LiveData<List<CatFactModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(CatFactModel catFactModel);

    @Delete
    void delete(CatFactModel user);
}
