import java.util.List;
import java.util.Random;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxCountOnCell, int speed, double foodNeed) {
        super(weight, maxCountOnCell, speed, foodNeed);
    }

    @Override
    public void eat(Cell cell) {
        // Особое поведение для утки
        if (this instanceof Duck) {
            boolean ate = false;
            List<Animal> animalsInCell = cell.getAnimals();
            for (Animal prey : animalsInCell) {
                if (prey == this || !prey.isAlive()) continue;
                if (prey instanceof Caterpillar) {
                    int probability = ProbabilityTable.getProbability(this.getClass(), prey.getClass());
                    if (probability > 0) {
                        Random random = new Random();
                        int chance = random.nextInt(100);
                        if (chance < probability) {
                            prey.die();
                            System.out.println("Утка съела гусеницу");
                            ate = true;
                            break;
                        }
                    }
                }
            }
            if (!ate && cell.hasPlants()) {
                cell.consumePlant();
                System.out.println("Утка поела растения");
            }
        } else {
            // Остальные травоядные едят растения
            if (cell.hasPlants()) {
                cell.consumePlant();
                System.out.println(this.getClass().getSimpleName() + " поел растения");
            } else {
                System.out.println(this.getClass().getSimpleName() + " не нашел пищи");
            }
        }
    }
}

