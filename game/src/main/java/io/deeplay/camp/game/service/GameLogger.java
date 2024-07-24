package io.deeplay.camp.game.service;


import com.sun.tools.javac.Main;
import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GameLogger implements GalaxyListener {
    static Logger LOGGER;
    private Field field = null;

    static {
        try (FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(GalaxyListener.class.getName());
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
    public void connectingPlayer(String waitingPlayerName) {
        LOGGER.log(Level.INFO, "Подключился игрок " + waitingPlayerName);
    }

    @Override
    public void gameStarted(Field field) {
        this.field = field;
        LOGGER.log(Level.INFO, "Начало игры ");
        String fieldString = ConvertorFieldToString.convertFieldToString(field);
        LOGGER.log(Level.INFO, "\n" + fieldString);
    }

    @Override
    public void getPlayerAction(Move move, String playerName) {
        LOGGER.log(Level.INFO, "Сходил игрок " + playerName);
        if (field != null) {
            field.updateField();//todo: жду пока дпопишется логика ходов
            String fieldString = ConvertorFieldToString.convertFieldToString(field);
            LOGGER.log(Level.INFO, "\n" + fieldString);
        } else {
            LOGGER.log(Level.WARNING, "ПУСТОЕ ПОЛЕ");
        }
    }

    @Override
    public void gameEnded(String winner) {
        LOGGER.log(Level.INFO, "Игра закончилась. Выиграл игрок:" + winner);

    }


    @Override
    public void endGameSession() {
        LOGGER.log(Level.INFO, "Конец игровой сессии");
    }


}
