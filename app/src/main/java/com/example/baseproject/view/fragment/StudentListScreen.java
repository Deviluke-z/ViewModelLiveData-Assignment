package com.example.baseproject.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAddStudentScreenBinding;
import com.example.baseproject.databinding.FragmentStudentListScreenBinding;
import com.example.baseproject.room_database.model.StudentModel;
import com.example.baseproject.view.adapter.StudentAdapter;
import com.example.baseproject.viewmodel.StudentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentListScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentListScreen extends Fragment implements StudentAdapter.AdapterListener {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public StudentListScreen() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment StudentListScreen.
   */
  // TODO: Rename and change types and number of parameters
  public static StudentListScreen newInstance(String param1, String param2) {
    StudentListScreen fragment = new StudentListScreen();
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

  private FragmentStudentListScreenBinding mBinding;
  private StudentViewModel studentViewModel;
  private StudentAdapter studentAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBinding = FragmentStudentListScreenBinding.inflate(inflater, container, false);
    studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
    initRecycleView();
    setAddButton();
    setObserver();
    
    return mBinding.getRoot();
  }

  private void setObserver() {
    studentViewModel.queryAllStudent().observe(requireActivity(), studentModels ->
        studentAdapter.setListStudent(studentViewModel.queryAllStudent().getValue()));
  }

  private void setAddButton() {
    studentViewModel.getNewStudent().setValue(new StudentModel());
    mBinding.ibAdd.setOnClickListener(v ->
        Navigation
            .findNavController(mBinding.getRoot())
            .navigate(R.id.action_studentListScreen_to_addStudentScreen)
    );
  }


  private void initRecycleView() {
    studentAdapter = new StudentAdapter(requireActivity(), this);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
        requireActivity(),
        LinearLayoutManager.VERTICAL,
        false
    );
    mBinding.recycleView.setLayoutManager(linearLayoutManager);
    mBinding.recycleView.setAdapter(studentAdapter);
  }

  @Override
  public void onClickItem(StudentModel studentModel) {
    studentViewModel.setCurrentStudent(studentModel);
    Navigation
        .findNavController(mBinding.getRoot())
        .navigate(R.id.action_studentListScreen_to_studentDetailScreen);
  }

  @Override
  public void deleteItem(StudentModel studentModel, int position) {
    studentViewModel.setCurrentStudent(studentModel);
    studentViewModel.deleteStudent(position);
  }
}