package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testCellCreationWithoutPlanet() {
        Cell cell = new Cell(1, 2);
        assertEquals(1, cell.x, "Cell x = 1");
        assertEquals(2, cell.y, "Cell y = 2");
        assertNull(cell.planet, "Planet should be null");
    }

    @Test
    void testCellCreationWithPlanet() {
        Planet planet = new Planet(7);
        Cell cell = new Cell(3, 4, planet);
        assertEquals(3, cell.x, "Cell x = 3");
        assertEquals(4, cell.y, "Cell = 4");
        assertNotNull(cell.planet, "Planet should not be null when Planet");
        assertTrue(cell.planet.points >= 0 && cell.planet.points < 10,
                "Planet points should be between 0 and 9 inclusive");
    }
}
