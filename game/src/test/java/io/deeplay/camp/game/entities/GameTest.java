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

    /*@Test
    void gameConnectingStartedTest() {
        Field field2 = new Field(5);
        game = new Game(field2);
        Cell startPositionSM;
        Cell startPosition;
        Cell endPositionSM;
        Move move;
        startPositionSM =  field2.getBoard()[0][0];
        startPosition =  field2.getBoard()[4][0];
        endPositionSM = field2.getBoard()[4][4];
        Fleet fleet = new Fleet(startPositionSM, player1);
        Fleet fleet_ = new Fleet(startPosition, player2);
        move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY, 0);
        game.connectingPlayer("testPlayer");
        game.connectingPlayer("secondPlayer");
        game.gameStarted(field2);
        assertEquals(game.getNextPlayerToAct(), "testPlayer");
        game.getPlayerAction(move, "testPlayer");
    }*/
}
