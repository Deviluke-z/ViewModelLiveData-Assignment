package com.example.baseproject.room_database.repo;

import android.app.Application;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.baseproject.room_database.StudentDatabase;
import com.example.baseproject.room_database.dao.StudentDAO;
import com.example.baseproject.room_database.model.StudentModel;

import java.util.List;
import android.os.Handler;

// medium between room database and view model
public class StudentRepo {

  private StudentDAO mStudentDAO;

  // handler
  private Handler mHandler;
  private HandlerThread mHandlerThread;

  public StudentRepo(Application application) {
    StudentDatabase studentDatabase = StudentDatabase.getInstance(application);
    mStudentDAO = studentDatabase.mStudentDAO();

    mHandlerThread = new HandlerThread("new thread");
    mHandlerThread.start();
    mHandler = new Handler(mHandlerThread.getLooper());
  }

  public LiveData<List<StudentModel>> queryStudent() {
    return mStudentDAO.queryAllStudent();
  }

  public void addStudent(StudentModel studentModel) {
    mHandler.post(() -> mStudentDAO.AddStudent(studentModel));
  }

  public void deleteStudent(StudentModel studentModel) {
    mHandler.post(() -> mStudentDAO.DeleteStudent(studentModel));
  }
}
