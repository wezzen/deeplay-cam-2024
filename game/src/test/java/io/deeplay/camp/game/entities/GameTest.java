package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.entites.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//todo переделать и добавить тесты сейчас заглушка для CI
class GameTest {

    private Field field;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    Game game;

    @BeforeEach
    void setUp() {
        field = new Field(10);
        player1 = new Player(0, "Player 1");
        player2 = new Player(1, "Player 2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game = new Game(field);
    }

    @Test
    void testConstructor() {
        Game game = new Game(field);
    }

    @Test
    void startGameSessionTest() {
        game.startGameSession("test", GameTypes.HumanVsBot);
        assertEquals(game.getId(), "test");
        assertEquals(game.getGameType(), GameTypes.HumanVsBot);
    }

    @Test
    void gameConnectingStartedTest() {
        Field field2 = new Field(5);
        Cell startPositionSM;
        Cell endPositionSM;
        Move move;
        startPositionSM = new Cell(1, 1);
        endPositionSM = new Cell(5, 5);
        move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY, 5);
        game.connectingPlayer("testPlayer");
        game.connectingPlayer("secondPlayer");
        game.gameStarted(field2);
        assertEquals(game.getNextPlayerToAct(), "testPlayer");
        game.getPlayerAction(move, "testPlayer");
    }

}
