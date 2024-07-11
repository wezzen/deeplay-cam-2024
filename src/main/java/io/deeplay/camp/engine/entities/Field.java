package io.deeplay.camp.engine.entities;

import java.util.Random;

public class Field {
    int size;
    Cell[][] board;

    public void updateField() {
    }

    public Field(int size) {
        this.size = size;
        generateField(size);
    }

    private void generateField(int size) {
        Random random = new Random();
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (random.nextInt(2) == 1) {
                    board[i][j].generatePlanet();
                }
            }
        }

    }
    //Заглушка
}
