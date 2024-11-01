package rpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

abstract class Character {
    private final String name;
    private int health;
    private int money;
    private int magicalOrbs;

    public Character(String name) {
        this.name = name;
        health = 10;
        money = 10;
        magicalOrbs = 0;
    }

    public int getMagicalOrbs() {
        return magicalOrbs;
    }

    public abstract void specialPower(ArrayList<Character> players, Scanner scanner);

    public void displayCharacterInfo() {
        System.out.println("\nCharacter: " + name + "\nHealth: " + health + "\nMoney: " + money + "\nMagical Orbs: " + magicalOrbs);
    }

    public void rollDice(ArrayList<Character> players, Scanner scanner) {
        Random random = new Random();
        String[] diceSides = {"1", "2", "3", "$", "->", "<3"};
        int[] rolls = new int[6];

        System.out.println(name + " is rolling dice...");
        for (int i = 0; i < 6; i++) {
            int roll = random.nextInt(1, 7);
            rolls[i] = roll;

            String result = diceSides[roll - 1];
            System.out.print(result + " ");
        }
        System.out.println("\n");

        System.out.println("reroll: [n]one, [s]ome, [a]ll");
        switch (scanner.nextLine()) {
            case "s":
                System.out.println("Reroll indexes");
                String rerolls = scanner.nextLine();
                for (String num : rerolls.split(" ", 5)) {
                    rolls[Integer.parseInt(num)] = random.nextInt(1, 7);
                }
                break;
            case "a":
                for (int i = 0; i < 6; i++) {
                    int roll = random.nextInt(1, 7);
                    rolls[i] = roll;
                }
                break;
            case "n":
            default:
                break;
        }

        System.out.println("Final rolls:");
        for (int roll : rolls) {
            String result = diceSides[roll - 1];
            System.out.print(result + " ");
            handleDiceRolls(roll, players, this);
        }
        System.out.println();
    }

    public void handleDiceRolls(int roll, ArrayList<Character> players, Character roller) {
        switch (roll) {
            // Terrible: don't do this
            case 3:
                magicalOrbs++;
            case 2:
                magicalOrbs++;
            case 1:
                magicalOrbs++;
                break;
            case 4:
                for (Character player : players) {
                    if (!player.equals(roller)) {
                        player.setHealth(player.getHealth() - 1);
                    }
                }
                break;
            case 5:
                setMoney(money + 1);
                break;
            case 6:
                if (getHealth() <= 12) {
                    setHealth(health + 1);
                }
                break;
        }
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }
}

class Wizard extends Character {

    public Wizard() {
        super("Wizard");
    }

    @Override
    public void specialPower(ArrayList<Character> players, Scanner scanner) {
        if (getMoney() >= 2) {
            setMoney(getMoney() - 2);
            setHealth(getHealth() + 1);
            System.out.println("Wizard used special power! Traded 2 money for 1 health.");
        } else {
            System.out.println("Not enough money to use special power.");
        }
    }
}

class Giant extends Character {

    public Giant() {
        super("Giant");
    }

    @Override
    public void specialPower(ArrayList<Character> players, Scanner scanner) {
        if (getMoney() >= 5) {
            setMoney(getMoney() - 5);
            for (Character player : players) {
                if (!player.equals(this)) {
                    player.setHealth(player.getHealth() - 1);
                }
            }
            System.out.println("Giant used special power! Traded 5 money to attack (-1 health) all other players.");
        } else {
            System.out.println("Not enough money to use special power.");
        }
    }
}

class Centaur extends Character {

    public Centaur() {
        super("Centaur");
    }

    @Override
    public void specialPower(ArrayList<Character> players, Scanner scanner) {
        if (getMoney() >= 3) {
            setMoney(getMoney() - 3);
            System.out.println("Centaur used special power! Traded 3 money for an extra dice roll.");
            rollDice(players, scanner);
        } else {
            System.out.println("Not enough money to use special power.");
        }
    }
}

class EldritchHorror extends Character {
    public EldritchHorror() {
        super("Eldritch Horror");
    }

    @Override
    public void specialPower(ArrayList<Character> players, Scanner scanner) {
        int livesTaken = getHealth() - 1;
        for (Character player : players) {
            player.setHealth(player.getHealth() - livesTaken);
        }
        System.out.println("Eldritch Horror used special power! " + livesTaken + " lives taken from EVERYONE.");

    }
}

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> players = new ArrayList<>();

        System.out.println("Welcome to the RPG Game!");
        System.out.println("How many players? (2 or 3)");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Player " + i + ", choose your character:");
            System.out.println("1. Wizard");
            System.out.println("2. Giant");
            System.out.println("3. Centaur");
            System.out.println("4. Eldritch Horror");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Character player = switch (choice) {
                case 1 -> new Wizard();
                case 2 -> new Giant();
                case 3 -> new Centaur();
                case 4 -> new EldritchHorror();
                default -> {
                    System.out.println("Invalid choice! Defaulting to Wizard.");
                    yield new Wizard();
                }
            };

            players.add(player);
            System.out.println("Player " + i + " chose " + player.getName() + "\n");
        }

        System.out.println("All players have chosen their characters. Let the game begin!");
        System.out.println();

        boolean gameOn = true;
        while (gameOn) {
            if (players.size() == 1) {
                gameOn = false;
                System.out.println(players.getFirst().getName() + " Wins!");
            }

            for (Character player : players) {
                if (player.getHealth() <= 0) {
                    players.remove(player);
                }
                if (player.getMagicalOrbs() >= 30) {
                    gameOn = false;
                    System.out.println(player.getName() + " Wins!");
                    break;
                }
                System.out.println(player.getName() + "'s turn!");
                System.out.println("Press Enter to roll the dice...");
                scanner.nextLine();

                player.rollDice(players, scanner);

                System.out.println("Would you like to use your special power? (yes/no)");
                String usePower = scanner.nextLine();

                if (usePower.equalsIgnoreCase("yes")) {
                    player.specialPower(players, scanner);
                }

                player.displayCharacterInfo();
                System.out.println();
            }
        }

        System.out.println("Game over! Thanks for playing.");
        scanner.close();
    }
}
