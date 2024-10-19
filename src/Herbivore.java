import java.util.List;
import java.util.Random;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxPopOnCell, int speed, double maxFoodCount){
        super(weight, maxPopOnCell, speed, maxFoodCount);
    }

    @Override
    public void eat(Cell cell) {
        if (cell.getPlants() > 0) {
            int foodConsumed = (int) Math.min(maxFoodCount, cell.getPlants());
            cell.decreasePlants(foodConsumed);
            maxFoodCount -= foodConsumed;

        }


        if (maxFoodCount > 0) {
            maxFoodCount -= 0.1;
        }
        if (maxFoodCount <= 0) {
            this.die();
        }
    }

}

