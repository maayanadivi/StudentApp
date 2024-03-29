package com.example.maayanalice.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import kotlin.ReplaceWith;

@Dao
public interface StudentDao {
    @Query("select * from Student")
    List<Student> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Student WHERE id=:id")
    Student getStudentById(String id);
}
