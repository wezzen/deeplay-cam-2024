package io.deeplay.camp.game.entites;

import io.deeplay.camp.game.domain.GameStates;
import io.deeplay.camp.game.domain.GameTypes;

import java.util.List;

/**
 * Контроллер
 */
public class Game {
    private final Field field;
    private final List<Player> players;
    private GameStates currentState;
    private GameTypes gameType;
    private List<Move> allGameMoves;

    //Пока что не придумал куда и как пихать цикл обновление счетчика

    public Game(final Field field, final List<Player> players, final GameTypes gameType) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setCurrentState(GameStates currentState) {
        this.currentState = currentState;
    }
}
