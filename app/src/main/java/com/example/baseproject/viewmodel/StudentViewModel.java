package com.example.baseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baseproject.room_database.model.StudentModel;
import com.example.baseproject.room_database.repo.StudentRepo;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

  private StudentRepo mStudentRepo;
  private LiveData<List<StudentModel>> mLiveDataListStudent;

  // mutable live data
  private MutableLiveData<StudentModel> mLiveDataCurrentStudent =
      new MutableLiveData<>(new StudentModel());
  private MutableLiveData<StudentModel> mLiveDataNewStudent =
      new MutableLiveData<>(new StudentModel());

  public StudentViewModel(@NonNull Application application) {
    super(application);

    mStudentRepo = new StudentRepo(application);
    mLiveDataListStudent = mStudentRepo.queryStudent();
  }

  public  MutableLiveData<StudentModel> getCurrentStudent() {
    return mLiveDataCurrentStudent;
  }

  public MutableLiveData<StudentModel> getNewStudent() {
    return mLiveDataNewStudent;
  }

  public void addStudent() {
    mStudentRepo.addStudent(mLiveDataNewStudent.getValue());
    mLiveDataNewStudent.postValue(new StudentModel());
  }

  public void deleteStudent(int position) {
    mStudentRepo.deleteStudent(mLiveDataListStudent.getValue().remove(position));
  }

  public void setCurrentStudent(StudentModel studentModel) {
    mLiveDataCurrentStudent.postValue(studentModel);
  }

  public LiveData<List<StudentModel>> queryAllStudent() {
    return mLiveDataListStudent;
  }
}
