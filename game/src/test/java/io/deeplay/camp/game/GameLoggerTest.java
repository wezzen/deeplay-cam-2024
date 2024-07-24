package io.deeplay.camp.game;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.service.GameLogger;
import org.junit.jupiter.api.Test;

public class GameLoggerTest {

    @Test
    void gameLoggerTest() {
        GameLogger gameLogger = new GameLogger();
        String gameId = "TestID";
        GameTypes gameType = GameTypes.HumanVsBot;
        String PlayerName = "Test";
        Field field = new Field(5);
        Cell startPositionSM = new Cell(1, 1);
        Cell endPositionSM = new Cell(5, 5);
        Move move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY);

        gameLogger.startGameSession(gameId, gameType);
        gameLogger.connectingPlayer(PlayerName);
        gameLogger.gameStarted(field);
        gameLogger.getPlayerAction(move, PlayerName);
        gameLogger.gameEnded(PlayerName);
        gameLogger.endGameSession();
    }
}
