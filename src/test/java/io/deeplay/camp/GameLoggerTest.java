package io.deeplay.camp;

import io.deeplay.camp.engine.domain.GameTypes;
import io.deeplay.camp.engine.entities.*;
import org.junit.jupiter.api.Test;


public class GameLoggerTest {
    GameLogger gameLogger = new GameLogger();


    @Test
    void startGameSessionTest() {
        gameLogger.startGameSession("1", GameTypes.BotVsBot);
    }

    @Test
    void connectingPlayerTest() {
        Player player = new Player(1, "Test");
        gameLogger.connectingPlayer(player);
    }



    @Test
    public void gameStartedTest() {
        gameLogger.gameStarted(new Field(5));
    }

    @Test
    public void getPlayerActionTest() {
        Player player = new Player(1, "Test");
        Cell startPositionSM = new Cell(1, 1);
        Cell endPositionSM = new Cell(5, 5);
        Move move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY);
        gameLogger.getPlayerAction(move,player);
    }

    @Test
    public void gameEndedTest() {
        Player player = new Player(1, "Test");
        gameLogger.gameEnded(player);
    }

    @Test
    public void endGameSessionTest() {
        gameLogger.endGameSession();
    }

}
