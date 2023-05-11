package lms;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Management {
    ArrayList<Student> StudentList = new ArrayList<Student>();
    ArrayList<Course> CourseList = new ArrayList<Course>();
    ArrayList<String> FeeList = new ArrayList<String>();
    Arraylist<Teacher> teacherList= new  ArrayList<Teacher>();

    //addcourse: course object add to arraylist
    public void addCourse(String courseCode. String courseName, int creditHours){
        System.out.println("Enter new Course to be added: ");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Course Name: ");
        courseName=sc.next();
        System.out.println("Enter Course Code: ");
        courseCode=sc.next();
        System.out.println("Enter Course Credit Hours: ");
        creditHours=sc.nextInt();
        Course newCourse = new Course(courseCode, courseName, creditHours);
        CourseList.add(newCourse);
        System.out.println("New course added: \n" + newCourse.getCourseName() + newCourse.getCourseCode()+newCourse.getCreditHourse());

    }
    //addteacher: new teacher object add to teacher arraylist
    public void addTeacher(String tName, float salary){
        System.out.println("Enter new Teacher to be added: ");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Teacher Name: ");
        tName=sc.next();
        System.out.println("Enter Teacher's Salary: ");
        salary=sc.nextFloat();
        Teacher newTeacher=new Teacher(tName, salary);
        teacherList.add(newTeacher);
        System.out.println("New teacher added: \n"+newTeacher.getTName()+newTeacher.getSalary());

    }

    public ArrayList<String> getFeeList() {
        return FeeList;
    }

    public void setFeeList(ArrayList<String> feeList) {
        FeeList = feeList;
    }

    public ArrayList<Course> getCourseList() {
        return CourseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        CourseList = courseList;
    }

    public ArrayList<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        StudentList = studentList;
    }

    public Arraylist<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(Arraylist<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    //all details of course to added in table
    public void ViewCourse(){
        System.out.println("Current course list:");
        System.out.println("---------------------------------------");
        System.out.printf("| %-10s | %-40s | %-5s |\n", "Course Code", "Course Name", "Credit Hours");
        System.out.println("---------------------------------------");
        for (Course course : CourseList) {
            System.out.printf("| %-12s | %-20s | %-15d |\n", course.getcourseCode(), course.getcourseName(), course.getCreditHours());
        }
        System.out.println("---------------------------------------");
    }

    //edit course by finding index then removing previous and adding new
    public void editCourse(String courseCode, String newCourse) {
        int index=CourseList.indexOf(courseCode);
        if (index!=-1){
            CourseList.set(index,newCourse);
            System.out.println("Course Updated!");
        }
        else {
            System.out.println("Course not found!");
        }
    }
    //remove course through index
    public void removeCourse(String courseCode) {
        int index = CourseList.indexOf(courseCode);
        if (index != -1) {
            CourseList.remove(index);
            System.out.println("Course removed!");
        } else {
            System.out.println("Course not available!");
        }
    }
    public void viewFeeStructure(){
        System.out.println("Displaying Fee Structure: ");
        System.out.printf("%-20s %-5s %-10s %-10s\n", "Course", "Credit Hours", "Fee", "Total Fee");

        for (int i = 0; i < CourseList.size(); i++) {
            Course course = CourseList.get(i);
            int creditHours = course.getCreditHours();
            float fee = Float.parseFloat(FeeList.get(i));
            float totalFee = creditHours * fee;
            System.out.printf("%-2s %-5d %-10.2f %-10.2f\n", course.getCourseCode(), creditHours, fee, totalFee);
        }

    }
    //ffes, new fees, course code initialize, for eac: index find of course code, change course ffes per ch/ index removed, .add new fees
    public void editFee() {
        Scanner sc = new Scanner(System.in);
        viewFeeStructure();
        System.out.println("Select a course to edit fees for: ");
        String courseCode = sc.next();
        boolean found = false;
        for (int i = 0; i < CourseList.size(); i++) {
            if (CourseList.get(i).getCourseCode().equals(courseCode)) {
                System.out.println("Enter the new fee amount: ");
                float newFee = sc.nextFloat();
                CourseList.get(i).setFeePerCreditHour(newFee);//ye attribute Fee ka
                System.out.println("Fee updated for " + CourseList.get(i).getCourseName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Course not found!");
        }
        System.out.println("Updated Fee Structure per Credit Hour");
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-5s %-10s \n", "Course", "Credit Hours", "Fee");
        for (int i = 0; i < CourseList.size(); i++) {
            System.out.printf("%-2s %-5d %-10.2f \n", course.getCourseCode(), creditHours, fee);

        }
    }
    public void unpaidFee() {
        System.out.println("Unpaid Fee:");
        System.out.println("----------------------------");
        float UnpaidFee = 0;
        System.out.printf("%-15s %-20s %-15s %-12\n", "Course Code", "Fee Per Credit Hour", "Credit Hours", "TotalFee");

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            ArrayList<String> Fee = student.getFees();

            for (int j = 0; j < Fee.size(); j++) {
                String fee = Fee.get(j);
                boolean isPaid = fee.contains("Paid");

                if (!isPaid) {
                    String courseCode = fee.substring(0, fee.indexOf(":"));
                    Course course = addCourse(courseCode);
                    float feePerCreditHour = course.getFeePerCreditHour();
                    int creditHours = course.getCreditHours();
                    float totalFee = feePerCreditHour * creditHours;

                    UnpaidFee += totalFee;

                    System.out.printf("%-15s %-20.2f %-15d %-10.2f", courseCode, feePerCreditHour, creditHours, totalFee);
                }
            }
        }
        System.out.println("----------------------------");
        System.out.printf("Total Unpaid Fee: %.2f", UnpaidFee);
    }
    public void init_Teacher(String tName, float salary){
        Teacher teacher=new Teacher(tName, salary);
        teacherList.add(teacher);
        try{
            FileOutputStream fos = new FileOutputStream("Teacher.txt", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(teacher);
            oos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void init_Student(String StudentID, ArrayList<String> courseList) {
        Student student = new Student(StudentID, courseList);
        StudentList.add(student.toString());
        try {
            FileOutputStream fos = new FileOutputStream("Student.txt", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void CheckFees() {//}

}
