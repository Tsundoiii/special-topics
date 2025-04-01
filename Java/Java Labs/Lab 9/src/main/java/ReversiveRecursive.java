import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ReversiveRecursive {
    public static String reverse(String s) {
        if (s.length() == 1) {
            return s;
        }

        return reverse(s.substring(1)) + s.charAt(0);
    }

    public static void wordCount(RandomAccessFile reader, ArrayList<Word> words) {
        try {
            Word word = new Word(reader.readUTF());
            if (!words.contains(word)) {
                words.add(word);
            } else {
                words.get(words.indexOf(word)).count();
            }

            wordCount(reader, words);
        } catch (IOException ignored) {
        }
    }
}
