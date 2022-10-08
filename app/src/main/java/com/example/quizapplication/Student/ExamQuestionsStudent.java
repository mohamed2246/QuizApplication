package com.example.quizapplication.Student;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ExamQuestionsStudent extends AppCompatActivity {
    TextView timeexam , subName , QNum  , TheQuestionText,currentDegree;
    Button btnFirstAnswer , btnSecondAnswer ,btnThirdAnswer , banForceAnswer , btnNextButton;
    int numberOfQuestion = 0;
    int currentNumOfQuestion ;
    public  static long stat = 0;
    String numOfQue = "";
    private static final long START_TIME_IN_MILLIS = 600000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = stat;
    private long mEndTime;
    private CountDownTimer mCountDownTimer;
    DatabaseReference quesRef , stuQRef;
    String codeExam="";
    String studentName = "";
    String studentNum = "";
    int checkButton = 0;
    Drawable d ;
    String correctAnswer;
    int yourGradeNow = 0;
    int variable;
    int QueInt;
    int active = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_questions_student);
        studentName = getIntent().getExtras().getString("Sname");
        codeExam = getIntent().getExtras().getString("ExamCode");

        studentNum = getIntent().getExtras().getString("StudentPhone");
        timeexam = findViewById(R.id.examTimeDiscound);
        QNum = findViewById(R.id.QuestionCurrentNum);
        subName = findViewById(R.id.subjectName);
        TheQuestionText = findViewById(R.id.displayQuestionStu);
        currentDegree = findViewById(R.id.totalGradToNow);
        btnFirstAnswer = findViewById(R.id.firstOptionbutton);
        btnSecondAnswer = findViewById(R.id.secondOptionbutton);
        btnThirdAnswer = findViewById(R.id.thirdOptionbutton);
        banForceAnswer = findViewById(R.id.fourceOptionbutton);
        btnNextButton = findViewById(R.id.btnNextQuestion);
        d = getResources().getDrawable(R.drawable.editecheckbtn);
        startTimer();
        updateCountDownText();

        quesRef = FirebaseDatabase.getInstance().getReference("Quiz");
        stuQRef = FirebaseDatabase.getInstance().getReference("Quiz");;



        showNumOfQuestions();

        showquestion();

        btnFirstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton = 1;
                switch (checkButton){
                    case 1:
                        btnFirstAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;




                    case 2:
                        btnSecondAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 3:
                        btnThirdAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 4:
                        banForceAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        banForceAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    default:
                        Toast.makeText(ExamQuestionsStudent.this, "No Button pressed ", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        btnSecondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton=2;
                switch (checkButton){
                    case 1:
                        btnFirstAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;




                    case 2:
                        btnSecondAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 3:
                        btnThirdAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 4:
                        banForceAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        banForceAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    default:
                        Toast.makeText(ExamQuestionsStudent.this, "No Button pressed ", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        btnThirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton=3;
                switch (checkButton){
                    case 1:
                        btnFirstAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;




                    case 2:
                        btnSecondAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 3:
                        btnThirdAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 4:
                        banForceAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        banForceAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    default:
                        Toast.makeText(ExamQuestionsStudent.this, "No Button pressed ", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        banForceAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton=4;
                switch (checkButton){
                    case 1:
                        btnFirstAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;




                    case 2:
                        btnSecondAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 3:
                        btnThirdAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FF3700B3"));

                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;

                    case 4:
                        banForceAnswer.setBackgroundResource(R.drawable.editetextlayout);
                        banForceAnswer.setTextColor(Color.parseColor("#FF3700B3"));


                        btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                        btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                        btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    default:
                        Toast.makeText(ExamQuestionsStudent.this, "No Button pressed ", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });


        btnNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNextButton.setBackgroundResource(R.drawable.editetextlayout);
                btnNextButton.setTextColor(Color.parseColor("#FF3700B3"));
                Toast.makeText(ExamQuestionsStudent.this, "من فضلك انتظر لحظة !", Toast.LENGTH_LONG).show();



                if (checkButton==1){
                    if (btnFirstAnswer.getText().equals(correctAnswer)){
                        yourGradeNow++;
                        currentDegree.setText(String.valueOf("درجاتك الي الان : " + yourGradeNow));
                    }
                }else if (checkButton==2){

                    if (btnSecondAnswer.getText().equals(correctAnswer)){
                        yourGradeNow++;
                        currentDegree.setText(String.valueOf("درجاتك الي الان : " + yourGradeNow));
                    }

                }else if (checkButton==3){

                    if (btnThirdAnswer.getText().equals(correctAnswer)){
                        yourGradeNow++;
                        currentDegree.setText(String.valueOf("درجاتك الي الان : " + yourGradeNow));
                    }

                }else if (checkButton==4){

                    if (banForceAnswer.getText().equals(correctAnswer)){
                        yourGradeNow++;
                        currentDegree.setText(String.valueOf("درجاتك الي الان : " + yourGradeNow));
                    }

                }

                if (btnNextButton.getText().equals("تسليم الامتحان")){
                    int swap = QueInt +1;
                    Intent intent = new Intent(ExamQuestionsStudent.this,ResultGrade.class);
                    intent.putExtra("totalgrade" , swap);
                    intent.putExtra("grade" , yourGradeNow);
                    intent.putExtra("name" , studentName);
                    Map <Object , Object> StuGradMap = new HashMap();
                    StuGradMap.put("nameStudent" , studentName);
                    StuGradMap.put("gradStudent" ,yourGradeNow );
                    StuGradMap.put("StudentPhone" ,studentNum );
                    StuGradMap.put("totalGrade" ,swap );

                    stuQRef.child("StudentGrads").child(codeExam).child(studentName).setValue(StuGradMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ExamQuestionsStudent.this, "الطالب :  " + studentName + yourGradeNow + " / " + swap, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ExamQuestionsStudent.this, "درجة الطالب لم تسجل ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference stuRef = FirebaseDatabase.getInstance().getReference("Quiz").child("Student");
                    stuRef.child(codeExam).child(studentName).removeValue();

                    startActivity(intent);
                    finish();
                }

                if (QueInt==numberOfQuestion){
                    btnNextButton.setText("تسليم الامتحان");
                }



                btnFirstAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                btnFirstAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                btnSecondAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                btnSecondAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                btnThirdAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                btnThirdAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                banForceAnswer.setBackgroundResource(R.drawable.editecheckbtn);
                banForceAnswer.setTextColor(Color.parseColor("#FFFFFF"));

                checkButton =0;

                showquestion();



            }

        });






    }

    private void showNumOfQuestions() {
        quesRef.child("Exams").child(codeExam).child("totalNumQ").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numOfQue = snapshot.getValue().toString();
                 QueInt =    Integer.parseInt(numOfQue)-1;
                currentNumOfQuestion = numberOfQuestion+1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showquestion() {

        quesRef.child("Exams").child(codeExam).child(String.valueOf(numberOfQuestion)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                variable =numberOfQuestion;
                numberOfQuestion = numberOfQuestion+1;
                if (snapshot.exists()){
                    String subNamet = snapshot.child("teacherName").getValue().toString();
                    String QNumber =String.valueOf(numberOfQuestion);
                    String Qtext = snapshot.child("quetion").getValue().toString();
                    String firstO = snapshot.child("option1").getValue().toString();
                    String secondO = snapshot.child("option2").getValue().toString();
                    String thirdO = snapshot.child("option3").getValue().toString();
                    String fourceO = snapshot.child("option4").getValue().toString();
                    correctAnswer = snapshot.child("correct_option").getValue().toString();


                    subName.setText(subNamet);
                    QNum.setText(QNumber + " / " + numOfQue);
                    TheQuestionText.setText(Qtext);
                    currentDegree.setText(String.valueOf("درجاتك الي الان : " + yourGradeNow ));
                    btnFirstAnswer.setText(firstO);
                    btnSecondAnswer.setText(secondO);
                    btnThirdAnswer.setText(thirdO);
                    banForceAnswer.setText(fourceO);

                }else {
                    Toast.makeText(ExamQuestionsStudent.this, "لا يوجد اسالة آخري ! ", Toast.LENGTH_SHORT).show();
                    btnNextButton.setText("تسليم الامتحان");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ExamQuestionsStudent.this, "Erorr" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }





    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                Toast.makeText(ExamQuestionsStudent.this, "انتهي الوقت .", Toast.LENGTH_SHORT).show();

                int swap = QueInt +1;
                Intent intent = new Intent(ExamQuestionsStudent.this,ResultGrade.class);
                intent.putExtra("totalgrade" , swap);
                intent.putExtra("grade" , yourGradeNow);
                intent.putExtra("name" , studentName);
                Map <Object , Object> StuGradMap = new HashMap();
                StuGradMap.put("nameStudent" , studentName);
                StuGradMap.put("gradStudent" ,yourGradeNow );
                StuGradMap.put("StudentPhone" ,studentNum );

                StuGradMap.put("totalGrade" ,swap );

                stuQRef.child("StudentGrads").child(codeExam).child(studentName).setValue(StuGradMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ExamQuestionsStudent.this, "الطالب :  " + studentName + yourGradeNow + " / " + swap, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ExamQuestionsStudent.this, "درجة الطالب لم تسجل ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                DatabaseReference stuRef = FirebaseDatabase.getInstance().getReference("Quiz").child("Student");
                stuRef.child(codeExam).child(studentName).removeValue();

                startActivity(intent);
                finish();

            }
        }.start();
        mTimerRunning = true;

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timeexam.setText(timeLeftFormatted);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();

        if (mTimerRunning) {
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }
    }
}










































   /* private void myOwnTime() {

        new CountDownTimer(stat, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                timeexam.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                timeexam.setText("done!");
            }
        }.start();

    }*/


