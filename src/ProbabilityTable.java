import java.util.HashMap;
import java.util.Map;

public class ProbabilityTable {
    private static final Map<Class<? extends Animal>, Map<Class<? extends Animal>, Integer>> eatingProbabilities = new HashMap<>();

    static {

        Map<Class<? extends Animal>, Integer> wolfProbabilities = new HashMap<>();
        wolfProbabilities.put(Horse.class, 10);
        wolfProbabilities.put(Deer.class, 15);
        wolfProbabilities.put(Rabbit.class, 60);
        wolfProbabilities.put(Mouse.class, 80);
        wolfProbabilities.put(Goat.class, 60);
        wolfProbabilities.put(Sheep.class, 70);
        wolfProbabilities.put(Boar.class, 15);
        wolfProbabilities.put(Buffalo.class, 10);
        wolfProbabilities.put(Duck.class, 40);
        eatingProbabilities.put(Wolf.class, wolfProbabilities);

        Map<Class<? extends Animal>, Integer> boaProbabilities = new HashMap<>();
        boaProbabilities.put(Fox.class, 15);
        boaProbabilities.put(Rabbit.class, 20);
        boaProbabilities.put(Mouse.class, 40);
        boaProbabilities.put(Duck.class, 10);
        eatingProbabilities.put(Boa.class, boaProbabilities);

        Map<Class<? extends Animal>, Integer> foxProbabilities = new HashMap<>();
        foxProbabilities.put(Rabbit.class, 70);
        foxProbabilities.put(Mouse.class, 90);
        foxProbabilities.put(Duck.class, 60);
        eatingProbabilities.put(Fox.class, foxProbabilities);

        Map<Class<? extends Animal>, Integer> bearProbabilities = new HashMap<>();
        bearProbabilities.put(Boa.class, 80);
        bearProbabilities.put(Horse.class, 40);
        bearProbabilities.put(Deer.class, 80);
        bearProbabilities.put(Rabbit.class, 80);
        bearProbabilities.put(Mouse.class, 90);
        bearProbabilities.put(Goat.class, 70);
        bearProbabilities.put(Sheep.class, 70);
        bearProbabilities.put(Boar.class, 50);
        bearProbabilities.put(Buffalo.class, 20);
        bearProbabilities.put(Duck.class, 10);
        eatingProbabilities.put(Bear.class, bearProbabilities);

        Map<Class<? extends Animal>, Integer> eagleProbabilities = new HashMap<>();
        eagleProbabilities.put(Fox.class, 10);
        eagleProbabilities.put(Rabbit.class, 90);
        eagleProbabilities.put(Mouse.class, 90);
        eagleProbabilities.put(Duck.class, 80);
        eatingProbabilities.put(Eagle.class, eagleProbabilities);

        Map<Class<? extends Animal>, Integer> duckProbabilities = new HashMap<>();
        duckProbabilities.put(Caterpillar.class, 90);
        eatingProbabilities.put(Duck.class, duckProbabilities);
    }

    public static int getProbability(Class<? extends Animal> predatorClass, Class<? extends Animal> preyClass) {
        Map<Class<? extends Animal>, Integer> preyMap = eatingProbabilities.get(predatorClass);
        if (preyMap != null) {
            Integer probability = preyMap.get(preyClass);
            if (probability != null) {
                return probability;
            }
        }
        return 0;
    }
}

