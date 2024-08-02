package io.deeplay.camp.game.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTypesTest {
    @Test
    public void testEnumValues() {
        GameTypes[] expectedValues = {
                GameTypes.HumanVsBot,
        };

        GameTypes[] actualValues = GameTypes.values();

    }

    @Test
    public void testEnumValueOf() {
        assertEquals(GameTypes.HumanVsBot, GameTypes.valueOf("HumanVsBot"));
    }

    @Test
    public void testEnumNames() {
        assertEquals("HumanVsBot", GameTypes.HumanVsBot.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(1, GameTypes.HumanVsBot.ordinal());
    }
}
