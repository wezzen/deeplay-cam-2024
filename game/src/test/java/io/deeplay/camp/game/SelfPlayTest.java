package io.deeplay.camp.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelfPlayTest {
    SelfPlay selfPlay;
    String[] names;

    @Test
    void templateSelfPlayTest() {
        names = new String[]{"TestPlayer0", "TestPlayer1"};
        selfPlay = new SelfPlay(4, names);
        assertThrows(RuntimeException.class, () -> selfPlay.playGame());
    }
}
