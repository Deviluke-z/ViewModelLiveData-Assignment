package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.viewmodel.StudentViewModel;

public class MainActivity extends AppCompatActivity {

  private StudentViewModel studentViewModel;
  private ActivityMainBinding activityMainBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
    activityMainBinding.setViewmodel(studentViewModel);

    // to update newest data
    activityMainBinding.setLifecycleOwner(this);
  }
}