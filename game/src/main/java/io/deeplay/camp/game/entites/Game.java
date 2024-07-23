package io.deeplay.camp.game.entites;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер
 */
public class Game implements GalaxyListener {
    private final Field field;
    private GameTypes gameType;
    private List<Move> allGameMoves;
    public Player[] players = new Player[2];
    private Map<String, Player> playerNames;
    private String nextPlayerToAct;
    private String id;

    public Game(final Field field) {
        this.field = field;
        this.allGameMoves = null;
        this.playerNames = new HashMap<>();
    }


    public GameTypes getGameType() {
        return gameType;
    }


    public String getId() {
        return id;
    }

    public String getNextPlayerToAct() {
        return nextPlayerToAct;
    }


    public boolean isGameOver() {
        return field.isGameOver();
    }

    public Player isWinner() {
        return field.isWinner();
    }


    @Override
    public void startGameSession(String gameId, GameTypes gameType) {
        this.gameType = gameType;
        id = gameId;
    }

    @Override
    public void connectingPlayer(String waitingPlayerName) {
        if (players[0] == null) {
            players[0] = new Player(0, waitingPlayerName);
            playerNames.put(waitingPlayerName, players[0]);
        } else if (players[1] == null) {
            players[1] = new Player(1, waitingPlayerName);
            playerNames.put(waitingPlayerName, players[1]);
        } else throw new IllegalArgumentException("Игроки уже существуют");
    }

    @Override
    public void gameStarted(Field field, String firstPlayerName) {
        this.field.setBoard(field.getBoard());
        if (!playerNames.containsKey(firstPlayerName)) {
            throw new IllegalArgumentException("Отсутствует игрок:" + firstPlayerName);
        }
        nextPlayerToAct = firstPlayerName;
    }

    @Override
    public void getPlayerAction(Move move, String playerName) {
        if (!playerNames.containsKey(playerName)) {
            throw new IllegalArgumentException("Отсутствует игрок:" + playerName);
        }

        for (String name : playerNames.keySet()) {
            if (!name.equals(playerName)) {
                nextPlayerToAct = name;
                break;
            }
        }

        //todo проверка валидности хода
    }

    @Override
    public void gameEnded(String winner) {

    }

    @Override
    public void endGameSession() {

    }
}
