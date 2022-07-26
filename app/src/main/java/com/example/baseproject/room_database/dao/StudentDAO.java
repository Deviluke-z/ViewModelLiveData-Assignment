package com.example.baseproject.room_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.baseproject.room_database.model.StudentModel;

import java.util.List;

@Dao
public interface StudentDAO {

  @Insert
  void AddStudent(StudentModel studentModel);

  @Delete
  void DeleteStudent(StudentModel studentModel);

  @Query(value = "SELECT * from student_table")
  LiveData<List<StudentModel>> queryAllStudent();

}
