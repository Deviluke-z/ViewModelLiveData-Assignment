package com.example.baseproject.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemLayoutBinding;
import com.example.baseproject.room_database.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

  private LayoutInflater layoutInflater;
  private List<StudentModel> mListStudent;
  private Context mContext;

  private AdapterListener adapterListener;

  public StudentAdapter(Context mContext, AdapterListener adapterListener) {
    this.mContext = mContext;
    this.adapterListener = adapterListener;
    mListStudent = new ArrayList<>();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    ItemLayoutBinding mBinding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.item_layout,
        parent,
        false
    );
    return new ViewHolder(mBinding);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    StudentModel studentModel = mListStudent.get(position);
    holder.mBinding.tvStudentName.setText(studentModel.getName());
    holder.mBinding.tvStudentAge.setText("Age: " + studentModel.getAge());
    holder.itemView.setOnClickListener( v ->
        adapterListener.onClickItem(studentModel)
    );
    holder.mBinding.ivDelete.setOnClickListener( v -> {
      adapterListener.deleteItem(studentModel, position);
    });
  }

  @Override
  public int getItemCount() {
    if (mListStudent == null) {
      return 0;
    }
    return mListStudent.size();
  }

  public void setListStudent(List<StudentModel> mListStudent) {
    this.mListStudent = mListStudent;
    notifyDataSetChanged();
  }

  public interface AdapterListener {
    void onClickItem(StudentModel studentModel);
    void deleteItem(StudentModel studentModel, int position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ItemLayoutBinding mBinding;

    public ViewHolder(@NonNull ItemLayoutBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
