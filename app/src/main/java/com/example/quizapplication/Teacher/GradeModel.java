package com.example.quizapplication.Teacher;

public class GradeModel {
    String StudentPhone;
    String nameStudent;
    int gradStudent;
    long TotalGrade;


    public GradeModel() {
    }

    public GradeModel(String nameStudent, int gradStudent, long totalGrade, String studentPhone) {
        this.nameStudent = nameStudent;
        this.gradStudent = gradStudent;
        TotalGrade = totalGrade;
        StudentPhone = studentPhone;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public int getGradStudent() {
        return gradStudent;
    }

    public void setGradStudent(int gradStudent) {
        this.gradStudent = gradStudent;
    }

    public long getTotalGrade() {
        return TotalGrade;
    }

    public void setTotalGrade(long totalGrade) {
        TotalGrade = totalGrade;
    }

    public String getStudentPhone() {
        return StudentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        StudentPhone = studentPhone;
    }
}
