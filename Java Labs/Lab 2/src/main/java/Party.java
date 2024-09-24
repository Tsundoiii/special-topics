/*
	Name: Gavin Chen
	Username: gavinchen
*/

public class Party {
    private final Hero[] heroes = new Hero[3];

    public void addHero(Hero hero, int index) {
        for (Hero partyHero : heroes) {
            if (hero == partyHero) {
                System.out.printf("%s is already in the party.", hero.getName());
                break;
            }
        }

        heroes[index] = hero;
    }

    public void removeHero(int index) {
        heroes[index] = null;
    }

    public Hero getHero(int index) {
        return heroes[index];
    }

    public void gainExperience(int experience) {
        System.out.printf("The party gained %d experience.", experience);
        System.out.println();

        for (Hero hero : heroes) {
            if (hero != null) {
                hero.gainExperience(experience);
            }
        }
    }

    public String toString() {
        StringBuilder partyInfo = new StringBuilder("Party:\n");

        for (Hero hero : heroes) {
            partyInfo.append(hero.toString() + "\n");
        }

        return partyInfo.toString();
    }
}
