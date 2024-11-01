import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LetterPairs {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("letters.txt"));
        scanner.useDelimiter("");

        StringBuilder stringBuilder = new StringBuilder(scanner.next());

        while (scanner.hasNext()) {
            char next = scanner.next().charAt(0);
            if ((Math.abs(stringBuilder.charAt(stringBuilder.length() - 1) - next)) == 32) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            } else {
                stringBuilder.append(next);
            }
        }

        System.out.println(stringBuilder);

        scanner.close();
    }
}
