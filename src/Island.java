public class Island {
    private Cell[][] grid;
    private int width;
    private int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];

        // Заполняем остров клетками
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell(10);  // Допустим, каждая клетка может содержать до 10 животных
            }
        }
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveAnimal(Animal animal, int oldX, int oldY, int newX, int newY) {
        // Получаем текущую и новую клетку
        Cell currentCell = grid[oldX][oldY];
        Cell newCell = grid[newX][newY];

        // Удаляем животное из текущей клетки
        currentCell.getAnimals().remove(animal);

        // Проверяем, есть ли место в новой клетке для добавления животного
        if (newCell.getFreeSpace() > 0) {
            // Добавляем животное в новую клетку
            newCell.addAnimal(animal);
        } else {
            // Если на новой клетке нет места, животное остается на старой клетке
            currentCell.addAnimal(animal);
            System.out.println("Не удалось переместить " + animal + " из-за отсутствия места на клетке (" + newX + ", " + newY + ")");
        }
    }

}
