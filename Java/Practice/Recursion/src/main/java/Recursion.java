public class Recursion {
    public static void main(String[] args) {
        System.out.println(countLoop("ottter", "t"));
        System.out.println(countRecursion("ottter", "t"));
    }

    public static int countLoop(String s, String charToFind) {
        return (int) s.chars().filter(character -> character == charToFind.codePointAt(0)).count();
    }

    public static int countRecursion(String s, String charToFind) {
        if (s.isEmpty()) {
            return 0;
        }

        char x = s.charAt(0);
        char[] xs = s.substring(1).toCharArray();

        return (x == charToFind.codePointAt(0) ? 1 : 0) + countRecursion(String.valueOf(xs), charToFind);
    }
}
