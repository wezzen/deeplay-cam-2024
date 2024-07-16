package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.domain.GameStates;
import io.deeplay.camp.engine.entities.domain.GameTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private final Field field;
    private final ArrayList<Player> players;
    private GameStates currentState;
    private GameTypes gameType;

    //Пока что не придумал куда и как пихать цикл обновление счетчика

    public Game(Field field, ArrayList<Player> players, GameTypes gameType) {
        this.field = field;
        this.players = players;
        this.gameType = gameType;
        this.currentState = GameStates.DEFAULT;
    }

    public void startGame() {
        this.currentState = GameStates.PRECEDENCE;
        while (!isGameOver()) {
            for (Player player : players) {
                executeTurn(player);
                if (isGameOver()) {
                    break;
                }
            }
        }
        this.currentState = GameStates.COMPLETE;
    }

    private void executeTurn(Player player) {
        Collection<Move> legalMoves = player.getLegalMoves();
        if (legalMoves.isEmpty()) {
            return;
        }
        // Для простоты давайте просто выполним первый законный ход
        Move move = legalMoves.iterator().next();
        executeMove(player, move);
    }

    private void executeMove(Player player, Move move) {
        // Реализую логику для выполнения хода
        // Это может быть перемещение флота, атака на планету и т.д.
        System.out.println(player.getName() + " executes move: " + move);
        // Обновляем состояние игры, на основе перемещения
    }

    public boolean isGameOver() {
        // Реализую логику для определения окончания игры
        // Пока что только на захват всех планет
        return false;
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
}
