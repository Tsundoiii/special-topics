package gavinchen;

public class Game {
    public static void main(String[] args) {
        Warlock war1 = new Warlock(2, 10, "Saruman", 4);
        war1.increaseLevel();
        System.out.println(war1.attack());
        war1.name = "Voldemort";
    }
}
