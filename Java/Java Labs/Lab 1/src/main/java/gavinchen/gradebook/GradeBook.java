package gavinchen.gradebook;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeBook {
    private static Student[] students;
    private static double total;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("grade_data.txt"));
        int length = scanner.nextInt();

        students = new Student[length];

        for (int i = 0; scanner.hasNext(); i++) {
            String name = scanner.next();
            int[] scores = new int[5];

            for (int j = 0; scanner.hasNextInt(); j++) {
                scores[j] = scanner.nextInt();
            }

            students[i] = new Student(name, scores);
        }

        PrintWriter printWriter = new PrintWriter("gradebook.txt");
        printWriter.println("Student" + "\t" + "Test1" + "\t" + "Test2" + "\t" + "Test3" + "\t" + "Test4" + "\t"
                + "Test5" + "\t" + "Average" + "\t" + "Grade");

        for (Student student : students) {
            printWriter.println(student);
            total += student.getAverage();
        }

        System.out.println();
        printWriter.println("Class Average = " + total / length);

        scanner.close();
        printWriter.close();
    }
}
