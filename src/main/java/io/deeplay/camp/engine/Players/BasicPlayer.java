package io.deeplay.camp.engine.Players;

import io.deeplay.camp.engine.domain.MoveType;
import io.deeplay.camp.engine.entities.Fleet;
import io.deeplay.camp.engine.entities.Move;
import io.deeplay.camp.engine.entities.Planet;
import io.deeplay.camp.engine.entities.Player;

import java.util.ArrayList;
import java.util.Collection;

public class BasicPlayer extends Player {
    /*Move moveOrdinary = new Move(new int []{0,0}, new int []{1, 1}, MoveType.ORDINARY);
    Move moveCapture = new Move(new int []{1,1}, new int []{2, 2}, MoveType.CAPTURE);*/
    public BasicPlayer(Collection<Move> legalMoves, boolean isInCheck, ArrayList<Fleet> fleetList, ArrayList<Planet> controlledPlanet) {
        super(legalMoves, isInCheck, fleetList, controlledPlanet);
    }
    /*public BasicPlayer(String player1) {
        super(null, true, );
    }*/
}
