package com.example.maayanalice.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.maayanalice.MyApplication;
import com.example.maayanalice.MyApplication;
import com.example.maayanalice.model.StudentDao;

@Database(entities = {Student.class}, version = 2)
abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract StudentDao studentDao();
}

public class AppLocalDB {
    static public final AppLocalDbRepository db =
            Room.databaseBuilder(MyApplication.getAppContext(),
                    AppLocalDbRepository.class,
                    "dbFileName.db")
                    .fallbackToDestructiveMigration()
                    .build();
    private AppLocalDB(){}
}

