package lms;

import java.io.*;
import java.util.ArrayList;

class Student extends person implements std, Serializable{
    // courses the student is enrolled in;
    ArrayList<Course> course;
    ArrayList<Fees> fees;
    private static int seedID;
    public int studentID;

    public int getStudentID() {
        return studentID;
    }

    static{
        seedID = 99;
    }
    public Student(String firstName, String lastName, int age, String gender, String designation) {
        super(firstName, lastName, age, gender, designation);
        studentID = seedID;
        seedID++;
        course = new ArrayList<>();
        fees = new ArrayList<>();
    }

    public void registerCourse(Course c){
        int check=0;
        for (Course i: course){
            if (i.getCourse_code().equalsIgnoreCase(c.getCourse_code())){
                check++; // if course code maches then the check is increased
            }
        }
        if (check==0){
            this.course.add(c);

            //if check is not increased then the course is added (means student is not already enrolled in the course)
//            this.attendence.add(new Attendence(c.co, c.courseName, this.studentID, this.firstName, c.duration));
//            this.fees.add(new Fees(c.courseCode, c.courseName, this.studentID, this.firstName, c.creditHours, c.feesPerCredit));
            // FILING CODE HERE
            // CODE TO OVERRIDE WHOLE OBJECT IN THE CLASS
        }
        else {
            System.out.println("You are Already enrolled in the course: ");
        }
    }

    public void dropCourse(Course c){
        int j=0, check=0; // j is index and check is to find if the course is in the list or not
        for (Course i: course) {
            if (i.getCourse_code().equalsIgnoreCase(c.getCourse_code())) {
                course.remove(j); // if course code matches then the course is dropped at that index
//                attendence.remove(j);
//                fees.remove(j);
                check++;
                break;
                // FILING CODE HERE

                // CODE TO OVERRIDE WHOLE OBJECT IN THE CLASS
            }
            j++;
        }
        // conditions for check
        if (check > 0){
            System.out.println("Course deleted successfully");
        }
        else {
            System.out.println("Course not found");
        }
    }

    public void viewRegisteredCourse(){
        System.out.println("Current course list:");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-40s | %-5s | %-10s | %-25s |\n", "Course Code", "Course Name", "Credit Hours", "Fees", "Emrolled Students");
        System.out.println("---------------------------------------------------------------------------");
        for (Course course : course) {
            System.out.printf("| %-12s | %-20s | %-15d | %-10s | %-25s |\n", course.getCourse_code(), course.getCourse_name(), course.getCredit_hrs(), course.getFeesPerCredit(), course.student.size());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

//    public void viewAttendence(Course c){
//        int j=0, check=0;
//        for(Course i: course){
//            if (i.courseCode.equals(c.courseCode)){
//                attendence.get(j).viewAttendence();
//                check++;
//            }
//            j++;
//        }
//
//        if (check==0){
//            System.out.println("Not Enrolled in the course");
//        }
//    }

//    public void detailedAttendence(Course c){
//        int j=0, check=0;
//        for(Course i: course){
//            if (i.courseCode.equals(c.courseCode)){
//                attendence.get(j).viewAttendence();
//                check++;
//            }
//            j++;
//        }
//
//        if (check==0){
//            System.out.println("Not Enrolled in the course");
//        }
//    }
//    public  void attendence(){
//        for(Attendence i: attendence){
//            i.viewAttendence();
//        }
//    }

    public void checkFees(){
        int total=0, paid=0;
        for (Fees i: fees){
            total += i.totalFees;
            paid += i.paid;
            i.viewFees();
        }
        System.out.println("\nTotal: " + total + "\tPaid: " + paid + "\tBalance: " + (-(total-paid)));
    }

    public void payFees(int amount, String courseCode){
        int j=0, check=0;
        for (Course i: course){
            if (i.getCourse_code().equalsIgnoreCase(courseCode)){
                fees.get(j).payFees(amount);
                check++;
                System.out.println("Fees paid");
            }
            j++;
        }
        if (check==0){
            System.out.println("Course not found");
        }
    }
    public void createFile(){
        try{
            File f = new File(getStudentID()+".csv");
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Course name , Mid-1 , Mid-2 , Assignment , Quiz , Final , GPA ");
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void viewGrade(Course c){
        for (Course i: course){
            if (i.getCourse_code().equalsIgnoreCase(c.getCourse_code())){
//                c.checkGrade();
                System.out.println("Something");
            }
        }
    }
    public void transcript(){
        // code where transcript is printed by looping through the whole arraylist
    }
}
