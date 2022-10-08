package com.example.quizapplication.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.example.quizapplication.TecherCatigorie;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateQuestions extends AppCompatActivity {
    EditText questionEdt , Option1,Option2 , Option3 , Option4, correctOption , examDurationEdt , examTimeEdt ,examDate;
    Button addQuetionBtn , CreateExamFinal;
    DatePickerDialog .OnDateSetListener listener;
    int numofquestion = 0;
    long constant =60000;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questions);
        intiate();
        final int[] keyInt = {0};
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final int Houre = calendar.get(Calendar.HOUR_OF_DAY);
        final int Minete = calendar.get(Calendar.MINUTE);
        final int AMPM = calendar.get(Calendar.AM_PM);




        examTimeEdt.setFocusableInTouchMode(false);
        examTimeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimeFragment();


            }
        });






        examDate.setFocusableInTouchMode(false);
        examDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateQuestions.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String Date = day+" / "+month + " / "   + year;
                        examDate.setText(Date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


        String examId = getIntent().getExtras().getString("examId");
        String SubjectName = getIntent().getExtras().getString("subName");
        String teacherName = getIntent().getExtras().getString("teacherName");
        DatabaseReference ExamRef = FirebaseDatabase.getInstance().getReference("Quiz").child("Exams");
        addQuetionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String the_quetion = questionEdt.getText().toString();
                String option1 = Option1.getText().toString();
                String option2 = Option2.getText().toString();
                String option3 = Option3.getText().toString();
                String option4 = Option4.getText().toString();
                String correct_option = correctOption.getText().toString();



                if(TextUtils.isEmpty(the_quetion)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل السؤل ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(option1)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل الاختيار الاول ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(option2)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل الاختيار الثاني ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(option3)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل الاختيار الثالث ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(option4)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل الاختيار الرابع ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(correct_option)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل الاختيار الصحيح ...", Toast.LENGTH_SHORT).show();
                }else {

                    Map<String , Object> question_Add = new HashMap<>();
                    question_Add.put("quetion",the_quetion);
                    question_Add.put("option1",option1);
                    question_Add.put("option2",option2);
                    question_Add.put("option3",option3);
                    question_Add.put("option4",option4);
                    question_Add.put("correct_option",correct_option);
                    question_Add.put("SubjectName",SubjectName);
                    question_Add.put("teacherName",teacherName);
                    question_Add.put("examCode",examId);

                    String key = String.valueOf(keyInt[0]);
                    ExamRef.child(examId).child(key).updateChildren(question_Add);


                    keyInt[0]++;
                    questionEdt.getText().clear();
                    Option1.getText().clear();
                    Option2.getText().clear();
                    Option3.getText().clear();
                    Option4.getText().clear();
                    numofquestion++;
                    correctOption.getText().clear();
                    count++ ;
                    Toast.makeText(CreateQuestions.this, "تم اضافة السؤال بنجاح..", Toast.LENGTH_SHORT).show();

                }




            }
        });

        CreateExamFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long duration =1;
                String examDuration = examDurationEdt.getText().toString();
                if (examDuration.equals("")){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل مدة الامتحان ", Toast.LENGTH_SHORT).show();
                }else {
                     duration = Long.parseLong(examDuration);
                }

                long durationMul = duration * constant;
                String examTime = examTimeEdt.getText().toString();
                String date = examDate.getText().toString();

                 if(TextUtils.isEmpty(examDuration)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل مدة الامتحان ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(examTime)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل وقت الامتحان ...", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(date)){
                    Toast.makeText(CreateQuestions.this, "من فضلك ادخل تاريخ الامتحان ...", Toast.LENGTH_SHORT).show();
                }else {
                     Intent intent = new Intent(CreateQuestions.this , TecherCatigorie.class);
                     if (count ==1){
                         Toast.makeText(CreateQuestions.this, "من فضلك ادخل اكثر من سؤال واحد !", Toast.LENGTH_SHORT).show();
                     }else {

                         Map<String , Object> Exam_Details = new HashMap<>();
                         Exam_Details.put("examDuration",examDuration);
                         Exam_Details.put("examTime",examTime);
                         Exam_Details.put("date",date);
                         Exam_Details.put("longDuration",durationMul);
                         Exam_Details.put("totalNumQ",numofquestion);
                         ExamRef.child(examId).updateChildren(Exam_Details);

                         examDurationEdt.getText().clear();
                         examTimeEdt.getText().clear();
                         examDate.getText().clear();

                         startActivity(intent);
                         finish();

                     }


                 }

            }
        });





    }

    private void intiate() {
        questionEdt = findViewById(R.id.the_quesion);
        Option1 = findViewById(R.id.firstOption);
        Option2 = findViewById(R.id.secondOption);
        Option3 = findViewById(R.id.thirdOption);
        Option4 = findViewById(R.id.fourceOption);
        correctOption = findViewById(R.id.correctOption);
        examDurationEdt = findViewById(R.id.exam_Duration);
        examTimeEdt = findViewById(R.id.exam_time);
        addQuetionBtn = findViewById(R.id.btn_add_question);
        CreateExamFinal = findViewById(R.id.create_final_exam);
        examDate = findViewById(R.id.exam_Date);



    }

    void openTimeFragment() {
        TimePickerDialog mTimePicker;

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(CreateQuestions.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {



                String time = selectedHour + ":" + selectedMinute;

                SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = fmt.parse(time );
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                String formattedTime=fmtOut.format(date);

                examTimeEdt.setText(formattedTime);
            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


}