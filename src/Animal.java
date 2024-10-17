import java.util.Random;

public abstract class Animal {
    protected double weight;
    protected int maxCountOnCell;
    protected int speed;
    protected double foodNeed;
    protected boolean isAlive = true;

    public Animal(double weight, int maxCountOnCell, int speed, double foodNeed) {
        this.weight = weight;
        this.maxCountOnCell = maxCountOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
    }

    public abstract void eat(Cell cell);

    public void move(Island island, int x, int y) {
        Random random = new Random();
        int newX = x + random.nextInt(speed * 2 + 1) - speed;
        int newY = y + random.nextInt(speed * 2 + 1) - speed;

        // Проверка границ острова
        newX = Math.max(0, Math.min(newX, island.getWidth() - 1));
        newY = Math.max(0, Math.min(newY, island.getHeight() - 1));

        island.moveAnimal(this, x, y, newX, newY);
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