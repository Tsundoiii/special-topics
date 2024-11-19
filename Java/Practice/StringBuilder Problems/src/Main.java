public class Main {
    public static void main(String[] args) {
        StringBuilder hello = new StringBuilder("Hello");
        hello.append(", world!");

        StringBuilder loveProgramming = new StringBuilder("I love programming");
        loveProgramming.insert(0, "Java ");

        StringBuilder helloJava = new StringBuilder("Hello, Java world!");
        helloJava.delete(helloJava.indexOf("J"), helloJava.indexOf("w"));
        System.out.println(helloJava);
    }
}