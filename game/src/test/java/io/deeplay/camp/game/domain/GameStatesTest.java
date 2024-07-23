package io.deeplay.camp.game.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStatesTest {
    @Test
    public void testEnumValues() {
        GameStates[] expectedValues = {
                GameStates.CHECK,
                GameStates.COMPLETE,
                GameStates.PRECEDENCE,
                GameStates.DRAW,
                GameStates.DEFAULT
        };

        GameStates[] actualValues = GameStates.values();

        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(GameStates.CHECK, GameStates.valueOf("CHECK"));
        assertEquals(GameStates.COMPLETE, GameStates.valueOf("COMPLETE"));
        assertEquals(GameStates.PRECEDENCE, GameStates.valueOf("PRECEDENCE"));
        assertEquals(GameStates.DRAW, GameStates.valueOf("DRAW"));
        assertEquals(GameStates.DEFAULT, GameStates.valueOf("DEFAULT"));
    }

    @Test
    public void testEnumNames() {
        assertEquals("CHECK", GameStates.CHECK.name());
        assertEquals("COMPLETE", GameStates.COMPLETE.name());
        assertEquals("PRECEDENCE", GameStates.PRECEDENCE.name());
        assertEquals("DRAW", GameStates.DRAW.name());
        assertEquals("DEFAULT", GameStates.DEFAULT.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, GameStates.CHECK.ordinal());
        assertEquals(1, GameStates.COMPLETE.ordinal());
        assertEquals(2, GameStates.PRECEDENCE.ordinal());
        assertEquals(3, GameStates.DRAW.ordinal());
        assertEquals(4, GameStates.DEFAULT.ordinal());
    }
}
