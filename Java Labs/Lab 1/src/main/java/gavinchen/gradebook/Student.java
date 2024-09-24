// Gavin Chen

package gavinchen.gradebook;

import java.util.stream.IntStream;

public class Student {
    private final String name;
    private final int[] scores = new int[5];

    public Student(String name, int[] scores) {
        this.name = name;
        System.arraycopy(scores, 0, this.scores, 0, scores.length);
    }

    public double getAverage() {
        return IntStream.of(scores).average().getAsDouble();
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 90 && average <= 100) {
            return "A";
        } else if (average >= 80 && average <= 89) {
            return "B";
        } else if (average >= 70 && average <= 79) {
            return "C";
        } else if (average >= 60 && average <= 69) {
            return "D";
        } else {
            return "F";
        }
    }

    public String toString() {
        return name + "\t" + scores[0] + "\t" + scores[1] + "\t" + scores[2] + "\t" + scores[3] + "\t" + scores[4] + "\t" + getAverage() + "\t" + getGrade();
    }
}
