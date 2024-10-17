import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Создаем остров размером 100x20 клеток
        Island island = new Island(100, 20);

        // Заполняем остров животными и растениями
        populateIsland(island);

        // Создаем пул потоков для выполнения задач
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Задача для выполнения жизненного цикла животных
        scheduler.scheduleAtFixedRate(() -> {
            processAnimalLifeCycle(island);
        }, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            printIslandStatistics(island);
        }, 0, 1, TimeUnit.SECONDS);

        // Задача для вывода статистики о текущем состоянии острова
    }

    // Метод для заполнения острова животными и растениями
    private static void populateIsland(Island island) {
        // Добавляем несколько животных на клетки острова
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

        // Добавляем растения на остров
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                island.getCell(i, j).setPlants(20); // Устанавливаем 20 единиц растений на каждой клетке
            }
        }

        System.out.println("Остров заселен животными и растениями.");
    }

    // Метод для обработки жизненного цикла животных на острове
    private synchronized static void processAnimalLifeCycle(Island island) {
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Cell cell = island.getCell(i, j);

                // Для каждого животного на клетке
                for (Animal animal : cell.getAnimals()) {
                    if (animal.isAlive()) {
                        animal.eat(cell);   // Животное пытается покушать
                        animal.move(island, i, j);  // Животное перемещается
                        animal.reproduce(cell); // Животное размножается
                    }
                }

                // Убираем мертвых животных из клетки
                cell.getAnimals().removeIf(a -> !a.isAlive());
            }
        }
        System.out.println("Жизненный цикл животных на острове завершён.");
    }

    // Метод для вывода статистики о состоянии острова
    private synchronized static void printIslandStatistics(Island island) {
        int totalAnimals = 0;
        int totalPlants = 0;

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                totalAnimals += island.getCell(i, j).getAnimals().size();
                totalPlants += island.getCell(i, j).getPlants(); // Подсчитываем оставшиеся растения
            }
        }

        System.out.println("Общее количество животных на острове: " + totalAnimals);
        System.out.println("Общее количество растений на острове: " + totalPlants);
    }
}
