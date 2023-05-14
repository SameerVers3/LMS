package lms;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.InputMismatchException;

public class Course implements Serializable{
    private String course_name;
    private String course_code = "";
    private int credit_hrs;
    private int feesPerCredit;
    ArrayList<Student> student;
    Attendence attendence;
    private double Mid1_marks=0 , Mid2_marks=0 , Final_marks=0 , Assignments_marks=0 , Quiz_marks=0 , GPA=0;
    private static final long serialVersionUID = -4874729804637393940L;

    //Constructor
    public Course(String courseName, String courseCode, int creditHours, int feesPerCredit) {
        this.course_name = courseName;
        this.course_code = courseCode;
        this.credit_hrs = creditHours;
        this.feesPerCredit = feesPerCredit;
        student = new ArrayList<>();
    }
    // copy Constructor but with a twist
    public Course(Course c, int ch, int a){
        if (ch == 2){
            this.course_name = c.course_name;
            this.course_code = c.course_code;
            this.credit_hrs = a;
            this.feesPerCredit = c.feesPerCredit;
            this.student = c.student;
        } else if (ch == 3){
            this.course_name = c.course_name;
            this.course_code = c.course_code;
            this.credit_hrs = c.credit_hrs;
            this.feesPerCredit = a;
            this.student = c.student;
        } else {
            System.out.println("Error");
        }
    }

    public void addStudent(Student s){
        int check =0;
        for (Course c: s.course){
            if(c.getCourse_code().equalsIgnoreCase(this.getCourse_code())){
                check++;
            }
        }

        if (check ==  0){
            student.add(s);
            System.out.println("Student added in the Course List: ");
        } else {
            System.out.println("Student already enrolled in the Course: ");
        }
    }

    public void removeStudent(Student s){
        int j=0, check=0; // j is index and check is to find if the course is in the list or not
        for (Course i: s.course) {
            if (i.getCourse_code().equalsIgnoreCase(i.getCourse_code())) {
                student.remove(j);
                check++;
                break;
            }
            j++;
        }
        if (check > 0){
            System.out.println("Student removed from the course successfully");
        }
        else {
            System.out.println("Student not found");
        }
    }

    public Course(Course c, int ch, String a) {
        if (ch == 1){
            this.course_name = c.course_name;
            this.course_code = a;
            this.credit_hrs = c.credit_hrs;
            this.feesPerCredit = c.feesPerCredit;
            this.student = c.student;
        } else {
            System.out.println("Error");
        }
    }

    public Course(){}

    //Setters
    public void setCourse_name(String name) {
        if (!isString(name)) {
            throw new InputMismatchException("Invalid input. Only string data is allowed.");
        }
        this.course_name = name;
    }
    public void setCourse_code(String course_code) {
        if (!isString(course_code)) {
            throw new InputMismatchException("Invalid input. Only string data is allowed.");
        }
        this.course_code = course_code;
    }
    public void setCredit_hrs(int credit_hrs) {
        this.credit_hrs = credit_hrs;
    }

    public void setFeesPerCredit(int feesPerCredit) {
        this.feesPerCredit = feesPerCredit;
    }

    //GETTERS

    public int getCredit_hrs() {
        return credit_hrs;
    }
    public String getCourse_code() {
        return course_code;
    }
    public String getCourse_name() {
        return course_name;
    }


    public int getFeesPerCredit() {
        return feesPerCredit;
    }
    private static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public void setCredit_hours(int newCreditHours) {
    }

    public void setFees_per_credit(int newFeesPerCredit) {
    }
}
