package lms;

import java.io.*;
import java.util.ArrayList;

class Student extends person implements std, Serializable{
    // courses the student is enrolled in;
    ArrayList<Course> course;
    ArrayList<Fees> fees;
    private static int seedID;
    public int studentID;
    public int creditLimit=17;
    public int creditEnrolled=0;
    private static final long serialVersionUID = -3006587467375933807L;
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
        creditEnrolled=0;
    }

    public Student(){
        super();
    }
    public boolean registerCourse(Course c){
        int check=0;
        if (creditEnrolled<=creditLimit){
            for (Course i: course) {
                if (i.getCourse_code().equalsIgnoreCase(c.getCourse_code())) {
                    check++; // if course code maches then the check is increased
                }
            }
            if (check==0){
                this.course.add(c);
                this.fees.add(new Fees(c.getCourse_code(), c.getCourse_name(),this.studentID , this.firstName, c.getCredit_hrs(), c.getFeesPerCredit()));
                creditEnrolled+=c.getCredit_hrs();

                return true;
            }
            else {
                System.out.println("You are Already enrolled in the course: ");
                return false;
            }
        }

        else {
            System.out.println("Credit Exceed credit Limit! ");
            return false;
        }
    }

    public boolean dropCourse(Course c){
        int j=0, check=0; // j is index and check is to find if the course is in the list or not
        for (Course i: course) {
            if (i.getCourse_code().equalsIgnoreCase(c.getCourse_code())) {
                course.remove(j); // if course code matches then the course is dropped at that index
                fees.remove(j);
                creditEnrolled -= c.getCredit_hrs();

                check++;
                return true;
            }
            j++;
        }
        // conditions for check
        if (check > 0){
            System.out.println("Course deleted successfully");
            return true;
        }
        else {
            System.out.println("Course not found");
            return false;
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

        System.out.println("Credit Limit: " + this.creditLimit + "\t Credit Enrolled: " + this.creditEnrolled);
    }



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

    public boolean findCourse(Course c){
        int found=0;
        for (Course i: course){
            if (c.getCourse_code().equalsIgnoreCase(i.getCourse_code())){
                found++;
            }
        }
        if (found==0){
            return false;
        } else{
            return true;
        }
    }


}
