package lms;

import java.io.*;

public class Marks implements Serializable {
    Student student;
    private double Mid1_marks=0 , Mid2_marks=0 , Final_marks=0 , Assignments_marks=0 , Quiz_marks=0 , GPA=0;


    public Marks (Student st){
        this.student = st;
        createFile(st);
    }
    public void setMid1_marks(double mid1_marks, Student student, Course course){
        if (mid1_marks > 15){
            System.out.println("MARKS greater than 15");
        }
        else {
            Mid1_marks = mid1_marks;
        }
    }
    public void setMid2_marks(double mid2_marks,Student student, Course course){
        if (mid2_marks > 15){
            System.out.println("MARKS greater than 15");
        }
        else {
            Mid2_marks = mid2_marks;
        }
    }
    public void setFinal_marks(double final_marks,Student student, Course course) {
        if (final_marks > 50){
            System.out.println("MARKS greater than 50");
        }
        else {
            Final_marks = final_marks;
            filling(student, course);
            setGPA(student, course);
        }
        //System.out.println(getGPA());
    }
    public void setQuiz_marks(double quiz_marks,Student student, Course course){
        if (quiz_marks > 10){
            System.out.println("MARKS greater than 10");
        }
        else {
            Quiz_marks = quiz_marks;
        }
    }
    public void setAssignments_marks(double assignments_marks,Student student, Course course){
        if (assignments_marks > 10){
            System.out.println("MARKS greater than 10");
        } else {
            Assignments_marks = assignments_marks;
        }
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



    public void createFile(Student student){
        try{
            File f = new File(student.getStudentID()+".csv");
            if (!f.exists()){
                f.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write("Course name , Mid-1 , Mid-2 , Assignment , Quiz , Final , GPA ");
                bw.newLine();
                bw.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFile(Student s){
        try{
            File f = new File(s.getStudentID()+".csv");
            f.delete();
        } catch(Exception e){
            throw  new RuntimeException(e);
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

    public void filling(Student student, Course c){
        try{
            File f1 = new File(student.getStudentID()+".csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f1,true));
            // bw.write("Course name , Mid-1 , Mid-2 , Assignment , Quiz , Final , GPA ");
            bw.newLine();
            bw.write(c.getCourse_name()+" , "+this.getMid1_marks()+" , "+this.getMid2_marks()+" , "+this.getAssignments_marks()+" , "+this.getQuiz_marks()+" , "+this.getFinal_marks()+","+this.getGPA());
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

    public  void setGPA(Student student, Course c){
        int count = 0;

        String arr[]= new String[6];

        try {
            BufferedReader br = new BufferedReader(new FileReader(student.getStudentID() + ".csv"));
            String buffer;
            while((buffer=br.readLine())!=null){
                arr=buffer.split(",");
                // System.out.println(getCourse_name());
                if (arr[0].equalsIgnoreCase(c.getCourse_code())){
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
        filling(student, c);
    }

}
