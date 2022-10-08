package com.example.quizapplication.Teacher;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;

public class GradeViewModel extends RecyclerView.ViewHolder {
    public TextView studentName;
    public TextView studentNum;
    public TextView studentGrade;

    public GradeViewModel(@NonNull View itemView) {
        super(itemView);
        studentName =itemView.findViewById(R.id.stuName);
        studentNum =itemView.findViewById(R.id.stuNum);
        studentGrade =itemView.findViewById(R.id.stuGrade);
    }
}
