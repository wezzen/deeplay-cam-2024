package io.deeplay.camp.game.entites;

import java.util.*;

/**
 * Класс игрового поля
 */
public class Field {


    private final int size;
    private Cell[][] board;
    public final List<Cell> planetsOnField = new ArrayList<>();

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
     * Простая генерация зеркальной карты
     *
     * @param size размер поля
     */
    private void generateField(final int size) {
        Random random = new Random();
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (random.nextInt(2) == 1) {
                    int temp = random.nextInt(10);
                    Planet newPlanet = new Planet(temp);
                    board[i][j] = new Cell(i, j, newPlanet);
                    newPlanet.setCell(board[i][j]);
                    if (i != j) {
                        Planet newPlanet2 = new Planet(temp);
                        board[j][i] = new Cell(j, i, newPlanet2);
                        newPlanet2.setCell(board[j][i]);
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Field field)) return false;
        return size == field.size && Arrays.deepEquals(board, field.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }
}
