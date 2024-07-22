package io.deeplay.camp;

import io.deeplay.camp.engine.domain.GalaxyListener;
import io.deeplay.camp.engine.domain.GameTypes;
import io.deeplay.camp.engine.entities.*;

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
    public void gameStarted(Field newField) {
        field = newField;
        LOGGER.log(Level.INFO, "Начало игры ");
    }

    @Override
    public void getPlayerAction(Move move, Player player) {
        if (field != null) {
            field.updateField();//todo: жду пока дпопишется логика ходов
            String fieldString = ConvertorFieldToString.convertFieldToString(field);
            LOGGER.log(Level.INFO, "\n" + fieldString);
        }else {
            LOGGER.log(Level.WARNING , "ПУСТОЕ ПОЛЕ");
        }
    }

    @Override
    public void gameEnded(Player winner) {
        LOGGER.log(Level.INFO, "Игра закончилась. Выиграл игрок:" + winner.getName());
    }

    @Override
    public void endGameSession() {
        LOGGER.log(Level.INFO, "Конец игровой сессии");
    }


}
