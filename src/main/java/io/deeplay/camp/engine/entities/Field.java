package io.deeplay.camp.engine.entities;

public class Field {
    int size;
    Cell[][] board;

    public void updateField() {
    }

    public Field(int size) {
        this.size = size;
        generateField(size);
    }

    private void generateField(int size){

    }
    //Заглушка
}
