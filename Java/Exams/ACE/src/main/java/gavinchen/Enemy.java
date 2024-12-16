package gavinchen;

public abstract class Enemy {
    protected static int totalEnemies;
    protected String name;
    protected int health;
    private int level;

    public Enemy(int level, int health, String name) {
        this.level = level;
        this.health = health;
        this.name = name;

        totalEnemies++;
    }

    public void increaseLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    abstract public int attack();
}