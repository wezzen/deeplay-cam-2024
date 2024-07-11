package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void testCellCreationWithoutPlanet() {
        Cell cell = new Cell(1, 2, false);
        assertEquals(1, cell.getX(), "Cell x = 1");
        assertEquals(2, cell.getY(), "Cell y = 2");
        assertFalse(cell.isPlanet, "Cell should not be a planet");
        assertNull(cell.planet, "Planet should be null when isPlanet is false");
    }

    @Test
    void testCellCreationWithPlanet() {
        Cell cell = new Cell(3, 4, true);
        assertEquals(3, cell.getX(), "Cell x = 3");
        assertEquals(4, cell.getY(), "Cell = 4");
        assertTrue(cell.isPlanet, "Cell should be a planet");
        assertNotNull(cell.planet, "Planet should not be null when isPlanet is true");
        assertTrue(cell.planet.getPoints() >= 0 && cell.planet.getPoints() < 10,
                "Planet points should be between 0 and 9 inclusive");
    }
}
