package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapplication.Teacher.TeacherAuthentication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PasswordActivity extends AppCompatActivity {

    EditText editePassword ;
    Button btn_enterThePassword;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        databaseReference = FirebaseDatabase.getInstance().getReference("Quiz");
        editePassword = findViewById(R.id.password);
        btn_enterThePassword = findViewById(R.id.btn_EnterPassword);


        btn_enterThePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = editePassword.getText().toString();
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(PasswordActivity.this, "من فضلك ادخل كلمة !", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("Password").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String RealPassword = snapshot.child("TecherPassword").getValue().toString();
                                if (RealPassword.equals(pass)){
                                    Intent intent = new Intent(PasswordActivity.this , TecherCatigorie.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(PasswordActivity.this, "من فضلك ادخل كلمة مرور صحيحة !", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(PasswordActivity.this, "خطأ في الشبكة ! تاكد من الاتصال بالشبكة !", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(PasswordActivity.this, "خطأ في الشبكة ! تاكد من الاتصال بالشبكة !", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });



    }
}