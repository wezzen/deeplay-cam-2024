package io.deeplay.camp.game.utils;


import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import org.slf4j.LoggerFactory;


import org.slf4j.Logger;


public class GameLogger implements GalaxyListener {
    static Logger LOGGER;
    private Field field = null;

    private static final Logger logger = LoggerFactory.getLogger(
            GalaxyListener.class);

    @Override
    public void startGameSession(String gameId, GameTypes gameType) {
        logger.info("Начало игровой сессии {}", gameId);
        logger.info("Игра в формате {}", gameType.toString());

    }

    @Override
    public void connectingPlayer(String waitingPlayerName) {
        logger.info("Подключился игрок {}", waitingPlayerName);
    }

    @Override
    public void gameStarted(Field field) {
        this.field = field;
        logger.info("Начало игры ");
        String fieldString = ConvertorFieldToString.convertFieldToString(field);
        logger.info("\n{}", fieldString);
    }

    @Override
    public void getPlayerAction(Move move, String playerName) {
        logger.info("Сходил игрок {}", playerName);
        if (field != null) {
            field.updateField();//todo: жду пока дпопишется логика ходов
            String fieldString = ConvertorFieldToString.convertFieldToString(field);
            logger.info("\n{}", fieldString);
        } else {
            logger.error("ПУСТОЕ ПОЛЕ");
        }
    }

    @Override
    public void gameEnded(String winner) {
        logger.info("Игра закончилась. Выиграл игрок:{}", winner);
    }


    @Override
    public void endGameSession() {
        logger.info("Конец игровой сессии");
    }


}
