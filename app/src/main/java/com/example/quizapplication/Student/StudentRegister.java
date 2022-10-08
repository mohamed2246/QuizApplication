package com.example.quizapplication.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.example.quizapplication.Teacher.TeacherAuthentication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StudentRegister extends AppCompatActivity {

    EditText studentName, studentNum, exam_Code;
    Button enterTheExam;
    long millisInput;
    int active = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        DatabaseReference stuRef = FirebaseDatabase.getInstance().getReference("Quiz");
        DatabaseReference TechRef = FirebaseDatabase.getInstance().getReference("Quiz");
        intiate();


        enterTheExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameS = studentName.getText().toString();
                String numS = studentNum.getText().toString();
                String examCodeS = exam_Code.getText().toString();

                if (TextUtils.isEmpty(nameS)) {
                    Toast.makeText(StudentRegister.this, "من فضلك ادخل اسم الطالب ...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(numS)) {
                    Toast.makeText(StudentRegister.this, "من فضلك ادخل رقم هاتف الطالب ...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(examCodeS)) {
                    Toast.makeText(StudentRegister.this, "من فضلك ادخل كود الامتحان ...", Toast.LENGTH_SHORT).show();
                } else {

                    TechRef.child("Exams").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                boolean CodeEXAM = snapshot.child(examCodeS).exists();
                                if (CodeEXAM) {


                                    TechRef.child("Exams").child(examCodeS).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String duration = snapshot.child("longDuration").getValue().toString();
                                            millisInput = Long.parseLong(duration) ;
                                            ExamQuestionsStudent.stat = millisInput;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });



                                    //student enter the correct code
                                    Map<String, Object> StudMap = new HashMap<>();
                                    StudMap.put("nameStudent", nameS);
                                    StudMap.put("StudentPhone", numS);
                                    StudMap.put("ExamCode", examCodeS);
                                    StudMap.put("active",active);


                                    stuRef.child("Student").child(examCodeS).child(nameS).setValue(StudMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(StudentRegister.this, "تم اضافة الطالب", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(StudentRegister.this,ExamQuestionsStudent.class);
                                                intent.putExtra("Sname",nameS);
                                                intent.putExtra("ExamCode" , examCodeS);
                                                intent.putExtra("StudentPhone" , numS);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(StudentRegister.this, "حدث خطئ في اضافة الطالب", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                                }else {
                                    Toast.makeText(StudentRegister.this, "كود هذا الامتحان غير صحيح!", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(StudentRegister.this, "لا يوجد اي امتحانات", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(StudentRegister.this, "errrrrrrorrrrrrrrrrrrrrrrrrrror" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });





                }
            }
        });

    }

    private void intiate() {
        studentName = findViewById(R.id.student_name);
        studentNum = findViewById(R.id.student_num);
        exam_Code = findViewById(R.id.stuExamCode);
        enterTheExam = findViewById(R.id.btn_stu_EnterExam);
    }
}