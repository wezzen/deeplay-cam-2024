package io.deeplay.camp.game;

import io.deeplay.camp.game.bots.Bot;
import io.deeplay.camp.game.bots.RandomBot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelfPlayTest {
    SelfPlay selfPlay;
    String[] names;

    @Test
    void templateSelfPlayTest() {
        names = new String[]{"TestPlayer0", "TestPlayer1"};
        final Bot.BotFactory[] factories = new Bot.BotFactory[] {
                new RandomBot.Factory(),
                new RandomBot.Factory()
        };
        selfPlay = new SelfPlay(4, names, factories);
        selfPlay.playGame();
//        assertThrows(RuntimeException.class, () -> selfPlay.playGame());
    }
}
