package lms;
//
import java.io.Serializable;
import java.util.ArrayList;
//
//public class Course implements Serializable {
//    String courseName;
//    String courseCode;
//    int duration;
//    int creditHours;
//    int feesPerCredit;
//    Teacher teacher;
//    ArrayList<String> student;
//
//    public Course(String courseName, String courseCode, int duration, int creditHours, int feesPerCredit, Teacher t) {
//        this.courseName = courseName;
//        this.courseCode = courseCode;
//        this.duration = duration;
//        this.creditHours = creditHours;
//        this.feesPerCredit = feesPerCredit;
//        this.teacher = t;
//        student = new ArrayList<>();
//    }
//
//    void checkGrade() {
//
//    }
//
//    public void printDetails(){
//        System.out.println("Course Name: " + this.courseName);
//        System.out.println("Course Code: " + this.courseCode);
//        System.out.println("Credit Hours: " + this.creditHours);
//    }
//    public void addStudent(String st){
//        student.add(st);
//    }
//
//
//}

// -----------------------------------------------------------------



import java.io.*;
import java.util.InputMismatchException;

public class Course implements Serializable{
    private String course_name;
    private String course_code;
    private int credit_hrs;
    private int feesPerCredit;
    ArrayList<Student> student;
    private double Mid1_marks=0 , Mid2_marks=0 , Final_marks=0 , Assignments_marks=0 , Quiz_marks=0 , GPA=0;

    //Constructor
    public Course(String courseName, String courseCode, int creditHours, int feesPerCredit) {
        this.course_name = courseName;
        this.course_code = courseCode;
        this.credit_hrs = creditHours;
        this.feesPerCredit = feesPerCredit;
        student = new ArrayList<>();
        Attendence attendence = new Attendence();
    }
    // copy Constructor but with a twist
    public Course(Course c, int ch, int a){
        if (ch == 3){
            this.course_name = c.course_name;
            this.course_code = c.course_code;
            this.credit_hrs = a;
            this.feesPerCredit = c.feesPerCredit;
            this.student = c.student;
        } else if (ch == 4){
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
                student.remove(j); // if course code matches then the course is dropped at that index
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
            System.out.println("Student removed from the course successfully");
        }
        else {
            System.out.println("Student not found");
        }
    }

    public Course(Course c, int ch, String a) {
        if (ch == 2){
            this.course_name = a;
            this.course_code = c.course_code;
            this.credit_hrs = c.credit_hrs;
            this.feesPerCredit = c.feesPerCredit;
            this.student = c.student;
        } else if (ch == 1){
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
    public void setMid1_marks(double mid1_marks, Student student) throws Exception {
        if (mid1_marks > 15){
            throw new Exception("MARKS greater than 15");
        }
        Mid1_marks = mid1_marks;
        filling(student);

    }
    public void setMid2_marks(double mid2_marks,Student student) throws Exception {
        if (mid2_marks > 15){
            throw new Exception("MARKS greater than 15");
        }
        Mid2_marks = mid2_marks;
        filling(student);
    }
    public void setFinal_marks(double final_marks,Student student) throws Exception {
        if (final_marks > 50){
            throw new Exception("MARKS greater than 50");
        }
        Final_marks = final_marks;
        filling(student);
        setGPA(student);
        //System.out.println(getGPA());

    }
    public void setQuiz_marks(double quiz_marks,Student student) throws Exception {
        if (quiz_marks > 10){
            throw new Exception("MARKS greater than 10");
        }
        Quiz_marks = quiz_marks;
        filling(student);
    }
    public void setAssignments_marks(double assignments_marks,Student student) throws Exception {
        if (assignments_marks > 10){
            throw new Exception("MARKS greater than 10");
        }
        Assignments_marks = assignments_marks;
        filling(student);
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
    public double getGPA() {
        return GPA;
    }
    public double getMid1_marks() {
        return Mid1_marks;
    }
    public double getMid2_marks() {
        return Mid2_marks;
    }
    public double getFinal_marks() {
        return Final_marks;
    }
    public double getAssignments_marks() {
        return Assignments_marks;
    }
    public double getQuiz_marks() {
        return Quiz_marks;
    }

    public int getFeesPerCredit() {
        return feesPerCredit;
    }

    //Class Composition
//    Attendance attendance = new Attendance();
    //Method

    public void createFile(Student student){
        try{
            File f = new File(student.getStudentID()+".csv");
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Course name , Mid-1 , Mid-2 , Assignment , Quiz , Final , GPA ");
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void display(Student  student){
        try {
            BufferedReader br = new BufferedReader(new FileReader(student.getStudentID() + ".csv"));
            String buffer;

            while((buffer=br.readLine())!=null){
                System.out.println(buffer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void filling(Student student){
        try{
            File f1 = new File(student.getStudentID()+".csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f1,true));
            // bw.write("Course name , Mid-1 , Mid-2 , Assignment , Quiz , Final , GPA ");
            bw.newLine();
            bw.write(getCourse_name()+" , "+this.getMid1_marks()+" , "+this.getMid2_marks()+" , "+this.getAssignments_marks()+" , "+this.getQuiz_marks()+" , "+this.getFinal_marks()+","+this.getGPA());
            bw.close();
            //update_Counter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void check(double[] arr){
        if (arr[0]==0){
            System.out.println("Mid-1 marks are 0 ");
        }if (arr[1]==0){
            System.out.println("Mid-2 marks are 0 ");
        }if (arr[2]==0){
            System.out.println("Assignment marks are 0 ");
        }if (arr[3]==0){
            System.out.println("Quiz marks are 0 ");
        }if (arr[4]==0){
            System.out.println("Final marks are 0 ");
        }
    }
    public  void setGPA(Student student) throws Exception {
        int count = 0;

        String arr[]= new String[6];

        try {
            BufferedReader br = new BufferedReader(new FileReader(student.getStudentID() + ".csv"));
            String buffer;
            while((buffer=br.readLine())!=null){
                arr=buffer.split(",");
                // System.out.println(getCourse_name());
                if (arr[0].equalsIgnoreCase(this.getCourse_name())){
                    count++;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        for (int i = 0; i < arr.length-1; i++) {
//            System.out.println(arr[i]);
//        }
        double[] marks = new double[5];
        marks[0]= Double.parseDouble(arr[1]);
        marks[1]= Double.parseDouble(arr[2]);
        marks[2]= Double.parseDouble(arr[3]);
        marks[3]= Double.parseDouble(arr[4]);
        marks[4]= Double.parseDouble(arr[5]);

        check(marks);

        double m =marks[0]+ marks[1]+marks[4]+marks[2]+marks[3];
        this.GPA = (m >= 86 ? 4 :
                (m >= 82 ? 3.66 :
                        (m >= 78 ? 3.33 :
                                (m >= 74 ? 3.00 :
                                        (m >= 70 ? 2.66 :
                                                (m >= 66 ? 2.33 :
                                                        (m >= 62 ? 2.00 :
                                                                (m >= 58 ? 1.66 :
                                                                        (m >= 54 ? 1.33 :
                                                                                (m >= 50 ? 1.00 :
                                                                                        0)))))))))); // "else"
        filling(student);



    }

    //Only String entry check
    private static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

}
