// Gavin Chen

package gavinchen.rectangraphic;

public class Rectangle {
    private int rows;
    private int cols;
    private boolean filled;

    public Rectangle(int rows, int cols, boolean filled) {
        this.rows = rows;
        this.cols = cols;
        this.filled = filled;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("#".repeat(cols)).append("\n");
        for (int row = 1; row < rows - 1; row++) {
            string.append("#").append((filled ? "#" : " ").repeat(cols - 2)).append("#").append("\n");
        }
        string.append("#".repeat(cols));
        return string.toString();
    }
}