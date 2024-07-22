package io.deeplay.camp;

import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.Game;
import io.deeplay.camp.engine.entities.Player;
import io.deeplay.camp.engine.entities.domain.GameTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


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
    public void gameLobbyTest() {
        gameLogger.gameLobby();
    }

    @Test
    public void gameStartedTest() {
        gameLogger.gameStarted();
    }

    @Test
    public void getPlayerActionTest() {
        Field field = new Field(5);
        Player player = new Player(1, "Test");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        Game game = new Game(field, playerList, GameTypes.HumanVsBot);
        gameLogger.getPlayerAction(game);
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
