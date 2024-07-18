package io.deeplay.camp.moves;

import io.deeplay.camp.engine.entities.Cell;
import io.deeplay.camp.engine.entities.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    @Test
    void testIsValidBorder() {
        assertTrue(Movement.isValidBorder(10, 1, 1));
        assertTrue(Movement.isValidBorder(8, 2, 7));
        assertFalse(Movement.isValidBorder(4, 5, 0));
        assertFalse(Movement.isValidBorder(10, -1, -1));
        assertFalse(Movement.isValidBorder(10, 0, -1));
        assertFalse(Movement.isValidBorder(10, 10, 0));
        assertFalse(Movement.isValidBorder(10, 0, 10));

    }

    @Test
    void testIsValidCell() {
        Cell emptyCell = new Cell(0, 0);
        Cell occupyCell = new Cell(1, 1, new Planet(1));

        assertTrue(Movement.isValidCell(emptyCell));
        assertFalse(Movement.isValidCell(occupyCell));
    }

    @Test
    void testIsNewPosition() {
        Cell firstCell = new Cell(0, 0);
        Cell secondCell = new Cell(1, 0);
        assertTrue(Movement.isNewPosition(firstCell, secondCell));
        assertFalse(Movement.isNewPosition(firstCell, firstCell));
    }
}