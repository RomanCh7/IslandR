public class Island {
    private Cell[][] model;
    private int width;
    private int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.model = new Cell[width][height];


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                model[i][j] = new Cell(20);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return model[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveAnimal(Animal animal, int oldX, int oldY, int newX, int newY) {
        Cell currentCell = model[oldX][oldY];
        Cell newCell = model[newX][newY];
        currentCell.getAnimals().remove(animal);
        if (newCell.getFreeSpace() > 0) {
            newCell.addAnimal(animal);
        } else {
            currentCell.addAnimal(animal);
        }
    }


}
