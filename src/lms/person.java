package lms;

public abstract class person {
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
    abstract void registerCourse(Course c); // function to add course to the student object
    abstract void dropCourse(Course c); // function to drop the course from the student object
//    abstract void viewAttendence(Course c); // function to view the attendence for a given course if student has not enrolled in the course print the appropriate msg
//    abstract void detailedAttendence(Course c); // function to view detailed attendence for a given course, if nnot enrolled then print appropriate message
//    abstract void attendence();  // function to view all the attendence of the course the student enrolled in
    abstract void checkFees(); // function to check the fees for the student according to course enrolled and charges per credit hour;
    abstract void payFees(int amount, String courseCode); // Will be used to pay fees
    abstract void viewGrade(Course c); //used to view the grade for the perticular course if any;
    abstract void transcript(); // Print the transcript for the student
//    abstract void gradeChangeRequest(); //make a grade change request

    // all the data should be stored in file
}
