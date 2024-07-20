package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.domain.GameStates;
import io.deeplay.camp.engine.entities.domain.GameTypes;
import io.deeplay.camp.engine.entities.utill.Movement;

import java.util.ArrayList;
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
        this.allGameMoves = new ArrayList<>();
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

    public Move.MoveStatus addMove(Move move) {
        if (Movement.isValidMove(move, field)) {
            allGameMoves.add(move);
            field.updateField(move);
            return Move.MoveStatus.DONE;
        } else {
            return Move.MoveStatus.ILLEGAL_MOVE;
        }
    }

    public List<Move> getAllGameMoves() {
        return allGameMoves;
    }

}
