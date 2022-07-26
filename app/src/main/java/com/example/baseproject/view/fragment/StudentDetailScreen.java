package com.example.baseproject.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentStudentDetailScreenBinding;
import com.example.baseproject.room_database.model.StudentModel;
import com.example.baseproject.viewmodel.StudentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentDetailScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentDetailScreen extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public StudentDetailScreen() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment StudentDetailScreen.
   */
  // TODO: Rename and change types and number of parameters
  public static StudentDetailScreen newInstance(String param1, String param2) {
    StudentDetailScreen fragment = new StudentDetailScreen();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  private FragmentStudentDetailScreenBinding mBinding;
  private StudentViewModel studentViewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBinding = FragmentStudentDetailScreenBinding.inflate(inflater, container, false);
    studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
    studentViewModel.getCurrentStudent().observe(requireActivity(), new Observer<StudentModel>() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onChanged(StudentModel studentModel) {
        mBinding.tvStudentName.setText(studentModel.getName());
        mBinding.tvStudentAge.setText("Age: " + studentModel.getAge());
      }
    });

    setBackButton();

    return mBinding.getRoot();
  }

  private void setBackButton() {
    mBinding.btnBack.setOnClickListener( v ->
        Navigation.findNavController(mBinding.getRoot()).popBackStack()
    );
  }
}