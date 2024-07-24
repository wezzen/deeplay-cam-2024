package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Answer;
import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    private Field field;
    private Bot testBot;

    @BeforeEach
    public void setUp() {
        field = new Field(8); // Например, создаём поле размером 8x8
        testBot = new RandomBot.Factory().createBot(field);
    }

    @Test
    public void testGetAnswer() {
        Answer answer = testBot.getAnswer(field);
        assertNotNull(answer);
        assertEquals(new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY), answer.getMove());
    }

    @Test
    public void testStartGameSession() {
        assertDoesNotThrow(() -> testBot.startGameSession("testGameId", GameTypes.BotVsBot));
    }

    @Test
    public void testConnectingPlayer() {
        assertDoesNotThrow(() -> testBot.connectingPlayer("Player1"));
    }

    @Test
    public void testGameStarted() {
        assertDoesNotThrow(() -> testBot.gameStarted(field));
    }

    @Test
    public void testGetPlayerAction() {
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY);
        assertDoesNotThrow(() -> testBot.getPlayerAction(move, "Player1"));
    }

    @Test
    public void testGameEnded() {
        assertDoesNotThrow(() -> testBot.gameEnded("Player1"));
    }

    @Test
    public void testEndGameSession() {
        assertDoesNotThrow(() -> testBot.endGameSession());
    }
}
