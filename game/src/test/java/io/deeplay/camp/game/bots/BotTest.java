package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    private Field field;
    private Bot testBot;
    private Player player;
    private Fleet fleet;
    private Ship ship;

    /*@BeforeEach
    public void setUp() {
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);
        List<Ship> shipList= new ArrayList<Ship>();
        field = new Field(8); // Например, создаём поле размером 8x8
        fleet = new Fleet(new Cell(0, 0), player);
        player = new Player(1, "TestPlayer");
        testBot = new RandomBot.Factory(player).createBot(field);
    }*/

    @BeforeEach
    public void setUp() {
        field = new Field(8); // Например, создаём поле размером 8x8
        player = new Player(1, "TestPlayer");
        Cell position = field.getBoard()[0][0];
        fleet = new Fleet(position, player);
        fleet = new Fleet(new Cell(0, 0), player); // Инициализируем флот
        ship = new Ship(Ship.ShipType.BASIC, fleet);
        testBot = new RandomBot.Factory(player).createBot(field);
    }

    @Test
    public void testGetAnswer() {
        Answer answer = testBot.getAnswer(field);
        assertNotNull(answer, "Answer should not be null");
        Move move = answer.getMove();
        assertNotNull(move, "Move should not be null");
        assertNotNull(move.startPosition(), "Start position of the move should not be null");
        assertNotNull(move.endPosition(), "End position of the move should not be null");
        assertEquals(Move.MoveType.ORDINARY, move.moveType(), "Move type should be ORDINARY");
    }

    @Test
    public void testStartGameSession() {
        assertDoesNotThrow(() -> testBot.startGameSession("testGameId", GameTypes.BotVsBot), "startGameSession should not throw any exception");
    }

    @Test
    public void testConnectingPlayer() {
        assertDoesNotThrow(() -> testBot.connectingPlayer("Player1"), "connectingPlayer should not throw any exception");
    }

    @Test
    public void testGameStarted() {
        assertDoesNotThrow(() -> testBot.gameStarted(field), "gameStarted should not throw any exception");
    }

    @Test
    public void testGetPlayerAction() {
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY);
        assertDoesNotThrow(() -> testBot.getPlayerAction(move, "Player1"), "getPlayerAction should not throw any exception");
    }

    @Test
    public void testGameEnded() {
        assertDoesNotThrow(() -> testBot.gameEnded("Player1"), "gameEnded should not throw any exception");
    }

    @Test
    public void testEndGameSession() {
        assertDoesNotThrow(() -> testBot.endGameSession(), "endGameSession should not throw any exception");
    }
    /*private Field field;
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
    }*/
}
