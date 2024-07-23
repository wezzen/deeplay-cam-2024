package io.deeplay.camp.game.entities;

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
}
