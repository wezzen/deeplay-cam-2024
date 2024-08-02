package io.deeplay.camp.game.entites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyEntityTest {
    @Test
    void testIdGeneration() {
        GalaxyEntity entity1 = new GalaxyEntity();
        GalaxyEntity entity2 = new GalaxyEntity();
        assertNotEquals(entity1.getId(), entity2.getId());
        assertEquals(0, entity1.getId());
        assertEquals(1, entity2.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        GalaxyEntity entity1 = new GalaxyEntity();
        GalaxyEntity entity2 = new GalaxyEntity();
        GalaxyEntity entity3 = new GalaxyEntity();
        assertNotEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
        assertNotEquals(entity2, entity3);
    }
}