package gavinchen;

public class Warlock extends Enemy {
    private final int wandLevel;

    public Warlock(int level, int health, String name, int wandLevel) {
        super(level, health, name);

        this.wandLevel = wandLevel;
    }

    @Override
    public int attack() {
        return getLevel() * wandLevel;
    }
}
