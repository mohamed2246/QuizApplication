package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.Teacher.GradeModel;
import com.example.quizapplication.Teacher.GradeViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;

public class GradeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText EdtcodeExam;
    Button btnsearchgrade;
    String CodeExam;
    String TextPDF = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ActivityCompat.requestPermissions(GradeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        DatabaseReference Gradelist_ref = FirebaseDatabase.getInstance().getReference("Quiz").child("StudentGrads");
        EdtcodeExam = findViewById(R.id.edtgradeCode);
        recyclerView = findViewById(R.id.cart_list1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        btnsearchgrade = findViewById(R.id.btn_searchGrade);


        btnsearchgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextPDF = "";
                CodeExam = EdtcodeExam.getText().toString();
                Gradelist_ref.child(CodeExam).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean check = snapshot.exists();
                        if (check) {

                            FirebaseRecyclerOptions<GradeModel> options = new FirebaseRecyclerOptions.Builder<GradeModel>()
                                    .setQuery(Gradelist_ref.child(CodeExam), GradeModel.class).build();

                            FirebaseRecyclerAdapter<GradeModel, GradeViewModel> adapter = new FirebaseRecyclerAdapter<GradeModel, GradeViewModel>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull GradeViewModel gradeViewModel, int i, @NonNull GradeModel gradeModel) {
                                    String name, gradeS, totalS, numS;
                                    long total;
                                    int grade;
                                    grade = gradeModel.getGradStudent();
                                    total = gradeModel.getTotalGrade();
                                    name = gradeModel.getNameStudent();
                                    numS = gradeModel.getStudentPhone();

                                    gradeS = String.valueOf(grade);
                                    totalS = String.valueOf(total);
                                    gradeViewModel.studentName.setText(name);
                                    gradeViewModel.studentNum.setText(numS);
                                    gradeViewModel.studentGrade.setText(totalS + " / " + gradeS);

                                    TextPDF += "اسم الطالب : " + name + "\n" + "رقم الطالب : " + numS + "\n" + "درجة الطالب : " + totalS + " / " + gradeS + "\n" + "------------------------------------" + "\n";
                                    createMyPDF(TextPDF);
                                }

                                @NonNull
                                @Override
                                public GradeViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgradestudent, parent, false);
                                    GradeViewModel cart_view_holder = new GradeViewModel(view);
                                    return cart_view_holder;
                                }

                            };
                            recyclerView.setAdapter(adapter);
                            adapter.startListening();


                        } else {
                            Toast.makeText(GradeActivity.this, "كود الامتحان هذا غير صحيح من فضلك تأكد من الكود الصحيح !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(GradeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }

    public void createMyPDF(String AllText) {

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        String myString = AllText;
        int x = 10, y = 25;


        for (String line : myString.split("\n")) {
            myPage.getCanvas().drawText(line, x, y, myPaint);
            y += myPaint.descent() - myPaint.ascent();
        }

        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + CodeExam + ".pdf";

        File myFile = new File(myFilePath);
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        myPdfDocument.close();
    }
}