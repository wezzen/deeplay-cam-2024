package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.domain.GameStates;
import io.deeplay.camp.engine.entities.domain.GameTypes;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Контроллер
 */
public class Game {
    private final Field field;
    private final ArrayList<Player> players;
    private GameStates currentState;
    private GameTypes gameType;
    private ArrayList<Move> allGameMoves;

    //Пока что не придумал куда и как пихать цикл обновление счетчика

    public Game(Field field, ArrayList<Player> players, GameTypes gameType) {
        this.field = field;
        this.players = players;
        this.gameType = gameType;
        this.currentState = GameStates.DEFAULT;
        this.allGameMoves = null;
    }

    public void startGame() {
        setCurrentState(GameStates.CHECK);
    }

    private void executeTurn(Player player) {
    }

    public void executeMove(Player player, Move move) {
        setCurrentState(GameStates.PRECEDENCE);
    }

    public void updateGameState() {
    }

    public boolean isGameOver() {
        return currentState == GameStates.COMPLETE || currentState == GameStates.DRAW;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public GameTypes getGameType() {
        return gameType;
    }

    public void setGameType(GameTypes gameType) {
        this.gameType = gameType;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setCurrentState(GameStates currentState) {
        this.currentState = currentState;
    }
}
