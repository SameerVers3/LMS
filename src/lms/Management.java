package lms;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Management implements Serializable{
    ArrayList<Student> StudentList = new ArrayList<Student>();
    ArrayList<Marks> MarksList = new ArrayList<>();
    ArrayList<Course> CourseList = new ArrayList<Course>();
    ArrayList<Attendence> AttendenceList = new ArrayList<>();
    ArrayList<Fees> FeeList = new ArrayList<Fees>();
    ArrayList<Teacher> teacherList= new  ArrayList<Teacher>();
    private static final long serialVersionUID = 8937440120597790291L;

    //addcourse: course object add to arraylist
    public void addCourse(){
        System.out.println("Enter new Course to be added: ");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Course Name: ");
        String courseName=sc.next();
        System.out.println("Enter Course Code: ");
        String courseCode=sc.next();
        System.out.println("Enter Course Credit Hours: ");
        int creditHours=sc.nextInt();
        System.out.println("Enter fees per credit hour: ");
        int fees = sc.nextInt();
        CourseList.add(new Course(courseName, courseCode, creditHours, fees));
        System.out.println("\t\tNew course added! \n ------------------------------------------------------------------\n"
                + "| Course Name: " + CourseList.get(CourseList.size()-1).getCourse_name() + "\t| " +
                "Course code: " +  CourseList.get(CourseList.size()-1).getCourse_code()  +"\t| Credit Hours: " + CourseList.get(CourseList.size()-1).getCredit_hrs() + " |\n"
                +"------------------------------------------------------------------\n");
        AttendenceList.add(new Attendence(CourseList.get(CourseList.size()-1)));
    }
    //addteacher: new teacher object add to teacher arraylist

    public void addTeacher(){
        System.out.println("Enter new Teacher to be added: ");
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Teacher's First Name: ");
        String name = sc.next();
        System.out.println("Enter Teacher's Last Name: ");
        String lname = sc.next();
        System.out.println("Enter Teacher's age: ");
        int age = sc.nextInt();
        System.out.println("Enter Teacher's Gender: ");
        String gender = sc.next();
        System.out.println("Enter Teacher's Salary: ");
        float salary=sc.nextFloat();
        System.out.println("Enter Teacher's password: ");
        String password = sc.next();
        teacherList.add(new Teacher(name, lname, age, gender,"Teacher", password, salary));
        System.out.println("New teacher added: \n"+ teacherList.get(teacherList.size()-1).getTeacher_name() + teacherList.get(teacherList.size()-1).getT_salary());
    }
    public void viewCourse(){
        System.out.println("Current course list:");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-5s | %-10s | %-25s |\n", "Course Code", "Course Name", "Credit Hours", "Fees", "Emrolled Students");
        System.out.println("---------------------------------------------------------------------------");
        for (Course course : CourseList) {
            System.out.printf("| %-12s | %-15s | %-15d | %-10s | %-25s |\n", course.getCourse_code(), course.getCourse_name(), course.getCredit_hrs(), course.getFeesPerCredit(), course.student.size());
        }
        System.out.println("--------------------------------------------------------------------------");
    }


    public void editCourse(String courseCode) {
        Scanner sc= new Scanner(System.in);
        int index=0, found=0;
        for (Course i: CourseList){
            if (i.getCourse_code() != null && i.getCourse_code().equalsIgnoreCase(courseCode)){
                System.out.println("What do you want to change ? ");
                System.out.println("1. Course code \n2. Credit hours \n3. feesPerCredit");
                int choice= sc.nextInt();
                switch(choice){
                    case(1) -> {
                        System.out.println("Enter new Code: ");
                        String code = sc.next();
                        Course editedCourse = new Course(i, choice, code);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    } case (2) -> {
                        System.out.println("Enter new Credit hours: ");
                        int credit = sc.nextInt();
                        Course editedCourse = new Course(i, choice, credit);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    } case (3) -> {
                        System.out.println("Enter new Fees per credit: ");
                        int fees = sc.nextInt();
                        Course editedCourse = new Course(i, choice, fees);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    }default -> {
                        System.out.println("Invalid Input");
                    }
                }
                found++;
                break;
            }
            index++;
        }
        if (found==0){
            System.out.println("Course Not Found");
        } else{
            System.out.println("Edited scuccessfully");
        }
    }




    //remove course through index
    public void removeCourse(String courseCode) {
        int j=0, check=0, check2=0; // j is index and check is to find if the course is in the list or not
        for (Course i: CourseList) {
            if (i.getCourse_code().equalsIgnoreCase(courseCode)) {
                CourseList.remove(j); // if course code matches then the course is dropped at that index
                AttendenceList.get(j).deleteFile(i);
                AttendenceList.remove(j);
                check++;
                break;
            }
            j++;
        }

        int iStudent=0, iCourse=0, stCheck=0; // iStudent = index for student for each loop, iCourse is for course for each loop, and stCheck is for keeping index

        for (Student st: StudentList){
            iCourse=0;
            for (Course c: st.course){
                if (c.getCourse_code().equalsIgnoreCase(courseCode)){
                    StudentList.get(iStudent).course.remove(iCourse);
                    stCheck++;
                }
                iCourse++;
            }
            iStudent++;
        }
    }
    public void viewFeeStructure(){
        System.out.println("\t\tDisplaying Fee Structure: ");
        System.out.println("--------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-10s | %-10s |\n", "Course", "Credit Hours", "Fee", "Total Fee");
        System.out.println("--------------------------------------------------------");
        for (Course i: CourseList){
            System.out.printf("| %-10s | %-10d | %-10d | %-10d |\n", i.getCourse_code(), i.getCredit_hrs(), i.getFeesPerCredit(), (i.getCredit_hrs()*i.getFeesPerCredit()));
        }

        System.out.println("---------------------------------------------------------------");

    }
    public void editFee() {
        viewFeeStructure();
        Scanner sc= new Scanner(System.in);
        System.out.println("Select a course to edit fees for: ");
        String courseCode = sc.next();
        int index=0, found=0;
        for (Course i: CourseList){
            if (i.getCourse_code() != null && i.getCourse_code().equalsIgnoreCase(courseCode)){
                System.out.println("Enter new Fees per credit: ");
                int fees = sc.nextInt();
                Course editedCourse = new Course(i, 3, fees);
                CourseList.add(editedCourse);
                CourseList.remove(index);
                found++;
                break;
            }
            index++;
        }
        if (found==0){
            System.out.println("Fees Edited Successfully!");
        } else{
            System.out.println("Edited scuccessfully");
        }
    }

    public void viewStudentList(){
        System.out.println("\t\tStudent List");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |\n", "First Name", "Last Name", "ID", "Age", "Courses Enrolled");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (Student st: StudentList){
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |\n", st.getFirstName(), st.getLastName(), st.getStudentID(), st.getAge(), st.course.size());
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    public void viewTeacherList(){
        System.out.println("\t\tTeacher List");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", "First Name", "Last Name", "Age", "Gender", "Salary", "Courses Assigned", "password");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Teacher t: teacherList){
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", t.getFirstName(), t.getLastName(), t.getAge(), t.getGender(), t.getT_salary(), t.course.size(), t.getTeacher_password());
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    public void unpaidFee() {
        System.out.println("\t\tFees Summary");
        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-25s |\n", "Course Code", "Student Count", "Fee Per Credit Hour", "Credit Hours", "Per Course", "Total Fees", "Total Paid fees");
        System.out.println("----------------------------------------------------------------------------------------------");

        int paid = 0, totalPaid = 0, total = 0, i = 0, j = 0;
        for (Course c : CourseList) {
            j = 0;
            if (!StudentList.isEmpty()) {
                for (Student st : StudentList) {
                    if (!st.course.isEmpty()) {
                        if (j < st.course.size() && c != null && c.getCourse_code() != null && c.getCourse_code().equalsIgnoreCase(st.course.get(j).getCourse_code())) {
                            paid += st.fees.get(j).getPaidFees();
                            total += st.fees.get(j).getTotalFees();
                        }
                    }

                    j++;
                }
                totalPaid += paid;
                if(c != null) {
                    System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-25s |\n", c.getCourse_code(), c.student.size(), c.getFeesPerCredit(), c.getCredit_hrs(), (c.getFeesPerCredit() * c.getCredit_hrs()), total, totalPaid);
                    i++;
                }
                paid = 0;
                total = 0;
            }
        }

        System.out.println("-----------------------------------------------------------------------");
    }

    public int findCourse(String courseCode) {
        int index = 0;
        for (Course i : CourseList) {
            if (i.getCourse_code() != null && i.getCourse_code().equalsIgnoreCase(courseCode))
                {
                    return index;
                }
                index++;
            }
        return -1;

    }

    public int findTeacher(String name) {
        int index = 0;
        for (Teacher i : teacherList) {
            if (i.getTeacher_name() != null && i.getTeacher_name().equalsIgnoreCase(name))
            {
                return index;
            }
            index++;
        }
        return -1;

    }

    public void addStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String name = sc.next();
        System.out.println("Enter Last Name: ");
        String lname = sc.next();
        System.out.println("Enter age: ");
        int age = sc.nextInt();
        System.out.println("Enter Gender: ");
        String gender = sc.next();
        StudentList.add(new Student(name, lname, age, gender, "student"));
        MarksList.add(new Marks(StudentList.get(StudentList.size()-1)));
        System.out.println("Student added successfully!! ");
    }
}