package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Planet;
import io.deeplay.camp.game.entites.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    Planet planet;

    @BeforeEach
    void setUp() {
        planet = new Planet(10);
    }

    @Test
    void testPlanetCreationWithState() {
        assertEquals(10, planet.points, "Planet points = 10");
    }

    @Test
    void testCaptureOnPlanet() {
        assertFalse(planet.isCaptured());
        planet.setOwner(new Player(1, "test"));
        assertTrue(planet.isCaptured());
    }

    @Test
    public void testPlanetCreation() {
        int points = 100;
        Planet planet = new Planet(points);

        assertEquals(points, planet.getPoints());
        assertNull(planet.getOwner());
        assertNull(planet.getCell());
        assertFalse(planet.isCaptured());
    }

    @Test
    public void testPlanetCopyConstructor() {
        int points = 100;
        Planet originalPlanet = new Planet(points);
        Player owner = new Player(0, "Player1");
        originalPlanet.setOwner(owner);

        Planet copiedPlanet = new Planet(originalPlanet);

        assertEquals(originalPlanet.getPoints(), copiedPlanet.getPoints());
        assertEquals(originalPlanet.getOwner(), copiedPlanet.getOwner());
        assertNull(copiedPlanet.getCell()); // Ячейка не копируется, устанавливается позже
    }

    @Test
    public void testSetAndGetOwner() {
        Planet planet = new Planet(100);
        Player owner = new Player(0, "Player1");

        planet.setOwner(owner);

        assertEquals(owner, planet.getOwner());
        assertTrue(planet.isCaptured());
    }

    @Test
    public void testSetAndGetCell() {
        Planet planet = new Planet(100);
        Cell cell = new Cell(0, 0, planet);

        planet.setCell(cell);

        assertEquals(cell, planet.getCell());
    }

    @Test
    public void testSetCellThrowsExceptionWhenCellAlreadySet() {
        Planet planet = new Planet(100);
        Cell cell1 = new Cell(0, 0, planet);
        Cell cell2 = new Cell(1, 1, planet);

        planet.setCell(cell1);

        assertThrows(IllegalArgumentException.class, () -> planet.setCell(cell2));
    }

    @Test
    public void testIsCaptured() {
        Planet planet = new Planet(100);
        Player owner = new Player(0, "Player1");

        assertFalse(planet.isCaptured());

        planet.setOwner(owner);

        assertTrue(planet.isCaptured());
    }
}
