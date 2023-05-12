package lms;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Attendence implements Serializable{
    public void createFile(Course course){
        try{
            File f = new File(course.getCourse_name()+" Attendance"+".csv");
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Date ,Name , ID , Attendance");
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void isPresent(Student student,Course courses , boolean present){
        try {
            File f = new File(courses.getCourse_name()+" Attendance"+".csv");
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(f, true));
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatter);
            if (present){
                bw1.write(dateString+","+student.getFirstName()+","+student.getStudentID()+","+"Present");
            }else {
                bw1.write(dateString+","+student.getFirstName()+","+student.getStudentID()+","+"Absent");
            }
            bw1.newLine();
            bw1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showAttendance(Course courses , Student student) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(courses.getCourse_name()+" Attendance"+".csv"));
            String buffer;
            while ((buffer = br.readLine()) != null) {
                String[] parts = buffer.split(",");
                if (parts[0].equalsIgnoreCase(student.firstName)) {
                    System.out.println(buffer);
                }

            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}