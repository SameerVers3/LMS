package lms;

import java.io.Serializable;

public abstract class person implements Serializable{
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String gender;
    protected String designation;

    public person(String firstName, String lastName, int age, String gender, String designation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.designation = designation;
    }

    public person(){

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}

interface std{
    abstract boolean registerCourse(Course c); // function to add course to the student object
    abstract boolean dropCourse(Course c); // function to drop the course from the student objec
    abstract void checkFees(); // function to check the fees for the student according to course enrolled and charges per credit hour;
    abstract void payFees(int amount, String courseCode); // Will be used to pay fees
    abstract void viewGrade(Course c); //used to view the grade for the perticular course if any;
    abstract void transcript(); // Print the transcript for the student
}
