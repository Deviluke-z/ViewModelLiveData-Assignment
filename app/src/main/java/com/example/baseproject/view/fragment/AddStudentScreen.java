package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.baseproject.databinding.FragmentAddStudentScreenBinding;
import com.example.baseproject.viewmodel.StudentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddStudentScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStudentScreen extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private FragmentAddStudentScreenBinding mBinding;
  private StudentViewModel studentViewModel;

  public AddStudentScreen() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment AddStudentScreen.
   */
  // TODO: Rename and change types and number of parameters
  public static AddStudentScreen newInstance(String param1, String param2) {
    AddStudentScreen fragment = new AddStudentScreen();
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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBinding = FragmentAddStudentScreenBinding.inflate(inflater, container, false);
    studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
    mBinding.setViewmodel(studentViewModel);
    mBinding.setLifecycleOwner(this);
    setRegisterButton();
    setCancelButton();

    return mBinding.getRoot();
  }

  private void setRegisterButton() {
    mBinding.btnRegister.setOnClickListener(v -> {
      studentViewModel.addStudent();
      Navigation.findNavController(mBinding.getRoot()).popBackStack();
    });
  }

  private void setCancelButton() {
    mBinding.btnCancel.setOnClickListener(
        // go back
        v -> Navigation.findNavController(mBinding.getRoot()).navigateUp()
    );
  }


}