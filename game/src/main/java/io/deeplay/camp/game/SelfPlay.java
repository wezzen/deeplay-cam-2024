package io.deeplay.camp.game;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.entites.Answer;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Game;
import io.deeplay.camp.game.interfaces.PlayerInterface;

import java.util.Map;

public class SelfPlay {

    private final int sizeField;
    private String[] playerNames;
    PlayerInterface[] players;
    Map<String, PlayerInterface> stringPlayerInterfaceMap;
    GalaxyListener[] listeners;

    public SelfPlay(int sizeField, String[] playerNames) {
        this.sizeField = sizeField;
        this.playerNames = playerNames;
    }

    private void initializePlayers(Field field) {
        for (PlayerInterface player : players) {
            player.gameStarted(field);
        }
    }

    private void initializeListeners(Field field) {
        for (GalaxyListener listener : listeners) {
            listener.gameStarted(field);
        }
    }


    public void playGame() {

        for (int i = 0; i < playerNames.length; i++) {
            stringPlayerInterfaceMap.put(playerNames[i], players[i]);
        }
        Field field = new Field(sizeField);
        final Game game = new Game(field);
        String currentPlayer;
        Answer answer;
        initializePlayers(field);
        initializeListeners(field);
        while (!game.isGameOver()) {
            currentPlayer = game.getNextPlayerToAct();
            answer = stringPlayerInterfaceMap.get(currentPlayer).getAnswer();
            //todo валидировать
            game.getPlayerAction(answer.getMove(), currentPlayer);
            //todo валидировать опять(?)
            for (PlayerInterface player : players) {
                player.getPlayerAction(answer.getMove(), currentPlayer);
            }
            for (GalaxyListener listener : listeners) {
                listener.getPlayerAction(answer.getMove(), currentPlayer);
            }
        }

        String winner = game.isWinner().getName();
        for (PlayerInterface player : players) {
            player.gameEnded(winner);
        }
        for (GalaxyListener listener : listeners) {
            listener.gameEnded(winner);
        }
    }

    public static void main(String[] args) {


    }
}
