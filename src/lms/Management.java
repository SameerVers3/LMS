package lms;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Management implements Serializable{
    ArrayList<Student> StudentList = new ArrayList<Student>();
    ArrayList<Course> CourseList = new ArrayList<Course>();
    ArrayList<String> FeeList = new ArrayList<String>();
    ArrayList<Teacher> teacherList= new  ArrayList<Teacher>();

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

        System.out.println("New course added: \n" + CourseList.get(CourseList.size()-1).getCourse_name() + "\tCourse code: " +  CourseList.get(CourseList.size()-1).getCourse_code()  +"\tCredit Hours: " + CourseList.get(CourseList.size()-1).getCredit_hrs());
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

//    public Arraylist<Teacher> getTeacherList() {
//        return teacherList;
//    }
//
//    public void setTeacherList(Arraylist<Teacher> teacherList) {
//        this.teacherList = teacherList;
//    }

    //all details of course to added in table
    public void ViewCourse(){
        System.out.println("Current course list:");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-5s | %-10s | %-25s |\n", "Course Code", "Course Name", "Credit Hours", "Fees", "Emrolled Students");
        System.out.println("---------------------------------------------------------------------------");
        for (Course course : CourseList) {
            System.out.printf("| %-12s | %-15s | %-15d | %-10s | %-25s |\n", course.getCourse_code(), course.getCourse_name(), course.getCredit_hrs(), course.getFeesPerCredit(), course.student.size());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    //edit course by finding index then removing previous and adding new
    public void editCourse(String courseCode) {
        Scanner sc= new Scanner(System.in);
        int index=0, found=0;
        for (Course i: CourseList){
            if (i.getCourse_code().equalsIgnoreCase(courseCode)){
                System.out.println("What do you want to change ? ");
                System.out.println("1. Course code \n2. Course name\n3. Credit hours \t4. feesPerCredit");
                int choice= sc.nextInt();
                switch(choice){
                    case(1) -> {
                        System.out.println("Enter new Code: ");
                        String code = sc.next();
                        Course editedCourse = new Course(i, choice, code);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    } case (2) -> {
                        System.out.println("Enter ne Name: ");
                        String name = sc.next();
                        Course editedCourse = new Course(i, choice, name);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    } case (3) -> {
                        System.out.println("Enter new Credit hours: ");
                        int credit = sc.nextInt();
                        Course editedCourse = new Course(i, choice, credit);
                        CourseList.add(editedCourse);
                        CourseList.remove(index);
                    } case (4) -> {
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
        }
//        int index=CourseList.indexOf(courseCode);
//        if (index!=-1){
//            CourseList.set(index,newCourse);
//            System.out.println("Course Updated!");
//        }
//        else {
//            System.out.println("Course not found!");
//        }
    }
    //remove course through index
    public void removeCourse(String courseCode) {
        int j=0, check=0, check2=0; // j is index and check is to find if the course is in the list or not
        for (Course i: CourseList) {
            if (i.getCourse_code().equalsIgnoreCase(courseCode)) {
                CourseList.remove(j); // if course code matches then the course is dropped at that index
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
        // conditions for check
        if (check > 0 && stCheck > 0){
            System.out.println("Course deleted successfully");
        }
        else {
            System.out.println("Course not found");
        }

//        int index = CourseList.indexOf(courseCode);
//        if (index != -1) {
//            CourseList.remove(index);
//            System.out.println("Course removed!");
//        } else {
//            System.out.println("Course not available!");
//        }
    }
    public void viewFeeStructure(){
        System.out.println("Displaying Fee Structure: ");
        System.out.printf("| %-20s | %-5s | %-10s | %-10s |\n", "Course", "Credit Hours", "Fee", "Total Fee");
        for (Course i: CourseList){
            System.out.printf("%-2s %-5d %-10d %-10d\n", i.getCourse_code(), i.getCredit_hrs(), i.getFeesPerCredit(), (i.getCredit_hrs()*i.getFeesPerCredit()));
        }

//        for (int i = 0; i < CourseList.size(); i++) {
//            Course course = CourseList.get(i);
//            int creditHours = course.();
//            float fee = Float.parseFloat(FeeList.get(i));
//            float totalFee = creditHours * fee;
//            System.out.printf("%-2s %-5d %-10.2f %-10.2f\n", course.getCourseCode(), creditHours, fee, totalFee);
//        }

    }
    //ffes, new fees, course code initialize, for eac: index find of course code, change course ffes per ch/ index removed, .add new fees


    public void editFee() {
        Scanner sc = new Scanner(System.in);
        viewFeeStructure();
        System.out.println("Select a course to edit fees for: ");
        String courseCode = sc.next();

        int index=0, found=0;
        for (Course i: CourseList){
            if (i.getCourse_code().equalsIgnoreCase(courseCode)){
                System.out.println("Enter the new fee amount: ");
                int fee = sc.nextInt();
                if (fee>0){
                    Course editedCourse = new Course(i, 4, fee);
                    CourseList.add(editedCourse);
                    System.out.println("Fee updated for " + CourseList.get(index).getCourse_code());
                    CourseList.remove(index);
                } else{
                    System.out.println("Invalid Amount!");
                    break;
                }
                found++;
                break;
            }
            index++;
        }
        if (!(found==0)){
            System.out.println("Course Not Found");
        }


//        boolean found = false;
//        for (int i = 0; i < CourseList.size(); i++) {
//            if (CourseList.get(i).getCourseCode().equals(courseCode)) {
//                System.out.println("Enter the new fee amount: ");
//                float newFee = sc.nextFloat();
//                CourseList.get(i).setFeePerCreditHour(newFee);//ye attribute Fee ka
//                System.out.println("Fee updated for " + CourseList.get(i).getCourseName());
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            System.out.println("Course not found!");
//        }
//        System.out.println("Updated Fee Structure per Credit Hour");
//        System.out.println("------------------------------------------------");
//        System.out.printf("%-20s %-5s %-10s \n", "Course", "Credit Hours", "Fee");
//        for (int i = 0; i < CourseList.size(); i++) {
//            System.out.printf("%-2s %-5d %-10.2f \n", course.getCourseCode(), creditHours, fee);
//
//        }
    }

    public void unpaidFee() {
        System.out.println("Unpaid Fee: ");
        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-25s |\n", "Course Code", "Student Count", "Fee Per Credit Hour", "Credit Hours", "Per Course", "Total Fees", "Total Paid fees");
        System.out.println("----------------------------------------------------------------------------------------------");

        int paid=0, totalPaid=0, total=0, i=0, j=0;
        for (Course c: CourseList){
            j=0;
            for (Student st: StudentList){
                if( c.getCourse_code().equalsIgnoreCase(st.course.get(j).getCourse_code())){
                    paid += st.fees.get(j).getPaidFees();
                    total += st.fees.get(j).getTotalFees();
                }
                j++;
            }
            totalPaid += paid;
            System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-25s |\n", c.getCourse_code(), c.student.size(), c.getFeesPerCredit(), c.getCredit_hrs(), (c.getFeesPerCredit()*c.getCredit_hrs()), total, totalPaid);
            i++;
            paid=0;
            total=0;
        }

        System.out.println("-----------------------------------------------------------------------");
    }
//        float UnpaidFee = 0;
//        System.out.printf("%-15s %-20s %-15s %-12s\n", "Course Code", "Fee Per Credit Hour", "Credit Hours", "TotalFee");
//
//
//        for (int i = 0; i < studentList.size(); i++) {
//            Student student = studentList.get(i);
//            ArrayList<String> Fee = student.getFees();
//
//            for (int j = 0; j < Fee.size(); j++) {
//                String fee = Fee.get(j);
//                boolean isPaid = fee.contains("Paid");
//
//                if (!isPaid) {
//                    String courseCode = fee.substring(0, fee.indexOf(":"));
//                    Course course = findCourse(courseCode);
//                    float feePerCreditHour = course.getFeePerCreditHour();
//                    int creditHours = course.getCreditHours();
//                    float totalFee = feePerCreditHour * creditHours;
//
//                    UnpaidFee += totalFee;
//
//                    System.out.printf("%-15s %-20.2f %-15d %-10.2f", courseCode, feePerCreditHour, creditHours, totalFee);
//                }
//            }
//        }
//        System.out.println("----------------------------");
//        System.out.printf("Total Unpaid Fee: %.2f", UnpaidFee);
//    }

//    private Course findCourse(String courseCode) {
//        for (Course course : CourseList) {
//            if (course.getCourseCode().equals(courseCode)) {
//                return course;
//            }
//        }
//        return null;
//    }

//    public void init_Teacher(String tName, float salary){
//        Teacher teacher=new Teacher(tName, salary);
//        teacherList.add(teacher);
//        try{
//            FileOutputStream fos = new FileOutputStream("Teacher.txt", true);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(teacher);
//            oos.close();
//            fos.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }


//    public void init_Student(String StudentID, ArrayList<String> courseList) {
//        Student student = new Student(StudentID, courseList);
//        StudentList.add(student.toString());
//        try {
//            FileOutputStream fos = new FileOutputStream("Student.txt", true);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(student);
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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
        System.out.println("Student added successfully!! ");
    }

//    public void CheckFees() {//}

}
