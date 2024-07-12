package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    @Test
    void testPlanetCreationWithState() {
        Planet planet = new Planet(10);
        assertEquals(10, planet.getPoints(), "Planet points = 10");
    }


}
