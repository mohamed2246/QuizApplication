package com.example.quizapplication.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TeacherAuthentication extends AppCompatActivity {
    EditText teach_name , tech_num , sub_name , exam_id;
    Button btn_create_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_authentication);
        intaite();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Quiz");

        btn_create_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teacherName =teach_name.getText().toString()  ;
                String teacherNum =tech_num.getText().toString() ;
                String subName = sub_name.getText().toString() ;
                String examId = exam_id.getText().toString();

                if(TextUtils.isEmpty(teacherName)){
                    Toast.makeText(TeacherAuthentication.this, "من فضلك ادخل اسم المدرس ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(teacherNum)){
                    Toast.makeText(TeacherAuthentication.this, "من فضلك ادخل رقم هاتف المدرس ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(subName)){
                    Toast.makeText(TeacherAuthentication.this, "من فضلك ادخل اسم المادة ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(examId)){
                    Toast.makeText(TeacherAuthentication.this, "من فضلك ادخل كود الامتحان ...", Toast.LENGTH_SHORT).show();
                }
                else {

                    Map<Object,String> techerMap = new HashMap<>();
                    techerMap.put("teacherName" ,teacherName);
                    techerMap.put("teacherNumber" ,teacherNum);
                    techerMap.put("subjectName" ,subName);
                    techerMap.put("examId" ,examId);

                    databaseReference.child("Teachers").child(examId).setValue(techerMap);
                    Intent intent = new Intent(TeacherAuthentication.this , CreateQuestions.class);
                    intent.putExtra("examId" , examId);
                    intent.putExtra("teacherName" , teacherName);
                    intent.putExtra("subName" , subName);
                    startActivity(intent);
                }




            }
        });




    }

    private void intaite() {

        teach_name = findViewById(R.id.tech_name);
        tech_num = findViewById(R.id.tech_num);
        sub_name = findViewById(R.id.sub_name);
        exam_id = findViewById(R.id.exam_id);
        btn_create_exam = findViewById(R.id.btn_create_exam);
    }
}