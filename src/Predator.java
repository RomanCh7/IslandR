import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(double weight, int maxPopOnCell, int speed, double maxFoodCount) {
        super(weight, maxPopOnCell, speed, maxFoodCount);
    }

    @Override
    public void eat(Cell cell) {
        List<Animal> animalsInCell = cell.getAnimals();
        for (Animal prey : animalsInCell) {
            if (prey == this || !prey.isAlive()) continue;

            int probability = ProbabilityTable.getProbability(this.getClass(), prey.getClass());

            if (probability > 0) {
                Random random = new Random();
                int chance = random.nextInt(100);
                if (chance < probability) {
                    prey.die();
                    break;
                }
            }
        }
    }

}
