package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void testCellCreationWithoutPlanet() {
        Cell cell = new Cell(1, 2);
        assertEquals(1, cell.getX(), "Cell x = 1");
        assertEquals(2, cell.getY(), "Cell y = 2");
        assertNull(cell.getPlanet(), "Planet should be null");
    }

    @Test
    void testCellCreationWithPlanet() {
        Planet planet= new Planet(7);
        Cell cell = new Cell(3, 4, planet);
        assertEquals(3, cell.getX(), "Cell x = 3");
        assertEquals(4, cell.getY(), "Cell = 4");
        assertNotNull(cell.getPlanet(), "Planet should not be null when Planet");
        assertTrue(cell.getPlanet().getPoints() >= 0 && cell.getPlanet().getPoints() < 10,
                "Planet points should be between 0 and 9 inclusive");
    }
}
