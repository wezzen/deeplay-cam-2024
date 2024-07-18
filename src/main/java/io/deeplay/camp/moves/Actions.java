package io.deeplay.camp.moves;

import io.deeplay.camp.engine.entities.Cell;
import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.Fleet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Класс, который обрабатывает шаги флота
public class Actions extends KeyAdapter {
    private final Field field;
    private final Fleet fleet;
    private Direction currentDirection;

    public Actions(Field field, Fleet fleet) {
        this.field = field;
        this.fleet = fleet;
    }

    // Обработка нажатия клавиш
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                currentDirection = Direction.UP;
                moveFleet();
                break;
            case KeyEvent.VK_S:
                currentDirection = Direction.DOWN;
                moveFleet();
                break;
            case KeyEvent.VK_A:
                currentDirection = Direction.LEFT;
                moveFleet();
                break;
            case KeyEvent.VK_D:
                currentDirection = Direction.RIGHT;
                moveFleet();
                break;
        }
    }

    private void moveFleet() {
        int dx = 0, dy = 0;
        switch (currentDirection) {
            case UP -> dy = 1;
            case DOWN -> dy = -1;
            case LEFT -> dx = -1;
            case RIGHT -> dx = 1;
        }

        Cell fleetPosition = fleet.getFleetPosition();
        int newX = fleetPosition.x + dx;
        int newY = fleetPosition.y + dy;
        Cell newPosition = new Cell(newX, newY);

//      Проверки валидности

        if (Movement.isValidBorder(field.getSize(), newX, newY)
                && Movement.isValidCell(newPosition)
                && Movement.isNewPosition(fleetPosition, newPosition)) {

            // Изменение позиции флота

            fleet.setFleetPosition(newPosition);
            newPosition.setFleet(fleet);
            fleetPosition.setFleet(null);
        }
    }
}