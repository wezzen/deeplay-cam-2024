package io.deeplay.camp.engine.entities.move;

import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.utils.Movement;

import java.util.List;
import java.util.stream.Collectors;

public class Actions {
    private final List<Move> legalMoves;


    public Actions(List<Move> legalMoves, Field field) {
        this.legalMoves = validateMoves(legalMoves, field);
    }

    /**
     * Метод валидации ходов
     *
     * @return список валидных ходов
     */
    private List<Move> validateMoves(List<Move> legalMoves, Field field) {
        return legalMoves.stream()
                .filter(x -> Movement.isValidMove(x, field))
                .collect(Collectors.toList());
    }

    public List<Move> getLegalMoves() {
        return legalMoves;
    }
}