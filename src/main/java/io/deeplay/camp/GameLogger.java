package io.deeplay.camp;

import io.deeplay.camp.engine.entities.*;
import io.deeplay.camp.engine.entities.domain.GameEvents;
import io.deeplay.camp.engine.entities.domain.GameTypes;

import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GameLogger implements GameEvents {
    static Logger LOGGER;

    static {
        try (FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Main.class.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void startGameSession(String gameId, GameTypes gameType) {
        LOGGER.log(Level.INFO, "Начало игровой сессии " + gameId);
        LOGGER.log(Level.INFO, "Игра в формате " + gameType.toString());
    }

    @Override
    public void connectingPlayer(Player waitingPlayer) {
        LOGGER.log(Level.INFO, "Подключился игрок " + waitingPlayer);
    }

    @Override
    public List<Player> gameLobby() {
        LOGGER.log(Level.INFO, "Ожидание всех игроков");
        return null;
    }

    @Override
    public Game gameStarted() {
        LOGGER.log(Level.INFO, "Начало игры ");
        return null;
    }

    @Override
    public boolean getPlayerAction(Game game) {
        String field = ConvertorFieldToString.convertFieldToString(game.getField());
        LOGGER.log(Level.INFO, "\n" + field);
        return true;
    }

    @Override
    public void gameEnded(Player winner) {
        LOGGER.log(Level.INFO, "Игра закончилась. Выйграл игрок:" + winner.getName());
    }

    @Override
    public void endGameSession() {
        LOGGER.log(Level.INFO, "Конец игровой сессии");
    }


}
