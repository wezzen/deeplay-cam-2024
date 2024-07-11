package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public abstract class Player {
    protected final Field field;
    protected final Collection<Move> legalMoves;
    protected final boolean isInCheck;
    protected ArrayList<Fleet> fleetList;
    private int gamePoints;

    protected Player(Field field, Collection<Move> legalMoves, boolean isInCheck) {
        this.field = field;
        this.legalMoves = legalMoves;
        this.isInCheck = isInCheck;
    }

    public boolean isInCheck() {
     return this.isInCheck;
    }

    /*private Fleet establishFleet (){
        return (Fleet) getActiveFleet().stream()
                .filter(fleet -> fleet)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }*/

    public Field getField() {
        return field;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public int getCurrentGamePoints() {
        return gamePoints;
    }

    public int getNewGamePoint(int points) {
        return gamePoints + points;
    }

    static Collection<Move> calculateAttacksOnTile(final int[] tile,
                                                   final Collection<Move> moves) { //Метод фильтрует коллекцию ходов moves с помощью стрима stream(), оставляя только те ходы, у которых координаты назначения совпадают с заданной клеткой
        return moves.stream()
                .filter(move -> Arrays.equals(move.getDestinationCoordinate(), tile)) //Либо move.getDestinationCoordinate() == tile
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList)); //Использует метод collect для сбора отфильтрованных ходов в неизменяемый список
    }

    //какой-то makeMove

    public abstract Player getOpponent(); //Надо вынести

    public ArrayList<Fleet> getFleetList() { //Если унас есть разные флоты, которые мы составили
        return fleetList;
    }
}
