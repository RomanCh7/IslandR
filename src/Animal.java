import java.util.Random;

public abstract class Animal {
    protected double weight;
    protected int maxPopOnCell;
    protected int speed;
    protected double maxFoodCount;
    protected boolean isAlive = true;

    public Animal(double weight, int maxCountOnCell, int speed, double maxFoodCount) {
        this.weight = weight;
        this.maxPopOnCell = maxCountOnCell;
        this.speed = speed;
        this.maxFoodCount=maxFoodCount;
    }
    public abstract void eat(Cell cell);

    public void move(Island island, int x, int y) {
        Random random = new Random();
        int newX = x + random.nextInt(speed * 2 + 1) - speed;
        int newY = y + random.nextInt(speed * 2 + 1) - speed;


        newX = Math.max(0, Math.min(newX, island.getWidth() - 1));
        newY = Math.max(0, Math.min(newY, island.getHeight() - 1));


        if (newX != x || newY != y) {
            island.moveAnimal(this, x, y, newX, newY);
        }
    }


    public void reproduce(Cell cell) {
        if (cell.countAnimals(this.getClass()) >= 2 && cell.getFreeSpace() > 0) {
            try {
                Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                cell.addAnimal(offspring);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
    }
}