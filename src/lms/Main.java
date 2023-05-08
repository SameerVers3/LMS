package lms;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("---");
    }

}
class Management {
    ArrayList<String> TeacherList = new ArrayList<String>();
    ArrayList<String> StudentList = new ArrayList<String>();
    ArrayList<String> CourseList = new ArrayList<String>();
    ArrayList<String> FeeList = new ArrayList<String>();

    public Management(ArrayList<String> teacherList, ArrayList<String> studentList, ArrayList<String> courseList, ArrayList<String> feeList) {
        TeacherList = teacherList;
        StudentList = studentList;
        CourseList = courseList;
        FeeList = feeList;
    }

    public ArrayList<String> getFeeList() {
        return FeeList;
    }

    public void setFeeList(ArrayList<String> feeList) {
        FeeList = feeList;
    }

    public ArrayList<String> getCourseList() {
        return CourseList;
    }

    public void setCourseList(ArrayList<String> courseList) {
        CourseList = courseList;
    }

    public ArrayList<String> getTeacherList() {
        return TeacherList;
    }

    public void setTeacherList(ArrayList<String> teacherList) {
        TeacherList = teacherList;
    }

    public ArrayList<String> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        StudentList = studentList;
    }
    public void ViewCourse(){
        System.out.println("Current course list:");
        for (String course : CourseList) {
            System.out.println(course);
        }
    }
    public void addCourse(String course){
        CourseList.add(course);
        System.out.println("New course added: " + course);

    }
    public void editCourse(int index, String newCourse) {
        CourseList.set(index, newCourse);
    }
    public void removeCourse(int index) {
        CourseList.remove(index);
    }
    public void viewFee(){
        System.out.println("Displaying Fee Structure: ");
        for (String fee : FeeList){
            System.out.println(fee);
        }
    }
    public void editFee(){
        Scanner sc=new Scanner(System.in);
        viewFee();
        System.out.println("Select course to edit fees for: ");
        String Ecourse=sc.next();
        if (CourseList.indexOf(Ecourse)==-1){
            System.out.println("Course not available!");
        }
        System.out.println("Enter the new Fee amount: ");
        float newFee=sc.nextFloat();
        System.out.println("Enter payment deadline: ");
        String deadline=sc.next();
        FeeList.set(CourseList.indexOf(Ecourse), Ecourse+ ":"+ newFee + "Deadline: "+ deadline);
        System.out.println("Fee Structure updated!");

    }
    public void unpaidFee(){

    }



//    public void AddStudent() {}
//    public void AddTeacher() {}
//    public void CheckFees() {}

}
