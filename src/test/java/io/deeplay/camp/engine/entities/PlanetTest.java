package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    @Test
    void testPlanetCreationWithState() {
        Planet planet = new Planet(10, Planet.PlanetState.IS_OCCUPIED);
        assertEquals(10, planet.getPoints(), "Planet points = 10");
        assertEquals(Planet.PlanetState.IS_OCCUPIED, planet.getState(), "Planet state = IS_OCCUPIED");
    }

    @Test
    void testPlanetCreationWithoutState() {
        Planet planet = new Planet(20);
        assertEquals(20, planet.getPoints(), "Planet points = 20");
        assertEquals(Planet.PlanetState.FREE, planet.getState(), "Planet state = FREE by default");
    }
}
