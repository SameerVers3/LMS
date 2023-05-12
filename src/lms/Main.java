package lms;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import lms.Student;
import java.io.*;

public class Main {
//    public static void main(String[] args) {
//        Student st = new Student("sameer", "ahmed", 19, "male", "student");
//        Student s = new Student("kuch bhi", "ahmed", 19, "male", "student");
//        Teacher t = new Teacher();
//        System.out.println("Name: " + st.firstName);
////        String s ="shit";
//
//        Course c = new Course("OOP", "CS-1005", 20, 2, 8500, t);
//        Course c1 = new Course("CS", "Cd-1875", 20, 2, 8500, t);
//        st.registerCourse(c);
//        st.registerCourse(c1);
//        st.dropCourse(c1);
//        st.registerCourse(c1);
//        st.viewRegisteredCourse();
//        st.checkFees();
//        st.payFees(18000, c.courseCode);
//        st.checkFees();
//
//        try {
//
//            FileOutputStream fileOut = new FileOutputStream("text.txt");
//            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(st);
//            objectOut.close();
//            System.out.println("The Object  was succesfully written to a file");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        Management m = new Management();
        m = readFile();

        m.viewFeeStructure();


    }

    public static void mainMenu(){
        System.out.println("\t\t\t\tWelcome to THE FLEX");
        System.out.println("\t\t-------------------------------------");
        System.out.println("\n\t\t1. Admin\n\t\t2. Teacher\n\t\t3. Studentn\n\t\t4. Exit");
        System.out.println("\t\t-->> : ");
    }

    public static void studentMenu(){
        System.out.println("\n\n------------------------------------------");
        System.out.println("1. View Attendence");
        System.out.println("2. View Registered Courses");
        System.out.println("3. Add ");
        System.out.println("3. Drop Course");
        System.out.println("3. Grade");
        System.out.println("4. Transcript");
        System.out.println("6. ");
    }

    public static void file(Management m ) {
        try {
            File file = new File("management.txt");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));
            out.writeObject(m);
            out.close();
        } catch (Exception e) {
            System.out.println("Error Occured while writting the file!");
            System.out.println(e);
            e.getStackTrace();
        }
    }

    public static Management readFile(){
        try {
            File file = new File("management.txt");
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            Management m = (Management) input.readObject();
            input.close();

            return m;
        }

        catch (Exception e) {
            System.out.println("Error Occured while reading the file!");
            System.out.println(e);
            e.getStackTrace();
            return null;
        }
    }
}