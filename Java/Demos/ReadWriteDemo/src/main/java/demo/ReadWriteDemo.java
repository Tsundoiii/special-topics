package demo;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadWriteDemo {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(new FileReader("demo.txt"));
        PrintWriter writer = new PrintWriter("matrix.txt");

        String[][] matrix = new String[10][10];

        int row, col;
        String symbol;

        while (reader.hasNext()) {
            row = reader.nextInt();
            col = reader.nextInt();
            symbol = reader.next();

            matrix[row][col] = symbol;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == null) writer.print("-");
                else writer.print(matrix[i][j]);
            }
            writer.println();
        }

        reader.close();
        writer.close();
    }
}
