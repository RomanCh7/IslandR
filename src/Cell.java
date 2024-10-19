import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Animal> animals;
    private int plants;
    private final int maxAnimals;

    public Cell(int maxAnimals) {
        this.animals = new ArrayList<>();
        this.plants = 0;
        this.maxAnimals = maxAnimals;
    }


    public void addAnimal(Animal animal) {
        if (getFreeSpace() > 0) {
            animals.add(animal);
        }
    }


    public List<Animal> getAnimals() {
        return animals;
    }


    public void setPlants(int plants) {
        this.plants = plants;
    }


    public int getPlants() {
        return plants;
    }


    public void decreasePlants(int amount) {
        if (plants - amount >= 0) {
            plants -= amount;
        } else {
            plants = 0;
        }
    }


    public int countAnimals(Class<? extends Animal> animalType) {
        int count = 0;
        for (Animal animal : animals) {
            if (animalType.isInstance(animal)) {
                count++;
            }
        }
        return count;
    }


    public int getFreeSpace() {
        return maxAnimals - animals.size();
    }

    public boolean hasPlants() {
        return true;
    }

    public void consumePlant() {

    }


}



