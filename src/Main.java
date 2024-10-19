import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(100, 20);
        populateIsland(island);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(() -> {
            processAnimalLifeCycle(island);
        }, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            printIslandStatistics(island);
        }, 0, 1, TimeUnit.SECONDS);

    }

    private static void populateIsland(Island island) {
        for (int i = 0; i < 50; i++) {
            Random random=new Random();
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Fox());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Bear());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Rabbit());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Boa());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Boar());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Buffalo());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Caterpillar());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Deer());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Duck());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Eagle());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Mouse());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Sheep());
            island.getCell(random.nextInt(0,99),
                    random.nextInt(0,19)).addAnimal(new Wolf());

        }
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                island.getCell(i, j).setPlants(20);
            }
        }
        System.out.println("Остров заселен животными и растениями.");
    }

    private synchronized static void processAnimalLifeCycle(Island island) {
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Cell cell = island.getCell(i, j);
                List<Animal> animals = new ArrayList<>(cell.getAnimals());

                for (Animal animal : animals) {
                    if (animal.isAlive()) {
                        animal.eat(cell);
                        animal.reproduce(cell);
                        animal.move(island, i, j);
                    }
                }
                cell.getAnimals().removeIf(a -> !a.isAlive());
            }
        }

    }


    private synchronized static void printIslandStatistics(Island island) {
        int totalAnimals = 0;
        int totalPlants = 0;

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                totalAnimals += island.getCell(i, j).getAnimals().size();
                totalPlants += island.getCell(i, j).getPlants();
            }
        }

        System.out.println("Общее количество животных на острове: " + totalAnimals);
        System.out.println("Общее количество растений на острове: " + totalPlants);
    }
}
