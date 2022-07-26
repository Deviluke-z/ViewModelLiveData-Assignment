package com.example.baseproject.room_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.baseproject.room_database.dao.StudentDAO;
import com.example.baseproject.room_database.model.StudentModel;

@Database(entities = {StudentModel.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

  private static StudentDatabase instance;

  public abstract StudentDAO mStudentDAO();

  public static StudentDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context, StudentDatabase.class, "Student Database").build();
    }
    return instance;
  }

  public StudentDatabase() {
  }
}
