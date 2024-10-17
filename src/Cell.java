import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Animal> animals;
    private int plants;  // Количество растений на клетке
    private final int maxAnimals;  // Максимальное количество животных на клетке

    public Cell(int maxAnimals) {
        this.animals = new ArrayList<>();
        this.plants = 0;  // Изначально на клетке нет растений
        this.maxAnimals = maxAnimals;  // Устанавливаем максимальное количество животных на клетке
    }

    // Метод для добавления животного на клетку
    public void addAnimal(Animal animal) {
        if (getFreeSpace() > 0) {
            animals.add(animal);
        } else {
            System.out.println("На клетке больше нет места для добавления животных.");
        }
    }

    // Метод для получения списка животных на клетке
    public List<Animal> getAnimals() {
        return animals;
    }

    // Метод для установки начального количества растений на клетке
    public void setPlants(int plants) {
        this.plants = plants;
    }

    // Метод для получения текущего количества растений на клетке
    public int getPlants() {
        return plants;
    }

    // Метод для уменьшения количества растений (например, когда травоядное их ест)
    public void decreasePlants(int amount) {
        if (plants - amount >= 0) {
            plants -= amount;
        } else {
            plants = 0;  // Если растения съедены полностью
        }
    }

    // Метод для подсчета животных определенного типа
    public int countAnimals(Class<? extends Animal> animalType) {
        int count = 0;
        for (Animal animal : animals) {
            if (animalType.isInstance(animal)) {
                count++;
            }
        }
        return count;
    }

    // Метод для получения количества свободных мест для животных на клетке
    public int getFreeSpace() {
        return maxAnimals - animals.size();
    }

    public boolean hasPlants() {
        return true;
    }

    public void consumePlant() {

    }
}



