package io.deeplay.camp.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTypesTest {
    @Test
    public void testEnumValues() {
        GameTypes[] expectedValues = {
                GameTypes.BotVsBot,
                GameTypes.HumanVsBot,
                GameTypes.HumansHuman
        };

        GameTypes[] actualValues = GameTypes.values();

        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(GameTypes.BotVsBot, GameTypes.valueOf("BotVsBot"));
        assertEquals(GameTypes.HumanVsBot, GameTypes.valueOf("HumanVsBot"));
        assertEquals(GameTypes.HumansHuman, GameTypes.valueOf("HumansHuman"));
    }

    @Test
    public void testEnumNames() {
        assertEquals("BotVsBot", GameTypes.BotVsBot.name());
        assertEquals("HumanVsBot", GameTypes.HumanVsBot.name());
        assertEquals("HumansHuman", GameTypes.HumansHuman.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, GameTypes.BotVsBot.ordinal());
        assertEquals(1, GameTypes.HumanVsBot.ordinal());
        assertEquals(2, GameTypes.HumansHuman.ordinal());
    }
}
