import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(double weight, int maxCountOnCell, int speed, double foodNeed) {
        super(weight, maxCountOnCell, speed, foodNeed);
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
                    // Хищник съедает жертву
                    prey.die();
                    System.out.println(this.getClass().getSimpleName() + " съел " + prey.getClass().getSimpleName());
                    break; // Предполагаем, что хищник съедает только одну жертву за раз
                }
            }
        }
    }
}
