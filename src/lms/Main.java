package lms;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        File file = new File("Management.dat");
        Management m = new Management();
        if (file.exists()){
            m =  getObject();
        } else {
            m = new Management();
        }

        int chMain=0, chStudent=0, chAdmin=0, chTeacher=0;

        while (chMain < 4){
            mainMenu();
            chMain = sc.nextInt();
            switch(chMain){
                case(1) ->
                {
                    int adminLoop=0;
                        while(adminLoop==0){
                        System.out.println("Enter user name: ");
                        String user = sc.next();
                        System.out.println("Enter password: ");
                        String pass = sc.next();
                        if (user.equals("tom") && pass.equals("666")){
                            int adminMenuLoop=0;
                            while(chAdmin<16){
                                adminMenu();
                                chAdmin=sc.nextInt();
                                switch(chAdmin){
                                    case(1) ->
                                    {
                                        m.addCourse();
                                        fileObject(m);
                                    }
                                    case(2) ->
                                    {
                                        m.addStudent();
                                        fileObject(m);
                                    }
                                    case(3) ->
                                    {
                                        m.addTeacher();
                                        fileObject(m);
                                    }
                                    case(4) ->
                                    {
                                        System.out.println("Enter Course code to edit: ");
                                        String code = sc.next();
                                        int index = m.findCourse(code);
                                        if (index>0){
                                            m.editCourse(code);
                                            fileObject(m);
                                        }else{
                                            System.out.println("Course Not Found");
                                        }
                                    }
                                    case(5) ->
                                    {
                                        System.out.println("Enter Course code to asign: ");
                                        String code = sc.next();
                                        int index = m.findCourse(code);
                                        if (index >0){
                                            System.out.println("Enter Teacher name: ");
                                            String name = sc.next();
                                            int index2 = m.findTeacher(name);
                                            if (index2>=0){
                                                m.teacherList.get(index2).assignCourse(m.CourseList.get(index));
                                            }
                                            else {
                                                System.out.println("Teacaher does not exist!");
                                            }
                                        }
                                        else{
                                            System.out.println("Course not found ! ");
                                        }
                                    }
                                    case(6) ->
                                    {
                                        m.viewCourse();
                                        fileObject(m);
                                    }
                                    case(7) ->
                                    {
                                        m.viewStudentList();
                                        fileObject(m);
                                    }
                                    case(8) ->
                                    {
                                        m.viewTeacherList();
                                        fileObject(m);
                                    }
                                    case(9) ->
                                    {
                                        System.out.println("Enter Course Code to remove: ");
                                        String code = sc.next();
                                        int index = m.findCourse(code);
                                        if (index > 0){
                                            m.removeCourse(code);
                                            fileObject(m);
                                        }
                                        else {
                                            System.out.println("Course Not found");
                                        }
                                    }
                                    case(10) ->
                                    {
                                        m.viewFeeStructure();
                                    }
                                    case(11) ->
                                    {
                                        m.editFee();
                                        fileObject(m);
                                    }
                                    case(12) ->
                                    {
                                        m.unpaidFee();
                                    }
                                    case(13) ->
                                    {
                                        chAdmin=16;
                                        adminLoop=1;
                                    }
                                    case(14) ->
                                    {
                                        fileObject(m);
                                        System.exit(0);
                                    }
                                    default ->
                                    {
                                        System.out.println("Invalid Input");
                                    }

                                }
                            }
                            chAdmin=0;
                        } else{
                            System.out.println("User Name or password Incorrect! ");
                            System.out.print("Enter 1 to retry:  ");
                            adminLoop = sc.nextInt();
                        }
                    }
                }
                case (2) ->
                {
                    System.out.println("Enter student id: ");
                    int id = sc.nextInt();
                    System.out.println("id: " + id);
                    int count=0, index=0;
                    for (Student student: m.StudentList){
                        if(student.getStudentID() == id){
                            while (chStudent<8) {
                                studentMenu();
                                chStudent = sc.nextInt();
                                switch (chStudent){
                                    case (1) -> {
                                        System.out.println("Enter Course code: ");
                                        String code = sc.next();
                                        int ind = m.findCourse(code);
                                        if (ind >=0 ){
                                            int in =0, f=0;
                                            for (Student st: m.StudentList){
                                                if (st.getStudentID() == id){
                                                    m.MarksList.get(ind).display(st);
                                                    f++;
                                                    break;
                                                }
                                                in++;
                                            }
                                            if (f==0){
                                                System.out.println("Student not Found");
                                            }
                                        }
                                        else{
                                            System.out.println("Course not found! ");
                                        }
                                    }

                                    case (2) ->
                                    {
                                        for
                                        (Course c: m.StudentList.get(index).course){
                                            System.out.println("Course code: " + c.getCourse_code());
                                        }
                                    }
                                    case (3) ->
                                    {
                                        System.out.println("Enter Course Code to register: ");
                                        String code = sc.next();
                                        if (m.findCourse(code) >= 0){
                                            boolean f = m.StudentList.get(index).registerCourse(m.CourseList.get(m.findCourse(code)));
                                            if (f){
                                                m.CourseList.get(m.findCourse(code)).addStudent(m.StudentList.get(index));
                                            }
                                            fileObject(m);
                                        } else {
                                            System.out.println("Course not Found!");
                                        }
                                    }
                                    case (4) ->
                                    {
                                        System.out.println("Enter Course Code to Drop: ");
                                        String code = sc.next();
                                        if (m.findCourse(code) >= 0){
                                            boolean f = m.StudentList.get(index).dropCourse(m.CourseList.get(m.findCourse(code)));
                                            if (f){
                                                m.CourseList.get(m.findCourse(code)).removeStudent(m.StudentList.get(index));
                                            }
                                            fileObject(m);
                                        } else {
                                            System.out.println("Course not Found!");
                                        }
                                    }
                                    case (5) ->
                                    {
                                        int in =0, f=0;
                                        for (Student st: m.StudentList){
                                            if (st.getStudentID() == id){
                                                m.MarksList.get(in).display(st);
                                                f++;
                                                break;
                                            }
                                            in++;
                                        }
                                        if (f==0){
                                            System.out.println("Student not Found");
                                        }
                                    }
                                    case(6)->
                                    {
                                            chStudent=8;
                                    }

                                    case(7) ->
                                    {
                                         System.exit(0);
                                    }

                                }
                            }
                            chStudent=0;
                            count++;
                        }
                        index++;
                    }
                    if (count==0){
                        System.out.println("Student not Found!!");
                    }
                }
                case (3) ->
                {
                    System.out.println("Enter Teacher Name: ");
                    String name= sc.next();
                    System.out.println("Enter Teacher password: ");
                    String pass = sc.next();

                    int index = m.findTeacher(name);
                    if (index>=0){
                        if (m.teacherList.get(index).getTeacher_password().equals(pass)){
                            while(chTeacher<8){
                                teacherMenu();
                                chTeacher = sc.nextInt();
                                switch(chTeacher){
                                    case(1) ->
                                    {
                                        m.teacherList.get(index).viewAssignedCourses();
                                    }
                                    case(2) ->
                                    {
                                        System.out.println("Enter Course code: ");
                                        String code = sc.next();
                                        int ind = m.findCourse(code);
                                        if (index >=0 ){
                                            m.teacherList.get(index).viewStudentList(m.StudentList, m.CourseList.get(ind));
                                        }
                                        else{
                                            System.out.println("Course not found! ");
                                        }
                                    }
                                    case(3) ->
                                    {
                                        System.out.println("Enter Course code: ");
                                        String code = sc.next();
                                        int ind = m.findCourse(code);
                                        if (index >=0 ){
                                            m.teacherList.get(index).markAttendence(m.StudentList, m.CourseList.get(ind), m.AttendenceList.get(ind));
                                        }
                                        else{
                                            System.out.println("Course not found! ");
                                        }
                                    }
                                    case(4) ->
                                    {
                                        System.out.println("Enter Course code: ");
                                        String code = sc.next();
                                        int ind = m.findCourse(code);
                                        if (ind >=0 ){
                                            System.out.println("Enter student ID: ");
                                            int id = sc.nextInt();
                                            int in =0, f=0;
                                            for (Student st: m.StudentList){
                                                if (st.getStudentID() == id){
                                                    m.MarksList.get(ind).display(st);
                                                    f++;
                                                    break;
                                                }
                                                in++;
                                            }
                                            if (f==0){
                                                System.out.println("Student not Found");
                                            }
                                        }
                                        else{
                                            System.out.println("Course not found! ");
                                        }
                                    }
                                    case(5) ->
                                    {
                                        System.out.println("Enter student ID: ");
                                        int id = sc.nextInt();
                                        int in =0, f=0;
                                        for (Student st: m.StudentList){
                                            if (st.getStudentID() == id){
                                                m.MarksList.get(in).display(st);
                                                f++;
                                                break;
                                            }
                                            in++;
                                        }
                                        if (f==0){
                                            System.out.println("Student not Found");
                                        }
                                    }
                                    case(6) ->
                                    {
                                        System.out.println("Enter Course code: ");
                                        String code = sc.next();
                                        int ind = m.findCourse(code);
                                        if (ind >=0 ){
                                            System.out.println("Enter student ID: ");
                                            int id = sc.nextInt();
                                            int in =0, f=0;
                                            for (Student st: m.StudentList){
                                                if (st.getStudentID() == id){
                                                    double mid1, mid2, assign, quiz, fin;
                                                    do {
                                                        System.out.println("Enter Mid 1 marks: ");
                                                        mid1 = sc.nextInt();
                                                        m.MarksList.get(in).setMid1_marks(mid1, m.StudentList.get(in), m.CourseList.get(ind));
                                                    } while (mid1>15);

                                                    do {
                                                        System.out.println("Enter Mid 1 marks: ");
                                                        mid2 = sc.nextInt();
                                                        m.MarksList.get(in).setMid2_marks(mid2, m.StudentList.get(in), m.CourseList.get(ind));
                                                    } while (mid2>15);

                                                    do {
                                                        System.out.println("Enter Assignment marks: ");
                                                        assign = sc.nextInt();
                                                        m.MarksList.get(in).setAssignments_marks(assign, m.StudentList.get(in), m.CourseList.get(ind));
                                                    } while (assign >15);

                                                    do {
                                                        System.out.println("Enter Quiz marks: ");
                                                        quiz = sc.nextInt();
                                                        m.MarksList.get(in).setQuiz_marks(quiz, m.StudentList.get(in), m.CourseList.get(ind));
                                                    } while (quiz>15);

                                                    do {
                                                        System.out.println("Enter Final marks: ");
                                                        fin = sc.nextInt();
                                                        m.MarksList.get(in).setFinal_marks(fin, m.StudentList.get(in), m.CourseList.get(ind));
                                                    } while(fin>15);

                                                    System.out.println("Marks added successfully! ");
                                                    f++;
                                                    break;
                                                }
                                                in++;
                                            }
                                            if (f==0){
                                                System.out.println("Student not Found");
                                            }
                                        }
                                        else{
                                            System.out.println("Course not found! ");
                                        }
                                    }
                                    case(7) ->
                                    {
                                        System.out.println("Name: " + m.teacherList.get(index).getTeacher_name() + "\nPassword: " + m.teacherList.get(index).getTeacher_password() + "\nSalary: " + m.teacherList.get(index).getT_salary());
                                    }
                                    case(8) ->
                                    {
                                        chTeacher = 10;
                                    }
                                    case(9) ->
                                    {
                                        System.exit(0);
                                    }
                                    default ->
                                    {
                                        System.out.println("Invalid Input!");
                                        chTeacher=0;
                                    }

                                }
                            }
                            chTeacher=0;
                        }
                        else{
                            System.out.println("Incorrect password! ");
                        }
                    }
                    else{
                        System.out.println("Name not found! ");
                    }
                }
                case (4) ->
                {
                    System.exit(0);
                }
                default ->
                {
                    System.out.println("Invalid Input");
                }
            }
        }
    }

    public static void mainMenu(){
        System.out.println("\t\t\t\tWelcome to THE FLEX");
        System.out.println("\t\t-------------------------------------");
        System.out.println("\n\t\t1. Admin\n\t\t2. Stud1" +
                "ent\n\t\t3. Teachern\n\t\t4. Exit");
        System.out.println("\t\t-->> : ");
    }

    public static void adminMenu(){
        System.out.println("\n\n------------------------------------------");
        System.out.println("1. Add Course");
        System.out.println("2. Add Student");
        System.out.println("3. Add Teacher");
        System.out.println("4. Edit Course");
        System.out.println("5. Assign Course to Teacher");
        System.out.println("6. View Course List");
        System.out.println("7. View Student List");
        System.out.println("8. View Tecaher List");
        System.out.println("9. Remove Course");
        System.out.println("10. View Fee Structure");
        System.out.println("11. Edit Fees");
        System.out.println("12. View Fees Summary");
        System.out.println("13. logout");
        System.out.println("14. exit");
    }

    public static void studentMenu(){
        System.out.println("\n\n------------------------------------------");
        System.out.println("1. View Attendence");
        System.out.println("2. View Registered Courses");
        System.out.println("3. Add Course");
        System.out.println("4. Drop Course");
        System.out.println("5. Transcript");
        System.out.println("6. Transcript");
        System.out.println("7. LogOut");
        System.out.println("8. Exit");
    }

    public static void teacherMenu(){
        System.out.println("\n\n---------------------------------------------");
        System.out.println("1. View Assigned Courses");
        System.out.println("2. View Student List");
        System.out.println("3. Mark Attendence");
        System.out.println("4. View Attendence");
        System.out.println("5. View Grade");
        System.out.println("6. Mark Student");
        System.out.println("7. Get personal info");
        System.out.println("8. logout");
        System.out.println("9. Exit");
    }

    public static void fileObject(Management m){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Management.dat")));
            oos.writeObject(m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public static Management getObject() {
        Management m = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Management.dat"));
            m = (Management) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return m;
    }

}