package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizapplication.Student.StudentRegister;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btStu , btTeach;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btStu = findViewById(R.id.idStudent);
        btTeach = findViewById(R.id.idTeacher);
        databaseReference = FirebaseDatabase.getInstance().getReference("Quiz");
        getSupportActionBar().hide();


        btStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this , StudentRegister.class);
                startActivity(intent);
            }
        });

        btTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this , PasswordActivity.class);
                startActivity(intent);
            }
        });




    }
}