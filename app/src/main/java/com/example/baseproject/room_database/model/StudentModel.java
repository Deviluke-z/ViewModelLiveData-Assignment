package com.example.baseproject.room_database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentModel {

  @PrimaryKey(autoGenerate = true)
  private int mId;

  @ColumnInfo(name = "name_column")
  private String mName;

  @ColumnInfo(name = "age_column")
  private String mAge;

  public StudentModel() {
  }

  public StudentModel(int mId, String mName, String mAge) {
    this.mId = mId;
    this.mName = mName;
    this.mAge = mAge;
  }

  public String getName() {
    return mName;
  }

  public void setName(String mName) {
    this.mName = mName;
  }

  public String getAge() {
    return mAge;
  }

  public void setAge(String mAge) {
    this.mAge = mAge;
  }

  public int getId() {
    return mId;
  }

  public void setId(int mId) {
    this.mId = mId;
  }

  @Override
  public String toString() {
    return "StudentModel{" +
        "mId=" + mId +
        ", mName='" + mName + '\'' +
        ", mAge='" + mAge + '\'' +
        '}';
  }
}
