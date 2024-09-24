// Gavin Chen

package gavinchen.rectangraphic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RectanGraphic {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("rectangles.txt"));

        while (scanner.hasNext()) {
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            String filled = scanner.next();

            Rectangle rectangle = new Rectangle(rows, cols, filled.equals("filled"));

            System.out.println(rectangle);
            System.out.println();
        }

        scanner.close();
    }
}
