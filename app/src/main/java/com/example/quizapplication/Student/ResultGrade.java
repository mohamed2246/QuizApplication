package com.example.quizapplication.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapplication.R;

public class ResultGrade extends AppCompatActivity {
    TextView name , grade ;
    Button btn;
    String tname;
    int tgrade;
    int ttotalgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_grade);
        name = findViewById(R.id.studentNameResultGrde);
        grade = findViewById(R.id.studentGardeResultGrde);
        btn = findViewById(R.id.btn_studentGardeResultGrde);


        tname = getIntent().getExtras().getString("name");
        tgrade =getIntent().getExtras().getInt("grade");
        ttotalgrade =getIntent().getExtras().getInt("totalgrade");


        name.setText(tname);
        grade.setText( ttotalgrade + " / " + tgrade);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultGrade.this,StudentRegister.class);
                startActivity(intent);
                finish();
            }
        });



    }
}