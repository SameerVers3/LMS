package lms;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends person implements Serializable{
    private String teacher_password;
    private double t_salary;
    ArrayList<Course> CourseList;

    public Teacher(String firstName, String lastName, int age, String gender, String designation, String teacher_password, double t_salary) {
        super(firstName, lastName, age, gender, designation);
        this.teacher_password = teacher_password;
        this.t_salary = t_salary;
        CourseList = new ArrayList<>();
    }

    public double getT_salary() {
        return t_salary;
    }

    public void setT_salary(double t_salary) {
        this.t_salary = t_salary;
    }

    public String getTeacher_name() {
        return firstName;
    }

    public void setTeacher_name(String teacher_name) {
        this.firstName = teacher_name;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }
//    Course[] courses = new Course[3];
//    {   Course m1 = new Course();
//        courses[0] = m1;
//        Course m2 = new Course();
//        courses[1] = m2;
//        Course m3 = new Course();
//        courses[2] = m3;
//    }







}
