package io.deeplay.camp.engine.entities;

import java.util.*;

/**
 * Класс игрового поля
 */
public class Field {


    private final int size;
    private Cell[][] board;

    public void updateField() {
    }

    public Field(final int size) {
        this.size = size;
        generateField(size);
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Метод для нахождения всех планет на поле.
     *
     * @return список клеток, содержащих планеты.
     */
    public List<Cell> getAllPlanets() {
        List<Cell> planets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getPlanet() != null) {
                    planets.add(board[i][j]);
                }
            }
        }
        return planets;
    }

    /**
     * Простая генерация зеркальной карты
     *
     * @param size размер поля
     */
    private void generateField(int size) {
        Random random = new Random();
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (random.nextInt(2) == 1) {
                    int temp = random.nextInt(10);
                    board[i][j] = new Cell(i, j, new Planet(temp));
                    if (i != j) {
                        board[j][i] = new Cell(j, i, new Planet(temp));

                    }
                } else {
                    board[i][j] = new Cell(i, j);
                    if (i != j) {
                        board[j][i] = new Cell(j, i);
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return size == field.size && Arrays.equals(board, field.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}
