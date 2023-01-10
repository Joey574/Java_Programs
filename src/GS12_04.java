import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class GS12_04
{

    static class Student {
        private String fName;
        private String lName;
        private int id;
        private float grade;
        private String letterGrade;
    }

    static class compareByName implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
            return o2.lName.compareTo(o1.lName);
        }
    }

    static class compareByID implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    static class compareByGrade implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
            float t = o1.grade - o2.grade;
            int r = 0;
            if (t > 0.0) {
                r = 1;
            } else if (t < 0.0) {
                r = -1;
            }
            return r;
        }
    }


    public static void main(String[] args) throws IOException {

        String fileName = "GS12_04_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList<Student> students = new ArrayList<Student>();

        for (int i = 0; lineScanner.hasNextLine(); i++) {
            students.add(new Student());
            students.get(i).fName = lineScanner.next();
            students.get(i).lName = lineScanner.next();
            students.get(i).id = lineScanner.nextInt();
            students.get(i).grade = lineScanner.nextFloat();
            students.get(i).letterGrade = lineScanner.next();
        }

        Scanner r = new Scanner(System.in);

        int choice;
        int aORd;

        System.out.print("How would you like to arrange the data?\n1. Last name\n2. student ID\n3. Grade\nInput: ");
        int temp = r.nextInt();

        if (temp == 1) {
            choice = 1;
        } else if (temp == 2) {
            choice = 2;
        } else if (temp == 3) {
            choice = 3;
        }

        System.out.print("Would you like to sort by ascending or descending order?\n1. Ascending\n2. Descending\nInput: ");
        temp = r.nextInt();

        if (temp == 1) {
            aORd = 1;
        } else if (temp == 2) {
            aORd = 2;
        }


    }
}
