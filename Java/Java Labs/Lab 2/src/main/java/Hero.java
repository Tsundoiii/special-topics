/*
	Name: Gavin Chen
	Username: gavinchen
*/

public class Hero {
    private static final int MAX_LEVEL = 100;
    private static final String[] ROLES = {"Warrior", "Priest", "Wizard", "Thief", "Unassigned"};
    private final String name;
    private String role;
    private int level;
    private int experience;

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.role = "Unassigned";
        this.experience = 0;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        for (String possibleRole : ROLES) {
            if (role.equals(possibleRole)) {
                this.role = possibleRole;
                return;
            }
        }

        System.out.println("Invalid role");
        this.role = "Unassigned";
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int expToLevelUp() {
        return (int) Math.pow(level, 2);
    }

    public void gainExperience(int experience) {
        this.experience += experience;

        while (this.experience >= expToLevelUp() && level < 100) {
            this.experience -= expToLevelUp();
            level++;
        }

        System.out.printf("%s is now level %d!", name, level);
    }

    public String toString() {
        return String.format("%s the %s is level %d with %d experience.", name, role, level, experience);
    }
}
