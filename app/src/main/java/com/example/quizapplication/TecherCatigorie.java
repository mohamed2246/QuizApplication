package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizapplication.Teacher.TeacherAuthentication;
import com.example.quizapplication.Teacher.activeNow;

public class TecherCatigorie extends AppCompatActivity {
    Button btnActiveStudent , btnShowAllUserGrads , btnCreateExam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techer_catigorie);
        btnActiveStudent = findViewById(R.id.currentStudentconnectTheExam);
        btnShowAllUserGrads = findViewById(R.id.btn_Students_grads);
        btnCreateExam = findViewById(R.id.btnCreateAnExam);

        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TecherCatigorie.this , TeacherAuthentication.class);
                startActivity(intent);

            }
        });

        btnShowAllUserGrads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TecherCatigorie.this , GradeActivity.class);
                startActivity(intent);
            }
        });

        btnActiveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TecherCatigorie.this , activeNow.class);
                startActivity(intent);
            }
        });



    }
}