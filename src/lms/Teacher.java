package lms;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends person implements Serializable{
    private String teacher_password;
    private double t_salary;
    ArrayList<Course> course;
    private static final long serialVersionUID = 1694287355572388542L;


    public Teacher(String firstName, String lastName, int age, String gender, String designation, String teacher_password, double t_salary) {
        super(firstName, lastName, age, gender, designation);
        this.teacher_password = teacher_password;
        this.t_salary = t_salary;
        course = new ArrayList<>();
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

    public void assignCourse(Course crs){
        int found=0;
        for (Course c: course){
            if (c.getCourse_code().equals(crs.getCourse_code())){
                found++;
            }
        }
        if (found==0){
            this.course.add(crs);
            System.out.println("Course assignment sucessfully");
        } else{
            System.out.println("Already Assigned");
        }
    }

    public void markAttendence(ArrayList<Student> students, Course crs, Attendence attend){
        Scanner sc = new Scanner(System.in);
        int found=0;
        for (Course c: course){
            if (c.getCourse_code().equals(crs.getCourse_code())){
                found++;
            }
        }
        if (found==0){
            System.out.println("Teacher is not assigned to the course");
        } else{
            for (Student st: students){
                if (st.findCourse(crs)){
                    int loop=0;
                    boolean mark=true;
                    while(loop!=1 && loop != 2) {
                        System.out.println("Name: " + st.getFirstName() + "\tID: " + st.getStudentID());
                        System.out.println("Present or Absent\n1. present\n2. absent\n\t->:");
                        loop = sc.nextInt();
                        switch(loop){
                            case(1) ->
                            {
                                mark=true;
                            }
                            case(2) ->
                            {
                                mark=false;
                            }
                            default ->
                            {
                                System.out.println("Invalid input!");
                            }
                        }
                    }

                    attend.isPresent(st, crs, mark);
                }
            }
        }
    }

    public void viewStudentList(ArrayList<Student> student,  Course crs){
        int found=0;
        for (Course c: course){
            if (c.getCourse_code().equals(crs.getCourse_code())){
                found++;
            }
        }
        if (found==0){
            System.out.println("Course not assigned to teacher");
        } else{
            System.out.println("\t\tStudent List");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |\n", "First Name", "Last Name", "ID", "Age", "Course");
            System.out.println("----------------------------------------------------------------------------------------------");

            for (Student st: student){
                if (st.findCourse(crs)){
                    System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |\n", st.getFirstName(), st.getLastName(), st.getStudentID(), st.getAge(), crs.getCourse_name());
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------");

        }
    }

    public void viewAssignedCourses(){
        System.out.println("-----------------------------------------");
        System.out.printf("| %-15s | %-15s |\n", "Course Name", "Course Code");
        System.out.println("-----------------------------------------");
        for(Course c: course){
            System.out.printf("| %-15s | %-15s |\n", c.getCourse_name(), c.getCourse_code());
        }
        System.out.println("------------------------------------------");
    }


}
