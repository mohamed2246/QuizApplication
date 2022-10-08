package com.example.quizapplication.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.GradeActivity;
import com.example.quizapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.rpc.Code;

public class activeNow extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText EdtcodeExam;
    Button btnsearchActive;
    String CodeExam;
    boolean isExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_now);

        DatabaseReference Gradelist_ref = FirebaseDatabase.getInstance().getReference("Quiz").child("StudentGrads");
        DatabaseReference Activelist_ref = FirebaseDatabase.getInstance().getReference("Quiz").child("Student");
        EdtcodeExam = findViewById(R.id.edtactiveCode);
        recyclerView = findViewById(R.id.cart_listActive);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        btnsearchActive = findViewById(R.id.btn_searchActive);
        btnsearchActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeExam = EdtcodeExam.getText().toString();
                Activelist_ref.child(CodeExam).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean check = snapshot.exists();
                        if (check) {

                            FirebaseRecyclerOptions<GradeModel> options = new FirebaseRecyclerOptions.Builder<GradeModel>()
                                    .setQuery(Activelist_ref.child(CodeExam), GradeModel.class).build();

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
                            Toast.makeText(activeNow.this, "كود الامتحان هذا غير صحيح من فضلك تأكد من الكود الصحيح !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(activeNow.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}