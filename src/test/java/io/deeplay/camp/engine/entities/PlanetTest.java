package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {
    @Test
    void testPlanetCreationWithState() {
        Planet planet = new Planet(10);
        assertEquals(10, planet.points, "Planet points = 10");
    }
}
