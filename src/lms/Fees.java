package lms;

import java.io.Serializable;

public class Fees implements Serializable {
    String courseCode;
    String courseName;
    int studentID;
    String name;
    int credit;
    int perCredit;

    int totalFees=0;
    int paid=0;
    public Fees(String courseCode, String courseName, int studentID, String name, int credit, int perCredit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studentID = studentID;
        this.name = name;
        this.credit = credit;
        this.perCredit = perCredit;
        totalFees = credit*perCredit;
    }

    public void payFees(int amount){
        if (amount > 0){
            if (amount <= totalFees){
                paid += amount;
            }
            else {
                System.out.println("Amount is greater than the total fees for the course! ");
            }
        }
        else {
            System.out.println("Invalid Fees");
        }
    }

    public void viewFees(){
        System.out.println("Course Name: " + this.courseName + "\tCourse Code: " + this.courseCode + "\tTotal Fees: " + this.totalFees + "\tPaid Fees: " + this.paid + "\tNet: " + (this.totalFees-this.paid));
    }

    public int getPaidFees(){
        return paid;
    }

    public int getTotalFees(){
        return this.totalFees;
    }

}
