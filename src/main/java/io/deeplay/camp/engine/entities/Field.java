package io.deeplay.camp.engine.entities;

import java.util.Random;

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
                        board[size-1-i][size-1-j] = new Cell(j, i, new Planet(temp));
                    }
                } else {
                    board[i][j] = new Cell(i, j);
                    if (i != j) {
                        board[size-1-i][size-1-j] = new Cell(j, i);
                    }
                }
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }
}
